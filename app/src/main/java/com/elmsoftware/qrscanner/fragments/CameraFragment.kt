package com.elmsoftware.qrscanner.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.elmsoftware.qrscanner.R
import com.elmsoftware.qrscanner.interfaces.getFromCamera
import com.elmsoftware.qrscanner.models.MainViewModel
import com.elmsoftware.qrscanner.models.MyViewModel
import com.elmsoftware.qrscanner.models.SharedViewModel
import java.nio.channels.Selector

class CameraFragment : Fragment() {
    private lateinit var codeScanner: CodeScanner
    var listener: getFromCamera? = null

    private lateinit var itemSelector: Selector

    private lateinit var model: SharedViewModel

    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /* Called on Activity onCreate() */
        /*viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.getUsername().observe(this, Observer { text ->
            Log.e(TAG, text)
        })*/
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.camera_fragment, container, false)
    }

    fun getFromCamera(listener: getFromCamera) {
        this.listener = listener
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()

        codeScanner = CodeScanner(activity, scannerView)
        codeScanner.setDecodeCallback(object : DecodeCallback {
            override fun onDecoded(result: com.google.zxing.Result) {
                activity.runOnUiThread {
                    //Toast.makeText(activity, it.text, Toast.LENGTH_LONG).show()
                    Log.e("barcode", result.barcodeFormat.toString())
                    listener?.getDataFromCamera(result.text, result.barcodeFormat)
                    viewModel.sendCode(result.text)
                }
            }
        })

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = context as getFromCamera
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement onSomeEventListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}
