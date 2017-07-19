@file:Suppress("UNUSED_EXPRESSION")

package com.example.ilafedoseev.calculatorkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.*
import android.os.Vibrator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivityK : AppCompatActivity() {

    var arrayExample : ArrayList<String> = ArrayList<String>()
    var calculate : Calculate = Calculate()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    fun clearAllNumbers(view : View) {
        setNumberText("0")
        cacheNumber.text = "0"
        arrayExample.clear()
    }

    /** Click equals */
    fun equalButton(view : View) {
         if (arrayExample.size < 1) {
            return
        } else {
            if(getNumberText().contains("/ 0")){
                cacheNumber.text = "Can't divide by 0"
                ErrorVibration()
            } else {
                var resultCalculate: String = calculate.calculate(arrayExample)

                cacheNumber.text = calculate.makeArrayToStr(arrayExample)
                setNumberText(resultCalculate)

                arrayExample.clear()
                arrayExample.add(resultCalculate)
            }
        }
    }

    /** Click any Symbols */
    fun mathSymbolButton(view : View) {
        var buttonCalculate = view as Button

        if (getNumberText().equals("0")) {
            return
        } else {
            // add only one symbol
            if (arrayExample[arrayExample.size - 1].equals("-") ||
                arrayExample[arrayExample.size - 1].equals("+") ||
                arrayExample[arrayExample.size - 1].equals("/") ||
                arrayExample[arrayExample.size - 1].equals("*") ||
                arrayExample[arrayExample.size - 1].equals(".")) {
                return
            } else {
                arrayExample.add(buttonCalculate.text.toString())
                setNumberText(calculate.makeArrayToStr(arrayExample))
            }
            vibration()
        }
    }

    /** Click any number */
    fun numberButton(view : View) {
        var buttonNumber = view as Button
        var buttonNumberStr = buttonNumber.text.toString()

        if (number.text.toString().equals("0")) {
            arrayExample.add(buttonNumberStr)
            setNumberText(calculate.makeArrayToStr(arrayExample))
        } else {
            arrayExample.add(buttonNumberStr)
            setNumberText(calculate.makeArrayToStr(arrayExample))
        }
        vibration()
    }

    /** Percent calc */
    fun percentButton(view : View) {
        if (arrayExample.size == 0 || calculate.makeArrayToStr(arrayExample).contains("/ 0")) {
            cacheNumber.text = "Error"
            ErrorVibration()
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
            ErrorVibration()
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
            ErrorVibration()
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
            cacheNumber.text = "Error"
            ErrorVibration()
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

    /** Vibration */
    fun vibration() {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 15)
        v.vibrate(vibrationPattern, -1);
    }

    /** Error */
    fun ErrorVibration() {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 50, 100, 200)
        v.vibrate(vibrationPattern, -1);
    }

}
