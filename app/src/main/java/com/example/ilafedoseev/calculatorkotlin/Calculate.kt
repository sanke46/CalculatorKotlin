package com.example.ilafedoseev.calculatorkotlin

import java.util.*

/**
 * Created by ilafedoseev on 16/07/2017.
 */

class Calculate {

    /** Calculate method */
    fun calculate(arr: ArrayList<String>) : String {
        var currentStr : String = ""
        var currentNumber : Double = 0.0
        var haveDot : Boolean = false

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


        // loop to calculate
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

        // display result
        if (currentNumber > 0 && currentNumber < 1) {
            return roundNumber(currentNumber).toString()
        } else if (haveDot) {
            if (currentNumber % 1.0 == 0.0) {
                return currentNumber.toInt().toString()
            } else {
                return currentNumber.toString()
            }
        } else {
            return currentNumber.toInt().toString()
        }
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
        return (str.toDouble().div(100)).toString()
    }
}