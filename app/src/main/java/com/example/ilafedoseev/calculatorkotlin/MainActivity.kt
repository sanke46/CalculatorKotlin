package com.example.ilafedoseev.calculatorkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var number: TextView

    private lateinit var oldNumbers: TextView

    //numbers [zero - nine]
    private val one = findViewById(R.id.one) as Button
    private val two = findViewById(R.id.two) as Button
    private val three = findViewById(R.id.three) as Button
    private val four = findViewById(R.id.four) as Button
    private val five = findViewById(R.id.five) as Button
    private val six = findViewById(R.id.six) as Button
    private val seven = findViewById(R.id.seven) as Button
    private val eight = findViewById(R.id.eighte) as Button
    private val nine = findViewById(R.id.nine) as Button

    // button actions [AC,+/-,%,-,+,*,/,=,.]
    private val deleteAll = findViewById(R.id.delete_all) as Button
    private val plusMinus = findViewById(R.id.plus_minus) as Button
    private val percent = findViewById(R.id.percent) as Button
    private val minus = findViewById(R.id.minus) as Button
    private val plus = findViewById(R.id.plus) as Button
    private val multiplication = findViewById(R.id.multiplication) as Button
    private val division = findViewById(R.id.division) as Button
    private val equals = findViewById(R.id.equals) as Button
    private val dot = findViewById(R.id.dot) as Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number = findViewById(R.id.number) as TextView
        oldNumbers = findViewById(R.id.old_numbers) as TextView

    }

    public fun setNumberToZero(){
        var zero = 0
        number.text = zero.toString()
    }

    //number buttons
    public fun oneNumber(){
        var one : Int = 1
        number.text = this.toString() + one.toString()
    }

    //methods buttons actions
    public fun actionMinus(){
        var result : String = oldNumbers.text.toString() + number.text.toString()
        oldNumbers.text = result + " - "
        setNumberToZero()

    }

}
