@file:Suppress("UNUSED_EXPRESSION")

package com.example.ilafedoseev.calculatorkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.*
import android.util.TypedValue
import android.os.Vibrator
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.lang.Math.toDegrees

class MainActivityK : AppCompatActivity() {

    var arrayExample : ArrayList<String> = ArrayList<String>()
    var calcul : Calculate = Calculate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // save instance
        setNumberText(savedInstanceState.getString("number"))
        arrayExample = savedInstanceState.getStringArrayList("arrayExample")
        old_numbers.text = savedInstanceState.getString("old_number")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save instance
        outState.putStringArrayList("arrayExample", arrayExample)
        outState.putString("number", number.text.toString())
        outState.putString("old_number", old_numbers.text.toString())
    }

    /** AC button */
    fun clearAllNumbers(view: View) {

        //change "number" and "old_number"
        setNumberText("0")
        old_numbers.text = "0"

        //clean array to empty
        arrayExample.clear()
    }

    /** Click equals */
    fun equalButtonDisplay(view: View) {
        //check array empty
         if (arrayExample.size < 1) {
            return
        } else {
            if(getNumberText().contains("/ 0")){
                old_numbers.text = "Can't divide by 0"
                failVibration()
            } else {
                var resultCalc: String = calcul.calculate(arrayExample)

                //clean array to empty
                old_numbers.text = calcul.makeArrayToStr(arrayExample)
                setNumberText(resultCalc)

                // remove all Strings in array
                arrayExample.clear()

                // ann number of rusult to array
                arrayExample.add(resultCalc)
            }
        }
    }

    /** Click any Symbols */
    fun calcButtonClick(view : View) {
        //display calcButton to "number" view
        var buttonCalc = view as Button

        //check equals number to 0
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
                arrayExample.add(buttonCalc.text.toString())
                setNumberText(calcul.makeArrayToStr(arrayExample))
            }

            // click vibration
            vibration()
        }
    }

    /** Click any number */
    fun numberButtonClick(view: View) {

        //display number to "number" view
        var buttonNumber = view as Button
        var buttonNumberStr = buttonNumber.text.toString()

        //add click number to array
        if (number.text.toString().equals("0")) {
            arrayExample.add(buttonNumberStr)
            setNumberText(calcul.makeArrayToStr(arrayExample))
        } else {
            arrayExample.add(buttonNumberStr)
            setNumberText(calcul.makeArrayToStr(arrayExample))
        }

        //active vibration
        vibration()
    }

    /** Percent calc */
    fun pressPercent(view: View) {
        if (arrayExample.size == 0 || calcul.makeArrayToStr(arrayExample).contains("/ 0")) {
            // print error and vibration
            old_numbers.text = "Error"
            failVibration()
        } else {
            //print to result
            setNumberText(calcul.percent(calcul.calculate(arrayExample)))
            arrayExample.clear()
            arrayExample.add(calcul.percent(getNumberText()))
        }
    }

    /** Factorial */
    fun factorial(view: View) {
        // check full number and not empty array and div to zero
        if(calcul.calculate(arrayExample).toDouble() % 1.0 != 0.0 || arrayExample.size == 0 || calcul.makeArrayToStr(arrayExample).contains("/ 0")) {
            // print error and vibration
            old_numbers.text = "Error"
            failVibration()
        } else {
            //print to result
            setNumberText(calcul.calculate(arrayExample))
            old_numbers.text = getNumberText() + "!"
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
    fun function(view: View) {
        if(arrayExample.size == 0 || calcul.makeArrayToStr(arrayExample).contains("/ 0")) {
            // print error and vibration
            old_numbers.text = "Error"
            failVibration()
        } else {
            //print to result
            setNumberText(calcul.calculate(arrayExample))
            var button = view as Button

            when(button.text.toString()) {
                "sin" -> old_numbers.text = "sin(${getNumberText()})"
                "cos" -> old_numbers.text = "cos(${getNumberText()})"
                "tan" -> old_numbers.text = "tan(${getNumberText()})"
            }

            setNumberText(calcul.functionIndecate(getNumberText(), button.text.toString()))
            arrayExample.clear()
            arrayExample.add(number.text.toString())
        }
    }

    /** Press button log */
    fun pressLog(view: View) {

        if(arrayExample.size == 0 || calcul.makeArrayToStr(arrayExample).contains("/ 0")) {
            // print error and vibration
            old_numbers.text = "Error"
            failVibration()
        } else {
            //print to result
            old_numbers.text = "log(${getNumberText()})"
            setNumberText(calcul.calculate(arrayExample))
            setNumberText(calcul.log(getNumberText()))

            arrayExample.clear()
            arrayExample.add(getNumberText())
        }
    }

    /** Change number to minus and plus */
    fun change(view: View) {
        if (getNumberText() == "0") {
            return
        } else if(arrayExample[0] == "-"){
            arrayExample.removeAt(0)

        } else {
            arrayExample.add(0,"-")
        }
        setNumberText(calcul.makeArrayToStr(arrayExample))
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
    fun failVibration() {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 50, 100, 200)
        v.vibrate(vibrationPattern, -1);
    }

}
