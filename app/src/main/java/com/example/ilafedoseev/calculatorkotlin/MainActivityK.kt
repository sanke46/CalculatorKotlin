@file:Suppress("UNUSED_EXPRESSION")

package com.example.ilafedoseev.calculatorkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivityK : AppCompatActivity() {

    var arrayExample : ArrayList<String> = ArrayList<String>()
    val calculate : Calculate = Calculate()
    val update : UpdateUI = UpdateUI()


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fb : FeedBackManager = FeedBackManager()
    }

    override fun onRestoreInstanceState(savedInstanceState : Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setNumberText(savedInstanceState.getString("number"))
        arrayExample = savedInstanceState.getStringArrayList("arrayExample")
        cacheNumber.text = savedInstanceState.getString("cacheNumber")
    }

    override fun onSaveInstanceState(outState : Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("arrayExample", arrayExample)
        outState.putString("number", number.text.toString())
        outState.putString("cacheNumber", cacheNumber.text.toString())
    }

    /** AC button */
    fun clearAllButton(view : View) {
        update.clearAll(number,cacheNumber,arrayExample)
    }

    /** Click equals */
    fun equalButton(view : View) {
        update.printResult(number,cacheNumber,arrayExample)
    }

    /** Click any Symbols */
    fun mathSymbolButton(view : View) {
        val button = view as Button
        update.addSymbols(button, number, arrayExample)
    }

    /** Click any number */
    fun numberButton(view : View) {
        val buttonNumber = view as Button
        val buttonNumberStr = buttonNumber.text.toString()

        if (number.text.toString() == "0") {
            arrayExample.add(buttonNumberStr)
            setNumberText(calculate.makeArrayToStr(arrayExample))
        } else {
            arrayExample.add(buttonNumberStr)
            setNumberText(calculate.makeArrayToStr(arrayExample))
        }
//        feedBackManager.vibration()
    }

    /** Percent calc */
    fun percentButton(view : View) {
        if (arrayExample.size == 0 || calculate.makeArrayToStr(arrayExample).contains("/ 0")) {
            cacheNumber.text = "Error"
//            feedBackManager.ErrorVibration()
        } else {
            setNumberText(calculate.percent(calculate.calculate(arrayExample)))
            arrayExample.clear()
            arrayExample.add(calculate.percent(getNumberText()))
        }
    }

    /** Factorial */
    fun factorialButton(view : View) {
        if(calculate.calculate(arrayExample).toDouble() % 1.0 != 0.0 || arrayExample.size == 0 || calculate.makeArrayToStr(arrayExample).contains("/ 0")) {
            cacheNumber.text = "Error"
//            feedBackManager.ErrorVibration()
        } else {
            setNumberText(calculate.calculate(arrayExample))
            cacheNumber.text = getNumberText() + "!"
            var result: Int = 1

            for (i in 1..number.text.toString().toInt()) {
                result *= i
            }

            arrayExample.clear()
            setNumberText(result.toString())
            arrayExample.add(result.toString())
        }
    }

    /** Function : sin, cos, tan  */
    fun functionsButton(view : View) {
        if(arrayExample.size == 0 || calculate.makeArrayToStr(arrayExample).contains("/ 0")) {
            cacheNumber.text = "Error"
//            feedBackManager.ErrorVibration()
        } else {
            setNumberText(calculate.calculate(arrayExample))
            var button = view as Button

            when(button.text.toString()) {
                "sin" -> cacheNumber.text = "sin(${getNumberText()})"
                "cos" -> cacheNumber.text = "cos(${getNumberText()})"
                "tan" -> cacheNumber.text = "tan(${getNumberText()})"
            }

            setNumberText(calculate.functionIndecate(getNumberText(), button.text.toString()))
            arrayExample.clear()
            arrayExample.add(number.text.toString())
        }
    }

    /** Press button log */
    fun logButton(view : View) {
        if(arrayExample.size == 0 || calculate.makeArrayToStr(arrayExample).contains("/ 0")) {

        } else {
            cacheNumber.text = "log(${getNumberText()})"
            setNumberText(calculate.calculate(arrayExample))
            setNumberText(calculate.log(getNumberText()))

            arrayExample.clear()
            arrayExample.add(getNumberText())
        }
    }

    /** Change number to minus and plus */
    fun changeValueButton(view : View) {
        if (getNumberText() == "0") {
            return
        } else if(arrayExample[0] == "-"){
            arrayExample.removeAt(0)
        } else {
            arrayExample.add(0,"-")
        }
        setNumberText(calculate.makeArrayToStr(arrayExample))
    }

    /** Change number text */
    fun setNumberText(text : String) {
        number.text = text
    }

    /** Get number text  */
    fun getNumberText() : String {
        return number.text.toString()
    }

}
