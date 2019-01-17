package com.elmsoftware.qrscanner.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elmsoftware.qrscanner.R
import com.elmsoftware.qrscanner.activitys.ScannerActivity
import com.elmsoftware.qrscanner.interfaces.saveCard
import com.elmsoftware.qrscanner.interfaces.sendOnCard
import com.elmsoftware.qrscanner.interfaces.setCurrentItem
import kotlinx.android.synthetic.main.card_fragment.*
import com.google.zxing.WriterException
import android.graphics.Bitmap
import android.graphics.Color
import android.widget.LinearLayout
import com.elmsoftware.qrscanner.Views.AndroidBarcodeView
import com.elmsoftware.qrscanner.functions.QRCodeEncoder
import com.elmsoftware.qrscanner.interfaces.addCard
import com.elmsoftware.qrscanner.models.MainViewModel
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.camera_fragment.*

class CardFragment : Fragment() {

    /*override fun getDataFromCamera(code: String) {
        Log.e("code", code)
        this.code.text = code
    }*/
    var codeStr: String = ""
    var listener: setCurrentItem? = null
    var save_listener: saveCard? = null
    private lateinit var model: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.card_fragment, container, false)
        model = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        model.getUsername().observe(this, Observer { text ->
            Log.e("text", text)
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //code_text.text = code_str
        val layout = view.findViewById(R.id.codeLayout) as android.support.constraint.ConstraintLayout



        (activity as ScannerActivity).sendDataJournal(object : sendOnCard {
            override fun sendDataOnCard(code: String, format: BarcodeFormat) {
                Log.e("code", code)
                code_text.text = "" + code
                codeStr = code
                //generateQR(code)
                qrCode.setImageBitmap(Ean13_Encode(code,200))

                val barView = AndroidBarcodeView(view.context, code)
                val imageParams =
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
                layout.addView(barView,imageParams)
                //barView.setData(code)
                //val bb = EAN13CodeBuilder("124958761310")
                //Log.e("EAN13", bb.code)
            }
        })
        btn_save.setOnClickListener {
            save_listener?.save(title_card.text.toString() ,codeStr)
        }
    }

    /*fun generateQR(content: String){
        val writer = QRCodeWriter()
        try {
            val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bmp.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            qrCode.setImageBitmap(bmp)

        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }*/

    fun EAN13(){
    }

    fun Ean13_Encode(qrData: String, qrCodeDimention: Int): Bitmap? {
        var bitmap: Bitmap? = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        val qrCodeEncoder = QRCodeEncoder(
            qrData, null,
            "text", null, qrCodeDimention
        )
        try {
            bitmap = qrCodeEncoder.encodeAsBitmap()
        } catch (e: WriterException) {
            e.printStackTrace()
        }

        return bitmap
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("code", codeStr)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            if (savedInstanceState.getString("code") != "") {
                code_text.text = savedInstanceState.getString("code")
                Log.e("code", savedInstanceState.getString("code"))
            } else{
            }
        } else{
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = context as setCurrentItem
            save_listener = context as saveCard
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement onSomeEventListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        save_listener = null
    }
}
