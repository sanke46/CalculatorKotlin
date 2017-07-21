@file:Suppress("UNUSED_EXPRESSION")

package com.example.ilafedoseev.calculatorkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivityK : AppCompatActivity() {

    var arrayExample : ArrayList<String> = ArrayList<String>()
    val update : UpdateUI = UpdateUI()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onRestoreInstanceState(savedInstanceState : Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        number.text = savedInstanceState.getString("number")
        arrayExample = savedInstanceState.getStringArrayList("arrayExample")
        cacheNumber.text = savedInstanceState.getString("cacheNumber")
    }

    override fun onSaveInstanceState(outState : Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList("arrayExample", arrayExample)
        outState.putString("number", number.text.toString())
        outState.putString("cacheNumber", cacheNumber.text.toString())
    }

    /** AC button */
    fun clearAllButton(view : View) {
        update.clearAll(number,cacheNumber,arrayExample)
    }

    /** Click equals */
    fun equalButton(view : View) {
        update.printResult(number,cacheNumber,arrayExample,this)
    }

    /** Click any Symbols */
    fun mathSymbolButton(view : View) {
        val button = view as Button
        update.addSymbols(button, number, arrayExample,this)
    }

    /** Click any number */
    fun numberButton(view : View) {
        val buttonNumber = view as Button
        update.addNumber(buttonNumber,number,arrayExample,this)
    }

    /** Percent calc */
    fun percentButton(view : View) {
        update.printPercent(number,cacheNumber,arrayExample,this)
    }

    /** Factorial */
    fun factorialButton(view : View) {
        update.printFactorial(number,cacheNumber,arrayExample,this)
    }

    /** Function : sin, cos, tan  */
    fun functionsButton(view : View) {
        val button = view as Button
        update.printFunction(button, number, cacheNumber,arrayExample,this)
    }

    /** Press button log */
    fun logButton(view : View) {
        update.printLog(number,cacheNumber,arrayExample,this)
    }

    /** Change number to minus and plus */
    fun changeValueButton(view : View) {
        update.printValueNumber(number,arrayExample)
    }
}
