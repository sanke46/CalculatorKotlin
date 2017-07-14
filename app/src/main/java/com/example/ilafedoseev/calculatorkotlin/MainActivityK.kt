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
import java.lang.Math.toDegrees

class MainActivityK : AppCompatActivity() {

    var arrayExample : ArrayList<String> = ArrayList<String>()
    var tokenList : ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // save instance
        number.text = savedInstanceState.getString("number")
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

    /** Method to help convert array to String */
    fun makeArrayToStr(arr: ArrayList<String>) : String {
        var str : StringBuilder = StringBuilder()
        for (s in arr) {
            if(s.equals("-") || s.equals("+") || s.equals("/") || s.equals("*") || s.equals("^")){
                str.append(" " + s + " ")
            } else {
                str.append(s)
            }
        }
        return str.toString()
    }

    /** AC button */
    fun clearAllNumbers(view: View) {
        //change "number" and "old_number"
        number.text = "0"
        old_numbers.text = "0"

        //clean array to empty
        arrayExample.clear()
        tokenList.clear()

        // change size "number" to normal
        testSizeCalc()
    }

    /** Click equals */
    fun equalButtonDisplay(view: View) {
        //check array empty
        if (arrayExample.size < 1) {
            return
        } else {
            calculate(arrayExample)
        }

    }

    /** Click any Symbols */
    fun calcButtonClick (view : View) {
        //display calcButton to "number" view
        var buttonCalc = view as Button

        //check equals number to 0
        if(number.text.toString().equals("0")){
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
                number.text = number.text.toString().plus(buttonCalc.text.toString())
            }

            // click vibration
            vibration()

            //add calcButton to array
            arrayExample.add(buttonCalc.text.toString())

            // test needed to change size of number
            testSizeCalc()
        }
    }

    /** Click any number */
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

        //active vibration
        vibration()

        //test needed to change size of number
        testSizeCalc()
    }

    /** Percent calc */
    fun pressPercent(view: View) {
        calculate(arrayExample)
        var procentResult = number.text.toString().toDouble().div(100)
        // chance number to result
        number.text = procentResult.toString()
        arrayExample.clear()
        arrayExample.add(procentResult.toString())
    }

    /** Factorial */
    fun fractorial (view: View) {
        calculate(arrayExample)
        old_numbers.text = number.text.toString() +  "!"
        var result : Int = 1

        for (i in 1..number.text.toString().toInt()) {
            result *= i
        }

        arrayExample.clear()
        number.text = result.toString()
        arrayExample.add(result.toString())

    }

    /** Function : sin, cos, tan  */
    fun function(view: View) {
        var button = view as Button
        when (button.text.toString()) {
            "sin" -> number.text = roundNumber(Math.sin(number.text.toString().toDouble())).toString()
            "cos" -> number.text = Math.cos(number.text.toString().toDouble()).toString()
            "tan" -> number.text = Math.tan(number.text.toString().toDouble()).toString()
        }
        arrayExample.clear()
        arrayExample.add(number.text.toString())
    }

    /** Change size TextView */
    fun testSizeCalc() {
        //change  size number if length is high 8
//        if (number.text.toString().length > 9) {
//           number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 65F)
//        }
//
//        //change  size number if length is high 12
//        if (number.text.toString().length > 12) {
//            number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 55F)
//        }
//
//        //change  size number if length is high 1
//        if (number.text.toString().length < 1) {
//            number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 75F)
//        }
    }

    /** Vibration */
    fun vibration () {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 15)
        v.vibrate(vibrationPattern, -1);
    }

    /** Division by zero */
    fun failVibration () {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 50, 100, 200)
        v.vibrate(vibrationPattern, -1);
    }

    /** Round the number */
    fun roundNumber (num: Double) : Double{
        return Math.round (num * 10000.0) / 10000.0;
    }

    /** Pow number */
    fun powNumber () : Double{
        var number =  number.text.toString()
        var splitNumber = number.split("^")
        return Math.pow(splitNumber[0].toDouble(),splitNumber[1].toDouble())
    }

    /** Change number to minus and plus */
    fun change(view: View){

    }

    /** Calculate method */
    fun calculate(arr: ArrayList<String>) {
        var currentOp: String = ""
        var currentNumber : Double = 0.0
        var haveDot : Boolean = false

        // sort to clean array
        var tokenListStr : String = makeArrayToStr(arr)
        tokenList = tokenListStr.split(" ") as ArrayList<String>

        // check doe contains in array
        for (i in arrayExample) {
            if(i.contains(".")) {
                haveDot = true
            }
        }

        // loop to calculate
        tokenList.forEach { token ->
            when {
                token.equals("+") || token.equals("/") || token.equals("*") || token.equals("-") || token.equals("^") -> currentOp = token

                //minus
                currentOp.equals("-") -> currentNumber -= token.toDouble()

                //div
                currentOp.equals("/") -> {
                    if (token.equals("0")) {
                        old_numbers.text = "Can't devide by 0"
                        failVibration()
                        return
                    } else if (token.toDouble() > currentNumber) {
                        currentNumber = currentNumber.div(token.toDouble())
                    } else {
                        currentNumber /= token.toInt()
                    }
                }

                //pow
                currentOp.equals("^") -> {
                    for (i in 1..token.toInt()-1) {
                        var num = currentNumber
                        currentNumber *= num
                    }
                }

                //times
                currentOp.equals("*") -> currentNumber *= token.toDouble()

                //plus
                else -> currentNumber += token.toDouble()
            }
        }

        // display result
        if (currentNumber < 1) {
            number.text = roundNumber(currentNumber).toString()
        } else if (haveDot) {
            number.text = currentNumber.toString()
        } else {
            number.text = currentNumber.toInt().toString()
        }

        //clean array to empty
        old_numbers.text = tokenListStr

        // remove all Strings in array
        arrayExample.clear()

        // ann number of rusult to array
        arrayExample.add(number.text.toString())
    }


}
