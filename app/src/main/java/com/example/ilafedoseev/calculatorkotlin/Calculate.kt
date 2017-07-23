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
    fun roundNumber (num : Double) : Double {
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

    fun forEachToken(tokenArrayList : ArrayList<String>) : Double {

        var currentStr : String = ""
        var currentNumber : Double = 0.0

        tokenArrayList.forEach {
            when {
                it == "+" || it == "/" || it == "*" || it == "-" || it == "^" -> currentStr = it

                currentStr == "-" -> {
                    if (it != "") {currentNumber -= it.toDouble()}
                }

                currentStr == "/" -> {
                    if (it == "") {
                        currentNumber += 0
                    } else if (it == "0") {
                        currentNumber = 0.0
                    } else if (it.toDouble() > currentNumber) {
                        currentNumber = currentNumber.div(it.toDouble())
                    } else {
                        currentNumber /= it.toDouble()
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
                        currentNumber *= it.toDouble()
                    }
                }

                else -> {
                    if (!it.equals("")) {
                        currentNumber += it.toDouble()
                    }
                }
            }
        }

        return currentNumber
    }

    fun getResult(number : Double): String {
        if (number > 0 && number < 1) {
            return roundNumber(number).toString()
        } else if (haveDot) {
            if (number % 1.0 == 0.0) {
                return number.toInt().toString()
            } else {
                if (number.toString().length > 8) {
                    return roundNumber(number).toString()
                } else {
                    return number.toString()
                }
            }
        } else {
            return number.toInt().toString()
        }
    }
}