package com.elmsoftware.qrscanner.models

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.ClipData
import android.util.Log

class MyViewModel : ViewModel() {
    private lateinit var users: MutableLiveData<String>

    val selected = MutableLiveData<String>()

    fun select(item: String) {
        selected.value = item
    }
}