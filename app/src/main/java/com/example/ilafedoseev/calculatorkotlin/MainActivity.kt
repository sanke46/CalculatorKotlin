@file:Suppress("UNUSED_EXPRESSION")

package com.example.ilafedoseev.calculatorkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import org.jetbrains.anko.toast
import java.util.*
import kotlinx.android.synthetic.main.activity_main.number
import kotlinx.android.synthetic.main.activity_main.old_numbers
import java.text.DecimalFormat
import android.util.TypedValue
import android.widget.HorizontalScrollView





class MainActivity : AppCompatActivity() {

    var arrayExample : ArrayList<String> = ArrayList<String>()
    var tokenList : ArrayList<String> = ArrayList<String>()
    var resultNumeberOfCalc : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val scrollView = findViewById(R.id.scroll) as HorizontalScrollView
//        scrollView.postDelayed({ scrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT) }, 10L)
    }

    fun actionCalculation() {
        //str to new array token

        //display calc of "number"
        for (s in arrayExample){

        }
    }

    fun makeArrayToStr(arr: ArrayList<String>): String {
        var str : StringBuilder = StringBuilder()

        for (s in arr) {
            str.append(s)
        }

        return str.toString()
    }

    fun clearAllNumbers(view: View) {
        //clean array to empty
        arrayExample.clear()
        number.text = "0"
        old_numbers.text = "0"
        testSizeCalc()
    }

    fun equalButtonDisplay(view: View) {
        // define what action do
        var res : Int = 0
        var strToResult : String = makeArrayToStr(arrayExample)
        var arrNumbers : List<String>

        //click equals -> change number to result
        arrayExample.forEach { token ->
            if (arrayExample.contains(".")) {
                when {
                    token.equals("-") -> {
                        arrNumbers = strToResult.split("-")

                        if(arrNumbers[1].length > 6 || arrNumbers[1].length > 6) {
                            number.text = (Math.round(arrNumbers[0].toDouble().minus(arrNumbers[1].toDouble()) * 10000.0) / 10000.0).toString();
                        } else {
                            number.text = "${arrNumbers[0].toDouble().minus(arrNumbers[1].toDouble())}"
                        }
                    }

                    token.equals("+") -> {
                        arrNumbers = strToResult.split("+")

                        if(arrNumbers[1].length > 6 || arrNumbers[1].length > 6) {
                            number.text = (Math.round(arrNumbers[0].toDouble().plus(arrNumbers[1].toDouble()) * 10000.0) / 10000.0).toString();
                        } else {
                            number.text = "${arrNumbers[0].toDouble().plus(arrNumbers[1].toDouble())}"
                        }
                    }

                    token.equals("*") -> {
                        arrNumbers = strToResult.split("*")

                        if(arrNumbers[1].length > 6 || arrNumbers[1].length > 6) {
                            number.text = (Math.round(arrNumbers[0].toDouble().times(arrNumbers[1].toDouble()) * 10000.0) / 10000.0).toString();
                        } else {
                            number.text = "${arrNumbers[0].toDouble().times(arrNumbers[1].toDouble())}"
                        }
                    }

                    token.equals("/") -> {
                        arrNumbers = strToResult.split("/")
                        //divide by zero
                        if (arrNumbers[1].toInt() == 0) {
                            old_numbers.text = "Can't devide by 0"
                        } else {
                            if(arrNumbers[1].length > 6 || arrNumbers[1].length > 6) {
                                number.text = (Math.round(arrNumbers[0].toDouble().div(arrNumbers[1].toDouble()) * 10000.0) / 10000.0).toString();
                            } else {
                                number.text = "${arrNumbers[0].toDouble().div(arrNumbers[1].toDouble())}"
                            }
                        }
                    }
                }
            } else {
                when {
                    token.equals("-") -> {
                        arrNumbers = strToResult.split("-")
                        number.text = "${arrNumbers[0].toInt().minus(arrNumbers[1].toInt())}"
                    }

                    token.equals("+") -> {
                        arrNumbers = strToResult.split("+")
                        number.text = "${arrNumbers[0].toInt().plus(arrNumbers[1].toInt())}"
                    }

                    token.equals("*") -> {
                        arrNumbers = strToResult.split("*")
                        number.text = "${arrNumbers[0].toInt().times(arrNumbers[1].toInt())}"
                    }

                    token.equals("/") -> {
                        arrNumbers = strToResult.split("/")
                        //divide by zero
                        if (arrNumbers[1].toInt() == 0) {
                            old_numbers.text = "Can't devide by 0"
                        } else {
                            number.text = "${(arrNumbers[0].toInt().div(arrNumbers[1].toInt()))}"
                        }
                    }
                }
            }
        }

        //clean array to empty
        arrayExample.clear()

        // ann number of rusult to array
        arrayExample.add(number.text.toString())

    }

    fun calcButtonClick (view : View) {
        //display calcButton to "number" view
        var buttonCalc = view as Button
        // add only one symbol
        if (arrayExample[arrayExample.size - 1].equals("-") ||
                arrayExample[arrayExample.size - 1].equals("+") ||
                arrayExample[arrayExample.size - 1].equals("/") ||
                arrayExample[arrayExample.size - 1].equals("*") ||
                arrayExample[arrayExample.size - 1].equals(".")){
            return
        } else {
            number.text = number.text.toString().plus(buttonCalc.text.toString())
        }


        //add calcButton to array
        arrayExample.add(buttonCalc.text.toString())
        println(testArray(arrayExample))
        testSizeCalc()
    }

    fun numberButtonClick(view: View){
        //display number to "number" view
        var buttonNumber = view as Button


        //add click number to array
            if (number.text.toString().equals("0")) {
                number.text = buttonNumber.text.toString()
                arrayExample.add(buttonNumber.text.toString())
            } else {
                number.text = number.text.toString().plus(buttonNumber.text.toString())
                arrayExample.add(buttonNumber.text.toString())
            }

            testSizeCalc()
        }


    fun testArray(arr: ArrayList<String>) : String {
        var str :StringBuilder = StringBuilder()

        for (s in arr){
            str.append(s)
        }

        return str.toString()
    }

    fun pressPercent(view: View){
        var procentResult = number.text.toString().toDouble().div(100)
        number.text = procentResult.toString()
    }

    fun testSizeCalc() {
        if (number.text.toString().length > 9) {
           number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 65F)
        }

        if (number.text.toString().length > 12) {
            number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 55F)
        }

        if (number.text.toString().length < 1) {
            number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 75F)
        }
    }


}
