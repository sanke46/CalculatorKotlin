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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save instance
        outState.putStringArrayList("arrayExample", arrayExample)
        outState.putString("number", number.text.toString())
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
        //clean array to empty
        arrayExample.clear()
        number.text = "0"
        old_numbers.text = "0"
        tokenList.clear()
        testSizeCalc()
    }

    /** Click equals */
    fun equalButtonDisplay(view: View) {

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

        // active vibration
        vibration()
        // test needed to change size of number
        testSizeCalc()
    }

    /** Percent calc */
    fun pressPercent(view: View) {
        var procentResult = number.text.toString().toDouble().div(100)
        // chance number to result
        number.text = procentResult.toString()
        arrayExample.clear()
        arrayExample.add(procentResult.toString())
    }

    fun fractarial (view: View) {
        old_numbers.text = number.text.toString() +  "!"
        var result : Int = 1

        for (i in 1..number.text.toString().toInt()) {
            result *= i
        }

        arrayExample.clear()
        number.text = result.toString()
        arrayExample.add(result.toString())

    }

    /** sin, cos, tan function */
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
        if (number.text.toString().length > 8) {
           number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 65F)
        }

        //change  size number if length is high 12
        if (number.text.toString().length > 12) {
            number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 55F)
        }

        //change  size number if length is high 1
        if (number.text.toString().length < 1) {
            number.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 75F)
        }
    }

    /** vibration */
    fun vibration () {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 15)
        v.vibrate(vibrationPattern, -1);
    }

    /** division by zero */
    fun failVibration () {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 50, 100, 200)
        v.vibrate(vibrationPattern, -1);
    }

    /** round the number */
    fun roundNumber (num: Double) : Double{
        return Math.round (num * 10000.0) / 10000.0;
    }

    /** pow number */
    fun powNumber () : Double{
        var number =  number.text.toString()
        var splitNumber = number.split("^")
        return Math.pow(splitNumber[0].toDouble(),splitNumber[1].toDouble())
    }

    /** calculate method */
    fun calculate(arr: ArrayList<String>) {
        var currentOp: String = ""
        var tokenListStr : String = makeArrayToStr(arr)
        tokenList = tokenListStr.split(" ") as ArrayList<String>
        var haveDot : Boolean = false

        for (i in arrayExample) {
            if(i.contains(".")) {
                haveDot = true
            }
        }

        if (haveDot) {
            var currentNumber : Double = 0.0

            tokenList.forEach { token ->
                when {
                    token.equals("+") || token.equals("/") || token.equals("*") || token.equals("-") || token.equals("^") -> currentOp = token
                    currentOp.equals("-") -> currentNumber -= token.toDouble()
                    currentOp.equals("/") -> {
                        if (token.equals("0")) {
                            old_numbers.text = "Can't devide by 0"
                        } else {
                            currentNumber /= token.toInt()
                        }
                    }
                    currentOp.equals("^") -> {
                        for (i in 1..token.toInt()-1) {
                            var num = currentNumber
                            currentNumber *= num
                        }
                    }
                    currentOp.equals("*") -> currentNumber *= token.toDouble()
                    else -> currentNumber += token.toDouble()
                }
            }
            // display result
            number.text = currentNumber.toString()
        } else {
            var currentNumber : Double = 0

            tokenList.forEach { token ->

                when {
                    token.equals("+") || token.equals("/") || token.equals("*") || token.equals("-") || token.equals("^") -> currentOp = token

                    // minus
                    currentOp.equals("-") -> currentNumber -= token.()
                    // division
                    currentOp.equals("/") -> {
                        if (token.toInt() == 0) {
                            old_numbers.text = "Can't devide by 0"
                            failVibration()
                            return
                        } else if (token.toInt() > currentNumber) {
                            currentNumber.toDouble() = currentNumber.toDouble().div(token.toDouble())
                        } else {
                            currentNumber /= token.toInt()
                        }
                    }
                    // pow
                    currentOp.equals("^") -> {
                        for (i in 1..token.toInt()-1) {
                            var num = currentNumber
                            currentNumber *= num
                        }
                    }
                    // times
                    currentOp.equals("*") -> currentNumber *= token.toInt()
                    else -> {
                        currentNumber += token.toInt()
                    }
                }
            }
            // display result
            number.text = currentNumber.toString()
        }

        //clean array to empty
        old_numbers.text = tokenListStr

        // remove all Strings in array
        arrayExample.clear()

        // ann number of rusult to array
        arrayExample.add(number.text.toString())
    }


}
