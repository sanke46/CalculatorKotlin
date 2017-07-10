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

class MainActivity : AppCompatActivity() {

    var arrayExample : ArrayList<String> = ArrayList<String>()
    var tokenList : ArrayList<String> = ArrayList<String>()
    var resultNumeberOfCalc : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }

    fun equalButtonDisplay(view: View) {
        // define what action do
        var res : Int = 0
        var strToResult : String = makeArrayToStr(arrayExample)
        var arrNumbers : List<String>

        //click equals -> change number to result
        arrayExample.forEach { token ->
            when {
                token.equals("-") -> {
                     arrNumbers = strToResult.split("-")
                    number.text = "${arrNumbers[0].toInt().minus(arrNumbers[1].toInt())}" }
                token.equals("+") -> {
                    arrNumbers = strToResult.split("+")
                    number.text = "${arrNumbers[0].toInt().plus(arrNumbers[1].toInt())}" }

                token.equals("*") -> {
                    arrNumbers = strToResult.split("*")
                    number.text = "${arrNumbers[0].toInt().times(arrNumbers[1].toInt())}" }
                token.equals("/") -> {
                    arrNumbers = strToResult.split("/")
                    number.text = "${arrNumbers[0].toInt().div(arrNumbers[1].toInt())}" }
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
        number.text = number.text.toString().plus(buttonCalc.text.toString())

        //add calcButton to array
        arrayExample.add(buttonCalc.text.toString())
        println(testArray(arrayExample))
    }

    fun numberButtonClick(view: View){
        //display number to "number" view
        var buttonNumber = view as Button

        //add click number to array
        if(number.text.toString().equals("0")) {
            number.text = buttonNumber.text.toString()
            arrayExample.add(buttonNumber.text.toString())
        } else {
            number.text = number.text.toString().plus(buttonNumber.text.toString())
            arrayExample.add(buttonNumber.text.toString())
        }
    }


    fun testArray(arr: ArrayList<String>) : String {
        var str :StringBuilder = StringBuilder()

        for (s in arr){
            str.append(s)
        }

        return str.toString()
    }


}
