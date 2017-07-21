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

    val calculateService : CalculateService = CalculateService()
    var feedBack: FeedBackManager = FeedBackManager()

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
                feedBack.canNotDoThat(cacheNumber,"Can't divide by 0", context)
            } else {
                val resultCalculate: String = calculateService.calculate(arr)

                updateText(cacheNumber, calculateService.makeArrayToStr(arr))
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
                arr.add(getButtonText(button))
                updateText(number,calculateService.makeArrayToStr(arr))
            }
        }
      feedBack.buttonVibration(context)
    }

    /** Add text number to TextView number */
    fun addNumber(button : Button, number: TextView, result: ArrayList<String>, context: Context) {
        val buttonNumberStr = getButtonText(button)

        if (number.text.toString() == "0") {
            result.add(buttonNumberStr)
            updateText(number,calculateService.makeArrayToStr(result))
        } else {
            result.add(buttonNumberStr)
            updateText(number,calculateService.makeArrayToStr(result))
        }
        feedBack.buttonVibration(context)
    }

    /** Print percent result to TextView number and change TextView cacheNumber */
    fun printPercent(number: TextView, cacheNumber: TextView, result: ArrayList<String>, context: Context) {
        if (result.size == 0 || calculateService.makeArrayToStr(result).contains("/ 0")) {
            feedBack.canNotDoThat(cacheNumber,"Error", context)
        } else {
            calculateService.percent(calculateService.calculate(result))
            result.clear()
            result.add(calculateService.percent(getText(number)))

        }
    }

    /** Print factorial result to TextView number and change TextView cacheNumber */
    fun printFactorial(number: TextView, cacheNumber: TextView, result: ArrayList<String>, context: Context) {
        if(calculateService.calculate(result).toDouble() % 1.0 != 0.0 || result.size == 0 || calculateService.makeArrayToStr(result).contains("/ 0")) {
            feedBack.canNotDoThat(cacheNumber,"Error", context)
        } else {
            updateText(number, calculateService.calculate(result))
            updateText(cacheNumber,getText(number) + "!")
            var resultFactorial: Int = 1

            for (i in 1..number.text.toString().toInt()) {
                resultFactorial *= i
            }

            result.clear()
            updateText(number, resultFactorial.toString())
            result.add(resultFactorial.toString())
        }
    }

    /** Print function result to TextView number and change TextView cacheNumber */
    fun printFunction(button: Button, number: TextView, cacheNumber: TextView, result: ArrayList<String>, context: Context) {
        if(result.size == 0 || calculateService.makeArrayToStr(result).contains("/ 0")) {
            feedBack.canNotDoThat(cacheNumber,"Error", context)
        } else {
            updateText(number, calculateService.calculate(result))

            when(button.text.toString()) {
                "sin" -> cacheNumber.text = "sin(${getText(number)})"
                "cos" -> cacheNumber.text = "cos(${getText(number)})"
                "tan" -> cacheNumber.text = "tan(${getText(number)})"
            }

            updateText(number,calculateService.functionIndecate(getText(number), button.text.toString()))
            result.clear()
            result.add(number.text.toString())
        }
    }

    /** Print log result to TextView number and change TextView cacheNumber */
    fun printLog(number: TextView, cacheNumber: TextView, result: ArrayList<String>, context: Context) {
        if(result.size != 0 || !calculateService.makeArrayToStr(result).contains("/ 0")) {
            feedBack.canNotDoThat(cacheNumber,"Error", context)
        } else {
            cacheNumber.text = "log(${getText(number)})"
            updateText(number, calculateService.calculate(result))
            updateText(number, calculateService.log(getText(number)))

            result.clear()
            result.add(getText(number))
        }
    }

    /** Change value of number */
    fun printValueNumber(number: TextView, result: ArrayList<String>) {
        if (getText(number) == "0") {
            return
        } else if(result[0] == "-"){
            result.removeAt(0)
        } else {
            result.add(0,"-")
        }
        updateText(number, calculateService.makeArrayToStr(result))
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
    private fun getButtonText(button: Button) : String {
        return button.text.toString()
    }
}