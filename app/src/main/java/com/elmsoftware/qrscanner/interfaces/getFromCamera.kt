package com.elmsoftware.qrscanner.interfaces

import com.google.zxing.BarcodeFormat

interface getFromCamera {
    fun getDataFromCamera(code: String, format: BarcodeFormat)
}
