package com.elmsoftware.qrscanner.models

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val username = MutableLiveData<String>()

    fun sendCode(code: String){
        username.value = code
    }

    fun getUsername(): LiveData<String> {
        return username
    }
}
