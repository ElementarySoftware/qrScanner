package com.elmsoftware.qrscanner.activitys

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.elmsoftware.qrscanner.R
import kotlinx.android.synthetic.main.card_activity.*

class CardActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.card_activity)
        val id = intent.getIntExtra("Id",0)
        val title = intent.getStringExtra("Title")
        val value = intent.getStringExtra("Value")
        Log.e("data", id.toString() + ", " + title.toString() + ", " + value.toString())
        title_card.text = title
        code_card.text = value
    }

}
