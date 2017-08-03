package com.example.ilafedoseev.calculatorkotlin

import java.util.*

@Suppress("UNREACHABLE_CODE")
/**
 * Created by ilafedoseev on 16/07/2017.
 */

class CalculateService {
    var haveDot : Boolean = false

    /** Calculate method */
    fun calculate(arr: ArrayList<String>) : String {

        var tokenStr : String = makeArrayToStr(arr)

        if (tokenStr[0].equals(" ")) {
            tokenStr.replace(" ", "")
        }

        var tokenArrayList : ArrayList<String> = tokenStr.split(" ") as ArrayList<String>

        // check dot contains in array
        for (i in arr) {
            if (i.contains(".")) {
                haveDot = true
            }
        }

        return getResult(forEachToken(tokenArrayList))
    }

    /** Method to help convert array to String */
    fun makeArrayToStr(arr : ArrayList<String>) : String {
        val str : StringBuilder = StringBuilder()
        for (s in arr) {
            if(s == "-" || s == "+" || s == "/" || s == "*" || s == "^"){
                str.append(" " + s + " ")
            } else {
                str.append(s)
            }
        }
        return str.toString()
    }

    /** Round the number */
    fun roundNumber (num: Double) : Double {
        return Math.round (num * 10000.0) / 10000.0
    }

    /** Log */
    fun log(str : String) : String {
       return roundNumber(Math.log(str.toDouble())).toString()
    }

    /** Function sin, cos, tan */
    fun functionIndecate(str : String, func : String) : String {
        when (func) {
            "sin" -> return roundNumber(Math.sin(str.toDouble())).toString()
            "cos" -> return roundNumber(Math.cos(str.toDouble())).toString()
            else -> return roundNumber(Math.tan(str.toDouble())).toString()
        }

    }

    /** Precent */
    fun percent(str : String) : String {
        val result = str.toDouble().div(100)
        return result.toString()
        if (result < 1) {
            haveDot = true
        }
    }

    fun forEachToken(tokenArrayList : ArrayList<String>) : Float {

        var currentStr : String = ""
        var currentNumber : Float = 0.0f

        tokenArrayList.forEach {
            when {
                it == "+" || it == "/" || it == "*" || it == "-" || it == "^" -> currentStr = it

                currentStr == "-" -> {
                    if (it != "") {currentNumber -= it.toFloat()}
                }

                currentStr == "/" -> {
                    if (it == "") {
                        currentNumber += 0
                    } else if (it == "0") {
                        currentNumber = 0.0f
                    } else if (it.toDouble() > currentNumber) {
                        currentNumber = currentNumber.div(it.toFloat())
                    } else {
                        currentNumber /= it.toFloat()
                    }
                }

                currentStr == "^" -> {
                    for (i in 1..it.toInt() - 1) {
                        var num = currentNumber
                        currentNumber *= num
                    }
                }

                currentStr == "*" ->{
                    if (it != "") {
                        currentNumber *= it.toFloat()
                    }
                }

                else -> {
                    if (!it.equals("")) {
                        currentNumber += it.toFloat()
                    }
                }
            }
        }

        return currentNumber
    }

    fun getResult(number: Float): String {
        if (number > 0 && number < 1) {
            return roundNumber(number.toDouble()).toString()
        } else if (haveDot) {
            if (number % 1.0 == 0.0) {
                return number.toString()
            } else {
                if (number.toString().length > 8) {
                    return roundNumber(number.toDouble()).toString()
                } else {
                    return number.toString()
                }
            }
        } else {
            return number.toString()
        }
    }
}