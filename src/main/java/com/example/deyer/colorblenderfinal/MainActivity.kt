package com.example.deyer.colorblenderfinal

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.SurfaceView
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.Exception
import java.lang.IllegalStateException
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    var color1 = arrayListOf(0, 0, 0)
    var color2 = arrayListOf(0, 0, 0)
    var color3 = arrayListOf(0, 0, 0)
    var textValue = 0
    var a : Boolean? = false
    var b :Boolean? = false
    //val black = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        val button = findViewById<Button>(R.id.buttonA)
        button.setOnClickListener {
            val intent = Intent(this, ColorBlender::class.java)
            startActivityForResult(intent, 1)

            a = true

        }


        val buttonB = findViewById<Button>(R.id.buttonB)
        buttonB.setOnClickListener {
            val intent = Intent(this, ColorBlender::class.java)
            startActivityForResult(intent, 1)

            b = true

        }


        var mid = findViewById<Button>(R.id.blendedColor)
        mid.setOnClickListener {
            Log.i("What: ", "DIDN'T WORK")
            mid.setBackgroundColor(color1[1])

        }

    } // End OnCreate

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 1) {

            if (resultCode == Activity.RESULT_OK) {

                if (a == true) {

                    val result = data!!.getIntegerArrayListExtra("result")
                    color1 = result


                    a = false
                    val mid = this.findViewById<Button>(R.id.blendedColor)
                    mid.setBackgroundColor(Color.rgb(result[0], result[1], result[2]))

                    Log.i("INFO1z: ", result.toString())

                    var seekBar = this.findViewById<SeekBar>(R.id.seekBar1)
                    var colorVal = this.findViewById<TextView>(R.id.colorA)

                    colorVal.text = color1.toString()

                    seekBar.setProgress(0)


                }
                else if (b == true){
                    val result = data!!.getIntegerArrayListExtra("result")
                    color2 = result

                    b = false
                    var mid = this.findViewById<Button>(R.id.blendedColor)
                    mid.setBackgroundColor(Color.rgb(result[0], result[1], result[2]))

                    var seekBar1 = this.findViewById<SeekBar>(R.id.seekBar1)
                    var colorVal = this.findViewById<TextView>(R.id.colorB)

                    colorVal.text = color2.toString()

                    seekBar1.setProgress(100)

                }






            }

            /*if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }*/

            //Here I use the progress bar to determine what color should be displayed
            //If the progress bar all the way to the right, display the b button respectfully of the color
            //if in the middle, use the progress bar to determine what color between the two colors to display



            var seekBar = findViewById<SeekBar>(R.id.seekBar1)
            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                               fromUser: Boolean) {
                    if (progress == 0){
                        var finalText = findViewById<TextView>(R.id.finalTextView)
                        finalText.setText(color1.toString())

                        var mid = findViewById<Button>(R.id.blendedColor)
                        mid.setBackgroundColor(Color.rgb(color1[0], color1[1], color1[2]))
                    }else if(progress == 100){
                        var finalText = findViewById<TextView>(R.id.finalTextView)
                        finalText.setText(color2.toString())

                        var mid = findViewById<Button>(R.id.blendedColor)
                        mid.setBackgroundColor(Color.rgb(color2[0], color2[1], color2[2]))
                    }
                    else{
                        color3[0] = (((color1[0]  + color2[0]) * progress) / 100)// * progress
                        color3[1] = (((color1[1]  + color2[1]) * progress) / 100) //* progress
                        color3[2] = (((color1[2]  + color2[2]) * progress) / 100) //* progress


                        var finalText = findViewById<TextView>(R.id.finalTextView)
                        finalText.setText(color3.toString())

                        var mid = findViewById<Button>(R.id.blendedColor)
                        mid.setBackgroundColor(Color.rgb(color3[0], color3[1], color3[2]))

                    }
                }
            })
        }
    }
}

