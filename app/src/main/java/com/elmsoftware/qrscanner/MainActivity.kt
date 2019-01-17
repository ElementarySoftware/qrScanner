package com.elmsoftware.qrscanner

import android.content.Intent
import android.content.pm.PackageManager
import android.opengl.Visibility
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import com.elmsoftware.clever_note.functions.DbManager
import com.elmsoftware.qrscanner.activitys.CardActivity
import com.elmsoftware.qrscanner.activitys.ScannerActivity
import com.elmsoftware.qrscanner.adapters.RecyclerListAdapter
import com.elmsoftware.qrscanner.datas.Card
import com.elmsoftware.qrscanner.functions.MyDiffUtilCallback
import com.elmsoftware.qrscanner.helper.SimpleItemTouchHelperCallback
import com.elmsoftware.qrscanner.interfaces.OnStartDragListener
import com.elmsoftware.qrscanner.interfaces.loadCards
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), OnStartDragListener {

    private var mItemTouchHelper: ItemTouchHelper? = null

    var listNotes = ArrayList<Card>()
    var adapter: RecyclerListAdapter? = null
    var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        if (ContextCompat.checkSelfPermission(
                applicationContext,
                android.Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 50)
        }
        recyclerView = findViewById<RecyclerView>(R.id.list)
        val inter = OnCreateListenerImpl()
        fab.setOnClickListener {
            val intent = Intent(this, ScannerActivity::class.java)
            intent.putExtra("interface", inter)
            startActivity(intent)
        }

        ScannerActivity().load(object : loadCards {
            override fun load(title: String) {
                Log.e("load", "title")
                loadQuery("%")
                //listNotes.add(Card(1, "title", "value"))

            }
        })

        /*val fragmentManager = supportFragmentManager
        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, GridFragment(), GridFragment::class.java!!.getSimpleName())
            .commit()*/

        adapter = RecyclerListAdapter(this, listNotes, this)

        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(this)

        adapter!!.onItemClick = { id, cardName, cardValue ->
            // do something with your item
            val intent = Intent(this, CardActivity::class.java)
            intent.putExtra("Id", id)
            intent.putExtra("Title", cardName)
            intent.putExtra("Value", cardValue)
            startActivity(intent)
        }
        adapter!!.onItemDelete = {  ->
            //Log.e("load", "title")
            loadQuery("%")
        }
        //listNotes.add(Card(0, "Title", "Value"))
        loadQuery("%")
        val callback = SimpleItemTouchHelperCallback(adapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper!!.attachToRecyclerView(list)
    }

    fun addItem(id: Int, title: String, value: String){
        Log.e("count",listNotes.count().toString())
        //adapter!!.addItem(Card(id, title, value))
        listNotes.add(Card(id, title, value))
    }

    fun update(listNotes: java.util.ArrayList<Card>){
        //this.listNotes = listNotes
        this.listNotes.clear()
        for (item in listNotes){
            this.listNotes.add(item)
        }
        adapter?.notifyDataSetChanged()
    }

    fun loadQuery(title: String){
        var dbManager = DbManager(this)
        val projection = arrayOf("ID", "Title", "Value")
        val selectionArgs = arrayOf(title)
        val cursor = dbManager.Query(projection, "Title Like ?", selectionArgs, "ID")
        listNotes.clear()
        if (cursor.count != 0) {
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndex("ID"))
                    val title = cursor.getString(cursor.getColumnIndex("Title"))
                    val value = cursor.getString(cursor.getColumnIndex("Value"))
                    listNotes.add(Card(id, title, value))
                } while (cursor.moveToNext())
            }
            alert.visibility = GONE
        } else{
            alert.visibility = VISIBLE
        }
        //adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        mItemTouchHelper!!.startDrag(viewHolder)
    }
}
