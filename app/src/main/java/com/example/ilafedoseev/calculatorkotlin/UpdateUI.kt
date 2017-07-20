package com.example.ilafedoseev.calculatorkotlin

import android.app.Activity
import android.widget.Button
import android.widget.TextView
import java.util.*


/**
 * Created by ilafedoseev on 20/07/2017.
 */

class UpdateUI : Activity() {

    val calculate : Calculate = Calculate()
    var feedBackManager : FeedBackManager = FeedBackManager()

    fun clearAll(number: TextView, cacheNumber: TextView, arr: ArrayList<String>) {
        number.text = "0"
        cacheNumber.text ="0"
        arr.clear()
    }

    fun printResult(number: TextView, cacheNumber: TextView, arr: ArrayList<String>) {
        if (arr.size < 1) {
            return
        } else {
            if(getText(number).contains("/ 0")){
                feedBackManager.canNotDoThat(cacheNumber,"Can't divide by 0")
            } else {
                val resultCalculate: String = calculate.calculate(arr)

                updateText(cacheNumber, calculate.makeArrayToStr(arr))
                updateText(number, resultCalculate)

                arr.clear()
                arr.add(resultCalculate)
            }
        }
    }

    fun addSymbols(button : Button, number: TextView, arr: ArrayList<String>) {
        if (getText(number) != "0") {
            // add only one symbol
            if (arr[arr.size - 1].equals("-") ||
                    arr[arr.size - 1].equals("+") ||
                    arr[arr.size - 1].equals("/") ||
                    arr[arr.size - 1].equals("*") ||
                    arr[arr.size - 1].equals(".")) {
            } else {
                arr.add(getBtnText(button))
                updateText(number,calculate.makeArrayToStr(arr))
            }
        }
      feedBackManager.btnVibration()
    }

    fun addNumber(button : Button, number: TextView, arr: ArrayList<String>) {

    }

    private fun updateText(textView : TextView, str : String) {
        textView.text = str
    }

    private fun getText(textView: TextView) : String {
        return textView.text.toString()
    }
    private fun getBtnText(btn: Button) : String {
        return btn.text.toString()
    }
}