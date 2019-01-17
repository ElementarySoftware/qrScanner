package com.elmsoftware.qrscanner.activitys

import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.elmsoftware.clever_note.functions.DbManager
import com.elmsoftware.qrscanner.MainActivity
import com.elmsoftware.qrscanner.R
import com.elmsoftware.qrscanner.adapters.PagerAdapter
import com.elmsoftware.qrscanner.adapters.RecyclerListAdapter
import com.elmsoftware.qrscanner.datas.Card
import com.elmsoftware.qrscanner.fragments.CameraFragment
import com.elmsoftware.qrscanner.fragments.CardFragment
import com.elmsoftware.qrscanner.interfaces.*
import com.google.zxing.BarcodeFormat
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.scanner_activity.*
import java.util.ArrayList
import com.elmsoftware.qrscanner.interfaces.MyInterface



class ScannerActivity : AppCompatActivity(), getFromCamera, setCurrentItem, saveCard {

    override fun save(title: String, code: String) {
        addFunc(title, code)
    }

    override fun setItem(position: Int) {
        pager.currentItem = position
    }

    fun load(loadListener: loadCards) {
        this.loadListener = loadListener
    }

    var code: String = ""
    private var loadListener: loadCards? = null

    override fun getDataFromCamera(code: String, format: BarcodeFormat) {
        this.code = code
        listener?.sendDataOnCard(code, format)
        pager.setCurrentItem(1, true)
    }

    var listener: sendOnCard? = null
    var pager_listener: setCurrentItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scanner_activity)
        /*val inter = intent.getSerializableExtra("interface") as MyInterface
        inter.aMethod()*/

        val pagerAdapter = PagerAdapter(supportFragmentManager, getFragments())
        pager.adapter = pagerAdapter
        pager.currentItem = 0
        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {}
                    1 -> {
                        //listener?.sendDataOnCard(code)
                    }
                }
            }
        })
    }

    fun sendDataJournal(listener: sendOnCard) {
        this.listener = listener
    }

    private fun getFragments(): List<Fragment> {
        val fList: MutableList<Fragment> = ArrayList()
        fList.add(CameraFragment())
        fList.add(CardFragment())
        return fList
    }

    fun addFunc(title: String, code: String){
        var dbManager = DbManager(this)
        var values = ContentValues()
        values.put("Title", title)
        values.put("Value", code)

        val ID = dbManager.insert(values)
        if (ID > 0){
            //Toast.makeText(this,"Note is added", Toast.LENGTH_SHORT)
            //Handler().postDelayed({
            loadListener?.load("%")
            finish()
            //Log.e("DB", dbManager.colID)
            /*var listNotes = ArrayList<Card>()
            val projection = arrayOf("ID", "Title", "Value")
            val selectionArgs = arrayOf("%")
            val cursor = dbManager.Query(projection, "Title Like ?", selectionArgs, "ID")
            MainActivity().listNotes.clear()
            if (cursor.count != 0) {
                cursor.moveToLast()
                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getInt(cursor.getColumnIndex("ID"))
                        val title = cursor.getString(cursor.getColumnIndex("Title"))
                        val value = cursor.getString(cursor.getColumnIndex("Value"))
                        Log.e("cursor",id.toString() + ":" + title + ":" + value)
                        MainActivity().listNotes.add(Card(id, title, value))
                        //MainActivity().adapter!!.addItem(Card(id, title, value))
                        //MainActivity().addItem(id, title, value)
                        //MainActivity().listNotes.add(Card(id, title, value))
                    } while (cursor.moveToNext())
                    //MainActivity().adapter?.update(listNotes)
                }
                //MainActivity().update(listNotes)
                //MainActivity().adapter!!.notifyDataSetChanged()
                if (MainActivity().adapter != null) {
                    list.adapter!!.notifyDataSetChanged()
                }
                //MainActivity().adapter?.notifyItemChanged(MainActivity().listNotes.count())
                finish()
            } else{
                //alert.visibility = VISIBLE
            }            //MainActivity().loadQuery("%")*/
            //MainActivity().addItem(title, code)
            //}, 1000)
        }else{
            //Toast.makeText(this,"Error adding note...", Toast.LENGTH_SHORT)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }
}