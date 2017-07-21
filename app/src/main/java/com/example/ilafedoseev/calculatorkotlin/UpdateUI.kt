package com.example.ilafedoseev.calculatorkotlin

import android.app.Activity
import android.content.Context
import android.widget.Button
import android.widget.TextView
import java.util.*

/**
 * Created by ilafedoseev on 20/07/2017.
 */

open class UpdateUI : Activity() {

    val calculate : Calculate = Calculate()
    var fbm : FeedBackManager = FeedBackManager()

    /** Get empty : array , TextView number, TextView cacheNumber */
    fun clearAll(number: TextView, cacheNumber: TextView, arr: ArrayList<String>) {
        number.text = "0"
        cacheNumber.text ="0"
        arr.clear()
    }

    /** Print result to TextView Number and change TextView cacheNumber */
    fun printResult(number: TextView, cacheNumber: TextView, arr: ArrayList<String>, context: Context) {
        if (arr.size < 1) {
            return
        } else {
            if(getText(number).contains("/ 0")){
                fbm.canNotDoThat(cacheNumber,"Can't divide by 0", context)
            } else {
                val resultCalculate: String = calculate.calculate(arr)

                updateText(cacheNumber, calculate.makeArrayToStr(arr))
                updateText(number, resultCalculate)

                arr.clear()
                arr.add(resultCalculate)
            }
        }
    }

    /** Add symbols to TextView number */
    fun addSymbols(button : Button, number: TextView, arr: ArrayList<String>, context: Context) {
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
      fbm.btnVibration(context)
    }

    /** Add text number to TextView number */
    fun addNumber(button : Button, number: TextView, arr: ArrayList<String>,context: Context) {
        val buttonNumberStr = getBtnText(button)

        if (number.text.toString() == "0") {
            arr.add(buttonNumberStr)
            updateText(number,calculate.makeArrayToStr(arr))
        } else {
            arr.add(buttonNumberStr)
            updateText(number,calculate.makeArrayToStr(arr))
        }
        fbm.btnVibration(context)
    }

    /** Print percent result to TextView number and change TextView cacheNumber */
    fun printPercent(number: TextView, cacheNumber: TextView, arr: ArrayList<String>, context: Context) {
        if (arr.size == 0 || calculate.makeArrayToStr(arr).contains("/ 0")) {
            fbm.canNotDoThat(cacheNumber,"Error", context)
        } else {
            calculate.percent(calculate.calculate(arr))
            arr.clear()
            arr.add(calculate.percent(getText(number)))
        }
    }

    /** Print factorial result to TextView number and change TextView cacheNumber */
    fun printFactorial(number: TextView, cacheNumber: TextView, arr: ArrayList<String>, context: Context) {
        if(calculate.calculate(arr).toDouble() % 1.0 != 0.0 || arr.size == 0 || calculate.makeArrayToStr(arr).contains("/ 0")) {
            fbm.canNotDoThat(cacheNumber,"Error", context)
        } else {
            updateText(number, calculate.calculate(arr))
            updateText(cacheNumber,getText(number) + "!")
            var result: Int = 1

            for (i in 1..number.text.toString().toInt()) {
                result *= i
            }

            arr.clear()
            updateText(number, result.toString())
            arr.add(result.toString())
        }
    }

    /** Print function result to TextView number and change TextView cacheNumber */
    fun printFunction(button: Button, number: TextView, cacheNumber: TextView, arr: ArrayList<String>, context: Context) {
        if(arr.size == 0 || calculate.makeArrayToStr(arr).contains("/ 0")) {
            fbm.canNotDoThat(cacheNumber,"Error", context)
        } else {
            updateText(number, calculate.calculate(arr))

            when(button.text.toString()) {
                "sin" -> cacheNumber.text = "sin(${getText(number)})"
                "cos" -> cacheNumber.text = "cos(${getText(number)})"
                "tan" -> cacheNumber.text = "tan(${getText(number)})"
            }

            updateText(number,calculate.functionIndecate(getText(number), button.text.toString()))
            arr.clear()
            arr.add(number.text.toString())
        }
    }

    /** Print log result to TextView number and change TextView cacheNumber */
    fun printLog(number: TextView, cacheNumber: TextView, arr: ArrayList<String>, context: Context) {
        if(arr.size != 0 || !calculate.makeArrayToStr(arr).contains("/ 0")) {
            fbm.canNotDoThat(cacheNumber,"Error", context)
        } else {
            cacheNumber.text = "log(${getText(number)})"
            updateText(number, calculate.calculate(arr))
            updateText(number, calculate.log(getText(number)))

            arr.clear()
            arr.add(getText(number))
        }
    }

    /** Change value of number */
    fun printValueNumber(number: TextView, arr: ArrayList<String>) {
        if (getText(number) == "0") {
            return
        } else if(arr[0] == "-"){
            arr.removeAt(0)
        } else {
            arr.add(0,"-")
        }
        updateText(number, calculate.makeArrayToStr(arr))
    }

    /** Set TextView */
    private fun updateText(textView : TextView, str : String) {
        textView.text = str
    }

    /** Get TextView */
    private fun getText(textView: TextView) : String {
        return textView.text.toString()
    }

    /** Get text of Button */
    private fun getBtnText(btn: Button) : String {
        return btn.text.toString()
    }
}