/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elmsoftware.qrscanner.adapters

import android.content.ContentValues
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.support.transition.Explode
import android.support.transition.Transition
import android.support.v4.view.MotionEventCompat
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.transition.TransitionSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.elmsoftware.clever_note.functions.DbManager
import com.elmsoftware.qrscanner.R
import com.elmsoftware.qrscanner.datas.Card
import com.elmsoftware.qrscanner.functions.MyDiffUtilCallback
import com.elmsoftware.qrscanner.interfaces.ItemTouchHelperAdapter
import com.elmsoftware.qrscanner.interfaces.ItemTouchHelperViewHolder
import com.elmsoftware.qrscanner.interfaces.OnStartDragListener


import java.util.ArrayList
import java.util.Collections

/**
 * Simple RecyclerView.Adapter that implements [ItemTouchHelperAdapter] to respond to move and
 * dismiss events from a [android.support.v7.widget.helper.ItemTouchHelper].
 *
 * @author Paul Burke (ipaulpro)
 */
class RecyclerListAdapter(private val context: Context, listNotes: ArrayList<Card>, private val mDragStartListener: OnStartDragListener) :
    RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>(), ItemTouchHelperAdapter {

    //private val mItems = ArrayList<String>()
    private var listNotes = ArrayList<Card>()
    var onItemClick: ((Int?, String?, String?) -> Unit)? = null
    var onItemDelete: (() -> Unit)? = null

    init {
        //mItems.addAll(Arrays.asList(*context.resources.getStringArray(R.array.dummy_items)))
        this.listNotes = listNotes
    }

    fun update(listNotes: ArrayList<Card>) {
        //this.listNotes.clear()
        //this.listNotes.addAll(listNotes)
        var count: Int = 0
        for (item: Card in listNotes)
        {
            this.listNotes.add(item)
            notifyItemChanged(count)
            count++
        }
        notifyDataSetChanged()
    }

    fun updateEmployeeListItems(my_data: ArrayList<Card>) {
        val diffCallback = MyDiffUtilCallback(my_data, this.listNotes)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.listNotes.clear()
        this.listNotes.addAll(my_data)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItem(card: Card) {
            this.listNotes.add(card)
            notifyItemChanged(this.listNotes.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        //getListFragmentExitTransition(view)
        val itemViewHolder = ItemViewHolder(view)
        return itemViewHolder
    }

    /*private fun getListFragmentExitTransition(itemView: View) {
        val epicCenterRect = Rect()
        //itemView is the full-width inbox item's view
        itemView.getGlobalVisibleRect(epicCenterRect)
        // Set Epic center to a imaginary horizontal full width line under the clicked item, so the explosion happens vertically away from it
        epicCenterRect.top = epicCenterRect.bottom
        val exitTransition = Explode()
        exitTransition.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition): Rect {
                return epicCenterRect
            }
        }
    }*/

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textView.text = listNotes[position].cardName

        // Start a drag whenever the handle view it touched
        holder.handleView.setOnTouchListener { v, event ->
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                mDragStartListener.onStartDrag(holder)
            }
            false
        }
    }

    fun moveFunc(fromPosition: Int, toPosition: Int){
        //val title = findViewById<EditText>(R.id.title)
        //val description = findViewById<EditText>(R.id.description)
        Log.e("fromPosition",fromPosition.toString())
        Log.e("toPosition",toPosition.toString())

        var dbManager = DbManager(context)
        var values_1 = ContentValues()
        values_1.put("Title", listNotes.get(fromPosition).cardName)
        values_1.put("Value", listNotes.get(fromPosition).cardValue)

        var values_2 = ContentValues()
        values_2.put("Title", listNotes.get(toPosition).cardName)
        values_2.put("Value", listNotes.get(toPosition).cardValue)

        var selectionArgs_1 = arrayOf(listNotes.get(toPosition).nodeID.toString())

        var selectionArgs_2 = arrayOf(listNotes.get(fromPosition).nodeID.toString())
        val ID = dbManager.rearange(values_1, values_2,"ID = ?", selectionArgs_1, selectionArgs_2)
        if (ID > 0){
            Toast.makeText(context,"Note is moved",Toast.LENGTH_SHORT)
            Log.e("ID_to","")
        }else{
            Toast.makeText(context,"Error move note...",Toast.LENGTH_SHORT)
        }

        Collections.swap(listNotes, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    fun removeFunc(position: Int){
        //val title = findViewById<EditText>(R.id.title)
        //val description = findViewById<EditText>(R.id.description)
        var dbManager = DbManager(context)
        var selectionArgs = arrayOf(listNotes.get(position).nodeID.toString())
        val ID = dbManager.delete("ID=?", selectionArgs)
        if (ID > 0){
            Toast.makeText(context,"Note is deleted",Toast.LENGTH_SHORT)
        }else{
            Toast.makeText(context,"Error delete note...",Toast.LENGTH_SHORT)
        }
    }

    override fun onItemDismiss(position: Int) {
        removeFunc(position)
        listNotes.removeAt(position)
        Log.e("listNotes Count: ", listNotes?.count().toString())
        if (listNotes?.count() == 0){
            onItemDelete?.invoke()
        }
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        moveFunc(fromPosition, toPosition)

        return true
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    /**
     * Simple example of a view holder that implements [ItemTouchHelperViewHolder] and has a
     * "handle" view that initiates a drag event when touched.
     */
    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), ItemTouchHelperViewHolder {

        val textView: TextView
        val handleView: ImageView
        val item: FrameLayout

        init {
            textView = itemView.findViewById<View>(R.id.text) as TextView
            handleView = itemView.findViewById<View>(R.id.handle) as ImageView
            item = itemView.findViewById<View>(R.id.item) as FrameLayout
            item.setOnClickListener {
                onItemClick?.invoke(listNotes.get(position).nodeID, listNotes.get(position).cardName, listNotes.get(position).cardValue)
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }
}
