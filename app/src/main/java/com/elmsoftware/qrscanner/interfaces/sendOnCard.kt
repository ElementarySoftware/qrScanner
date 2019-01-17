package com.elmsoftware.qrscanner.interfaces

import com.google.zxing.BarcodeFormat

interface sendOnCard {
    fun sendDataOnCard(code: String, format: BarcodeFormat)
}
