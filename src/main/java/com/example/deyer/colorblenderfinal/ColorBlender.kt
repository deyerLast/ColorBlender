package com.example.deyer.colorblenderfinal

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
//import com.example.deyer.colorblender.R.id.*
import kotlinx.android.synthetic.main.colorblender.*

class ColorBlender : AppCompatActivity() {

    val list = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.colorblender)

        var redValue = 0
        var greenValue = 0
        var blueValue = 0


        textVB.setText("Blue")
        textVR.setText("Red")
        textVG.setText("Green")

        //Hard coded so they appear at launch
        rButton.text = "0"
        gButton.text = "0"
        bButton.text = "0"


        colorR.max = 255
        colorR.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {

                redValue = progress
                val colorStr = getColorString()
                //redValue = colorStr.toInt()
                colorSurfaceView.setBackgroundColor(Color.parseColor(colorStr))
                rButton.text= progress.toString()
            }
        })

        colorG.max = 255
        colorG.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {

                greenValue = progress
                val colorStr = getColorString()
                colorSurfaceView.setBackgroundColor(Color.parseColor(colorStr))
                gButton.text = progress.toString()
            }
        })

        colorB.max = 255
        colorB.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {
                blueValue = progress
                val colorStr = getColorString()
                colorSurfaceView.setBackgroundColor(Color.parseColor(colorStr))
                bButton.text = progress.toString()

            }
        })













        //al returnB = findViewById<Button>(R.id.save)
        val returnB = findViewById<Button>(R.id.save)

        val info = intent.extras
        if (info != null) {
            if (info.containsKey("key")) {
                returnB.visibility = View.VISIBLE   //view was errored, check this
            }
        }





        returnB.setOnClickListener {

            System.out.println("        Return Button        ")





            val result1 = arrayListOf(0, 0, 0)
            result1[0] = redValue
            result1[1] = greenValue
            result1[2] = blueValue


            Log.i("info2: ", result1.toString())

            val returnIntent = Intent()
            returnIntent.putIntegerArrayListExtra("result", result1)
            setResult(Activity.RESULT_OK, returnIntent)


            Log.i("ResultString", result1.toString())

            finish()
        }
    }






    //==================================================================================================================

    // Add the rgb hex values to get the color.
    fun getColorString(): String {
        var r = Integer.toHexString(((255*colorR.progress)/colorR.max))
        if(r.length==1) {
            r = "0" + r
        }
        var g = Integer.toHexString(((255*colorG.progress)/colorG.max))
        if(g.length==1) g = "0"+g
        var b = Integer.toHexString(((255*colorB.progress)/colorB.max))
        if(b.length==1) b = "0"+b
        //var number = buttonTextView.setText("#" + r + g + b)
        val hexColorNum = "#" + r + g + b
        buttonTextView.setText(hexColorNum)



        //send data to MainActivity
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("hexColorNum", hexColorNum)



        return hexColorNum
    }

    //==================================================================================================================


}
