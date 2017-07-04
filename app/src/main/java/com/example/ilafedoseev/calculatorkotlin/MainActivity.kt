@file:Suppress("UNUSED_EXPRESSION")

package com.example.ilafedoseev.calculatorkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IntegerRes
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import org.jetbrains.anko.toast
import java.util.*

class MainActivity : AppCompatActivity() {

     var arrayExample : ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //numbers [zero - nine]
//        val one = findViewById(R.id.one) as Button
//        var two = findViewById(R.id.two) as Button
//        var three = findViewById(R.id.three) as Button
//        var four = findViewById(R.id.four) as Button
//        var five = findViewById(R.id.five) as Button
//        var six = findViewById(R.id.six) as Button
//        var seven = findViewById(R.id.seven) as Button
//        var eight = findViewById(R.id.eighte) as Button
//        var nine = findViewById(R.id.nine) as Button
//        var zero = findViewById(R.id.zero) as Button
//
//        var deleteAll = findViewById(R.id.delete_all) as Button
//        var plusMinus = findViewById(R.id.plus_minus) as Button
//        var percent = findViewById(R.id.percent) as Button
//        var minus = findViewById(R.id.minus) as Button
//        var plus = findViewById(R.id.plus) as Button
//        var multiplication = findViewById(R.id.multiplication) as Button
//        var division = findViewById(R.id.division) as Button
//        var equals = findViewById(R.id.division) as Button
//        var dot = findViewById(R.id.dot) as Button

//        actionCalculate = findViewById(R.id.number) as TextView

    }

    val res: Int = 0

    fun displayActionCalculation() : String {
        var actionNumbers : String = ""
        arrayExample.forEach { action : String -> actionNumbers += action }
        return actionNumbers
    }

    fun makeAllStr(list: List<String>,joiner: String = "") : String {
         if (list.isEmpty()){
             return ""
         } else {
             return list.reduce { acc, s ->acc + joiner + s }
         }

    }

    fun displayCachCalculation(){}

    fun actionOfCalculation(){}

    fun numberButtonClick(view: View){
        val button = view as Button
        val numberStr = button.text

//      val cashCalculate = findViewById(R.id.old_numbers) as TextView
        val actionCalculate = findViewById(R.id.number) as TextView
        arrayExample.add(numberStr.toString())
        var text = makeAllStr(arrayExample)
        actionCalculate.text = text

    }


}
