package com.elmsoftware.qrscanner.Views


import android.util.AttributeSet
import com.onbarcode.barcode.android.*

import android.content.Context
import android.graphics.Canvas
import android.graphics.RectF
import android.graphics.Typeface
import android.util.Log
import android.view.View

class AndroidBarcodeView(context: Context, code: String) : View(context) {

    var code: String = ""

    init {
        this.code = code
    }

    var screenW: Int = 0
    var screenH: Int = 0
    var canvas: Canvas? = null

    public override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenW = w
        screenH = h
    }

    fun setData(code: String){
        draw(canvas)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //this.canvas = canvas

        try {
            //testCODABAR(canvas);
            //testCODE11(canvas);
            //testCODE2OF5(canvas);
            //testCODE39(canvas);
            //testCODE93(canvas);
            //testEAN8(canvas);
            //testEAN13(canvas);
            //testISBN(canvas);
            //testISSN(canvas);
            //testITF14(canvas);
            //testINTERLEAVED25(canvas);
            //testIDENTCODE(canvas);
            //testLEITCODE(canvas);
            //testMSI(canvas);
            //testONECODE(canvas);
            //testPLANET(canvas);
            //testPOSTNET(canvas);
            //testRM4SCC(canvas);
            //testUPCA(canvas);
            //testUPCE(canvas);
            //testCODE128(canvas);
            //testEAN128(canvas);
            testQRCode(canvas)
            //testDataMatrix(canvas);
            //testPDF417(canvas);


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @Throws(Exception::class)
    private fun testCODABAR(canvas: Canvas) {
        val barcode = Codabar()

        // barcode data to encode
        /*
	           Codabar Valid data char set:
		            - (Dash), $ (Dollar), : (Colon), / (Slash), . (Point), + (Plus)
		            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
	        */
        barcode.data = "112233445566"

        // Codabar Start & Stop Char, Valid values are 'A', 'B', 'C', 'D'
        barcode.startChar = 'A'
        barcode.stopChar = 'A'

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
          specify your barcode drawing area
        */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testCODE11(canvas: Canvas) {
        val barcode = Code11()

        // barcode data to encode
        /*
	           Code-11 Valid data char set:
		            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
                    - (Dash)
	        */
        barcode.data = "0123456789-"

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
           specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testCODE2OF5(canvas: Canvas) {
        val barcode = Code25()

        /*
           Code 2 of 5 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
        */
        barcode.data = "0123456789"

        // Code 2 of 5 Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.n = 3.0f

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testCODE39(canvas: Canvas) {
        val barcode = Code39()

        /*
           Code39 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
                A - Z (Uppercase letters)
                - (Dash), $ (Dollar), % (Percentage), (Space), . (Point), / (Slash), + (Plus)

           Code39 extension Valid data char set:
                All ASCII 128 characters
        */
        // Code39 encodes upper case chars only, for lower case chars, use Code 39 extension
        barcode.data = "123456789012"

        barcode.isExtension = false

        barcode.isAddCheckSum = true

        // Code 39 Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.n = 3.0f
        // The space between 2 characters in code 39; This a multiple of X; The default is 1.;
        // Valid value is from 1.0 (inclusive) to 5.3 (exclusive)
        barcode.i = 1.0f
        barcode.isShowStartStopInText = true

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testCODE93(canvas: Canvas) {
        val barcode = Code93()

        /*
           Code 93 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
                A - Z (Uppercase letters)
                - (Dash), $ (Dollar), % (Percentage), (Space), . (Point), / (Slash), + (Plus)
        */
        barcode.data = "CODE93DATA"

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testEAN8(canvas: Canvas) {
        val barcode = EAN8()

        /*
           EAN 8 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           EAN 8 Valid data length: 7 digits only, excluding the last checksum digit
        */
        barcode.data = "7777777"

        // for EAN8 with supplement data (2 or 5 digits)
        /*
        barcode.setSupData("12");
        // supplement bar height vs bar height ratio
        barcode.setSupHeight(0.8f);
        // space between barcode and supplement barcode (in pixel)
        barcode.setSupSpace(15);
        */

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 45f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", 0, 10)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testEAN13(canvas: Canvas) {
        val barcode = EAN13()

        /*
           EAN 13 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           EAN 13 Valid data length: 12 digits only, excluding the last checksum digit
        */
        barcode.data = "012345678901"

        // for EAN13 with supplement data (2 or 5 digits)
        /*
        barcode.setSupData("12");
        // supplement bar height vs bar height ratio
        barcode.setSupHeight(0.8f);
        // space between barcode and supplement barcode (in pixel)
        barcode.setSupSpace(15);
        */

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 45f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 10)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testISBN(canvas: Canvas) {
        val barcode = ISBN()

        /*
           ISBN Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           ISBN Valid data length: 12 digits only, excluding the last checksum digit
            and it must start with "978" or "979"
        */
        barcode.data = "978047082163"

        // for ISBN with supplement data (2 or 5 digits)
        /*
        barcode.setSupData("12");
        // supplement bar height vs bar height ratio
        barcode.setSupHeight(0.8f);
        // space between barcode and supplement barcode (in pixel)
        barcode.setSupSpace(15);
        */

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 45f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 10)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testISSN(canvas: Canvas) {
        val barcode = ISSN()

        /*
           ISSN Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           ISSN Valid data length: 9 digits only, excluding starting "977" and the last checksum digit
        */
        barcode.data = "345678000"

        // for ISSN with supplement data (2 or 5 digits)
        /*
        barcode.setSupData("12");
        // supplement bar height vs bar height ratio
        barcode.setSupHeight(0.8f);
        // space between barcode and supplement barcode (in pixel)
        barcode.setSupSpace(15);
        */

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 45f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 10)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testITF14(canvas: Canvas) {
        val barcode = ITF14()

        /*
           ITF 14 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           ITF 14 Valid data length: 13 digits only, excluding the last checksum digit
        */
        barcode.data = "0123456789012"

        // ITF-14 Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.n = 3.0f

        // ITF-14 bearer bar size vs bar module (X) size ratio
        // Valid values are 0-10 which are a multiple of X.
        barcode.bearerBarHori = 1f
        barcode.bearerBarVert = 1f

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testINTERLEAVED25(canvas: Canvas) {
        val barcode = Interleaved25()

        /*
           Interleaved 2 of 5 Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
        */
        barcode.data = "112233445566"

        barcode.isAddCheckSum = true

        // Interleaved 2 of 5 Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.n = 3.0f

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testIDENTCODE(canvas: Canvas) {
        val barcode = Identcode()

        /*
           Identcode Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           Identcode Valid data length: 11 digits only, excluding the last checksum digit
        */
        barcode.data = "01234567890"

        // Identcode Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.n = 3.0f

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testLEITCODE(canvas: Canvas) {
        val barcode = Leitcode()

        /*
           Leitcode Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           Leitcode Valid data length: 13 digits only, excluding the last checksum digit
        */
        barcode.data = "0123456789012"

        // Leitcode Wide Narrow bar Ratio
        // Valid value is from 2.0 to 3.0 inclusive.
        barcode.n = 3.0f

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testMSI(canvas: Canvas) {
        val barcode = MSI()

        /*
           MSI Plessey Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
        */
        barcode.data = "112233445566"

        barcode.checksumType = MSI.CHECKSUM_AUTO

        // addCheckSum is for MSI.CHECKSUM_AUTO,
        // not valid for  CHECKSUM_10, CHECKSUM_11, CHECKSUM_1010, CHECKSUM_1110.
        barcode.isAddCheckSum = true

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testONECODE(canvas: Canvas) {
        val barcode = Onecode()

        /*
           USPS OneCode Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           USPS OneCode Valid data length: 20, 25, 29, 31 digits only
        */
        barcode.data = "01234567890123456789"

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testPLANET(canvas: Canvas) {
        val barcode = Planet()

        /*
           PLANET Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           PLANET Valid data length: 11 or 13 digits only, excluding the last checksum digit
        */
        barcode.data = "01234567890"

        // PLANET Short bar vs high bar ratio
        barcode.shortTallRatio = 0.4f

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testPOSTNET(canvas: Canvas) {
        val barcode = Postnet()

        /*
           POSTNET Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           POSTNET Valid data length: 5, 6, 9, 11 digits only, excluding the last checksum digit
        */
        barcode.data = "01234"

        // POSTNET Short bar vs high bar ratio
        barcode.shortTallRatio = 0.4f

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testRM4SCC(canvas: Canvas) {
        val barcode = RM4SCC()

        /*
           RM4SCC Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)
                A - Z (Uppercase letters)
        */
        barcode.data = "123"

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testUPCA(canvas: Canvas) {
        val barcode = UPCA()

        /*
           UPC-A Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           UPC-A Valid data length: 11 digits only, excluding the last checksum digit
        */
        barcode.data = "01234567890"

        // for UPC-A with supplement data (2 or 5 digits)
        /*
        barcode.setSupData("12");
        // supplement bar height vs bar height ratio
        barcode.setSupHeight(0.8f);
        // space between barcode and supplement barcode (in pixel)
        barcode.setSupSpace(15);
        */

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 45f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 10)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testUPCE(canvas: Canvas) {
        val barcode = UPCE()

        /*
           UPC-E Valid data char set:
                0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (Digits)

           UPC-E Valid data length: 6 digits only, excluding the first UPCE number system and the last checksum digit
        */
        barcode.data = "012345"

        // UPCE number system: 0 or 1
        barcode.upceNumber = 0

        // for UPC-E with supplement data (2 or 5 digits)
        /*
        barcode.setSupData("12");
        // supplement bar height vs bar height ratio
        barcode.setSupHeight(0.8f);
        // space between barcode and supplement barcode (in pixel)
        barcode.setSupSpace(15);
        */

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 45f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 10)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testCODE128(canvas: Canvas) {
        val barcode = Code128()

        /*
           Code 128 Valid data char set:
                all 128 ASCII characters (Char from 0 to 127)
        */
        barcode.data = "112233445566"

        //  Set the processTilde property to true, if you want use the tilde character "~"
        //  to specify special characters in the input data. Default is false.
        //  1) All 128 ISO/IEC 646 characters, i.e. characters 0 to 127 inclusive, in accordance with ISO/IEC 646.
        //       NOTE This version consists of the G0 set of ISO/IEC 646 and the C0 set of ISO/IEC 6429 with values 28 - 31
        //       modified to FS, GS, RS and US respectively.
        //  2) Characters with byte values 128 to 255 may also be encoded.
        //  3) 4 non-data function characters.
        //  4) 4 code set selection characters.
        //  5) 3 Start characters.
        //  6) 1 Stop character.
        barcode.isProcessTilde = false

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }


    @Throws(Exception::class)
    private fun testEAN128(canvas: Canvas) {
        val barcode = EAN128()

        /*
           GS1-128 / EAN-128 Valid data char set:
                all 128 ASCII characters (Char from 0 to 127)

           to encode Application Identifier (AI), add "()" around the AI code, and followed by the AI data
           such as: (00)350123451234567894(21)01234567
        */
        barcode.data = "(00)350123451234567894"

        //  Set the processTilde property to true, if you want use the tilde character "~"
        //  to specify special characters in the input data. Default is false.
        //  1) All 128 ISO/IEC 646 characters, i.e. characters 0 to 127 inclusive, in accordance with ISO/IEC 646.
        //       NOTE This version consists of the G0 set of ISO/IEC 646 and the C0 set of ISO/IEC 6429 with values 28 - 31
        //       modified to FS, GS, RS and US respectively.
        //  2) Characters with byte values 128 to 255 may also be encoded.
        //  3) 4 non-data function characters.
        //  4) 4 code set selection characters.
        //  5) 3 Start characters.
        //  6) 1 Stop character.
        barcode.isProcessTilde = true

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 1f
        // barcode bar module height (Y) in pixel
        barcode.y = 75f

        // barcode image margins
        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f

        // barcode image resolution in dpi
        barcode.resolution = 72

        // disply barcode encoding data below the barcode
        barcode.isShowText = true
        // barcode encoding data font style
        barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 12)
        // space between barcode and barcode encoding data
        barcode.textMargin = 6f
        barcode.textColor = AndroidColor.black

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testDataMatrix(canvas: Canvas) {
        val barcode = DataMatrix()

        /*
           Data Matrix Valid data char set:
                ASCII values 0 - 127 in accordance with the US national version of ISO/IEC 646
	            ASCII values 128 - 255 in accordance with ISO 8859-1. These are referred to as extended ASCII.

        */
        barcode.data = "112233445566"

        barcode.dataMode = DataMatrix.M_AUTO

        // if your selected format mode doesnot have enough space to encode your data,
        // the library will choose the right format mode for you automatically.
        barcode.formatMode = DataMatrix.F_10X10

        //  Set the processTilde property to true, if you want use the tilde character "~" to specify special characters in the input data. Default is false.
        //  1-byte character: ~ddd (character value from 0 ~ 255)
        //  ASCII (with EXT): from ~000 to ~255
        //  2-byte character: ~6ddddd (character value from 0 ~ 65535)
        //  Unicode: from ~600000 to ~665535
        //  ECI: from ~7000000 to ~7999999
        barcode.isProcessTilde = true

        //  if you want to encode GS1 compatible Data Matrix, you need set FNC1 mode to IBarcode.FNC1_ENABLE
        barcode.fnc1Mode = IBarcode.FNC1_NONE

        // Unit of Measure, pixel, cm, or inch
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode bar module width (X) in pixel
        barcode.x = 3f

        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f
        // barcode image resolution in dpi
        barcode.resolution = 72

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testPDF417(canvas: Canvas) {
        val barcode = PDF417()

        /*
           PDF-417 Valid data char set:
               1. Text Compaction mode permits all printable ASCII characters to be encoded,
                i.e. values 32 - 126 inclusive in accordance with ISO/IEC 646 (IRV),
                as well as selected control characters.
               2. Byte Compaction mode permits all 256 possible 8-bit byte values to be encoded.
                This includes all ASCII characters value 0 to 127 inclusive and provides for international character set support.
               3. Numeric Compaction mode permits efficient encoding of numeric data strings.
               4. Up to 811 800 different character sets or data interpretations.
               5. Various function codewords for control purposes.
        */
        barcode.data = "112233445566"

        // PDF 417 Error Correction Level
        barcode.ecl = PDF417.ECL_2
        barcode.rowCount = 30
        barcode.columnCount = 5
        barcode.dataMode = PDF417.M_AUTO

        barcode.isTruncated = false

        //  Set the processTilde property to true, if you want use the tilde character "~" to specify special characters in the input data. Default is false.
        //  1-byte character: ~ddd (character value from 0 ~ 255)
        //  ASCII (with EXT): from ~000 to ~255
        //  2-byte character: ~6ddddd (character value from 0 ~ 65535)
        //  Unicode: from ~600000 to ~665535
        //  ECI: from ~7000000 to ~7999999
        barcode.isProcessTilde = true

        /*
        // for macro PDF 417
        barcode.setMacro(false);
        barcode.setMacroSegmentIndex(0);
        barcode.setMacroSegmentCount(0);
        barcode.setMacroFileIndex(0);
        */

        // unit of measure for X, Y, LeftMargin, RightMargin, TopMargin, BottomMargin
        barcode.uom = IBarcode.UOM_PIXEL
        // barcode module width in pixel
        barcode.x = 1f
        barcode.xtoYRatio = 0.3f

        barcode.leftMargin = 10f
        barcode.rightMargin = 10f
        barcode.topMargin = 10f
        barcode.bottomMargin = 10f
        // barcode image resolution in dpi
        barcode.resolution = 72

        // barcode bar color and background color in Android device
        barcode.foreColor = AndroidColor.black
        barcode.backColor = AndroidColor.white

        /*
        specify your barcode drawing area
	    */
        val bounds = RectF(30f, 30f, 0f, 0f)
        barcode.drawBarcode(canvas, bounds)
    }

    @Throws(Exception::class)
    private fun testQRCode(canvas: Canvas) {

        if (code.length > 14){
            val barcode = Interleaved25()
            val scale: Int = 10

            barcode.data = code

            // Unit of Measure, pixel, cm, or inch
            barcode.uom = IBarcode.UOM_PIXEL
            // barcode bar module width (X) in pixel
            barcode.x = screenW.toFloat()/200
            // barcode bar module height (Y) in pixel
            barcode.y = screenH.toFloat()/2

            // barcode image margins
            barcode.leftMargin = 2f * scale
            barcode.rightMargin = 2f * scale
            barcode.topMargin = 2f * scale
            barcode.bottomMargin = 2f * scale

            // barcode image resolution in dpi
            barcode.resolution = 72

            // disply barcode encoding data below the barcode
            barcode.isShowText = true
            // barcode encoding data font style
            barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 6 * scale)
            // space between barcode and barcode encoding data
            barcode.textMargin = 1.8f * scale
            barcode.textColor = AndroidColor.black

            // barcode bar color and background color in Android device
            barcode.foreColor = AndroidColor.black
            barcode.backColor = AndroidColor.white

            /*
        specify your barcode drawing area
            */
            val bounds = RectF(30f, 30f, 0f, 0f)
            barcode.drawBarcode(canvas, bounds)

        }else {
            val barcode = EAN13()
            val scale: Int = 10

            barcode.data = code.substring(0, code.length - 1)

            // Unit of Measure, pixel, cm, or inch
            barcode.uom = IBarcode.UOM_PIXEL
            // barcode bar module width (X) in pixel
            barcode.x = screenW.toFloat()/180
            // barcode bar module height (Y) in pixel
            barcode.y = screenH.toFloat()/2

            // barcode image margins
            barcode.leftMargin = 2f * scale
            barcode.rightMargin = 2f * scale
            barcode.topMargin = 2f * scale
            barcode.bottomMargin = 2f * scale

            // barcode image resolution in dpi
            barcode.resolution = 72

            // disply barcode encoding data below the barcode
            barcode.isShowText = true
            // barcode encoding data font style
            barcode.textFont = AndroidFont("Arial", Typeface.NORMAL, 6 * scale)
            // space between barcode and barcode encoding data
            barcode.textMargin = 1.8f * scale
            barcode.textColor = AndroidColor.black

            // barcode bar color and background color in Android device
            barcode.foreColor = AndroidColor.black
            barcode.backColor = AndroidColor.white

            val bounds = RectF(30f, 30f, 0f, 0f)
            barcode.drawBarcode(canvas, bounds)
        }

        Log.e("width", screenW.toString())
        Log.e("height", screenH.toString())
    }

}
