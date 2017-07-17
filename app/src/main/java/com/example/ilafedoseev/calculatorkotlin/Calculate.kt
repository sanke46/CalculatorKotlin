package com.example.ilafedoseev.calculatorkotlin

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

/**
 * Created by ilafedoseev on 16/07/2017.
 */

class Calculate {
    /** Calculate method */
    fun calculate(arr: ArrayList<String>) : String {
        var currentOp: String = ""
        var currentNumber: Double = 0.0
        var haveDot: Boolean = false

        // sort to clean array
        var tokenListStr: String = makeArrayToStr(arr)
        if(tokenListStr[0].equals(" ")){
            tokenListStr.replace(" ","")
        }
        var tokenList: ArrayList<String> = tokenListStr.split(" ") as ArrayList<String>

        // check doe contains in array
        for (i in arr) {
            if (i.contains(".")) {
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
                        currentNumber = 0.0;
                    } else if (token.toDouble() > currentNumber) {
                        currentNumber = currentNumber.div(token.toDouble())
                    } else {
                        currentNumber /= token.toDouble()
                    }
                }

            //pow
                currentOp.equals("^") -> {
                    for (i in 1..token.toInt() - 1) {
                        var num = currentNumber
                        currentNumber *= num
                    }
                }

            //times
                currentOp.equals("*") -> currentNumber *= token.toDouble()

            //plus
                else -> {
                    if(token.equals("")){

                    } else {
                        currentNumber += token.toDouble()
                    }
                }
            }
        }

        // display result
        if (currentNumber > 0 && currentNumber < 1) {
            return roundNumber(currentNumber).toString()
        } else if (haveDot) {
            if(currentNumber % 1.0 == 0.0) {
                return currentNumber.toInt().toString()
            } else {
                return currentNumber.toString()
            }
        } else {
            return currentNumber.toInt().toString()
        }
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

    /** Round the number */
    fun roundNumber (num: Double) : Double {
        return Math.round (num * 10000.0) / 10000.0;
    }
}