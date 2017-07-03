package com.example.ilafedoseev.calculatorkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private lateinit var number: TextView
    private lateinit var oldNumbers: TextView

    //numbers [zero - nine]
    private lateinit var one : Button
    private lateinit var two : Button
    private lateinit var three : Button
    private lateinit var four : Button
    private lateinit var five : Button
    private lateinit var six : Button
    private lateinit var seven : Button
    private lateinit var eight : Button
    private lateinit var nine : Button

    // button actions [AC,+/-,%,-,+,*,/,=,.]
    private lateinit var deleteAll : Button
    private lateinit var plusMinus : Button
    private lateinit var percent : Button
    private lateinit var minus : Button
    private lateinit var plus : Button
    private lateinit var multiplication : Button
    private lateinit var division : Button
    private lateinit var equals : Button
    private lateinit var dot : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number = findViewById(R.id.number) as TextView
        oldNumbers = findViewById(R.id.old_numbers) as TextView

        one = findViewById(R.id.one) as Button
        two = findViewById(R.id.two) as Button
        three = findViewById(R.id.three) as Button
        four = findViewById(R.id.four) as Button
        five = findViewById(R.id.five) as Button
        six = findViewById(R.id.six) as Button
        seven = findViewById(R.id.seven) as Button
        eight = findViewById(R.id.eighte) as Button
        nine = findViewById(R.id.nine) as Button


        deleteAll = findViewById(R.id.delete_all) as Button
        plusMinus = findViewById(R.id.plus_minus) as Button
        percent = findViewById(R.id.percent) as Button
        minus = findViewById(R.id.minus) as Button
        plus = findViewById(R.id.plus) as Button
        multiplication = findViewById(R.id.multiplication) as Button
        division = findViewById(R.id.division) as Button
        equals = findViewById(R.id.division) as Button
        dot = findViewById(R.id.dot) as Button

        one.setOnClickListener {
            displayNumber(1)
            print("number one was pressing")
        }

        two.setOnClickListener {
            displayNumber(2)
            print("number two was pressing")
        }
    }


    public fun setNumberToZero(){
        var zero = 0
        number.text = zero.toString()
    }

    //number buttons
    public fun displayNumber(num : Int){
        if (number.text.toString().equals("0")) {
            number.text = num.toString()
        } else {
            number.text = number.text.toString() + num.toString()
        }
    }

    //methods buttons actions
    public fun actionMinus(){
        var result : String = oldNumbers.text.toString() + number.text.toString()
        oldNumbers.text = result + " - "
        setNumberToZero()

    }

}
