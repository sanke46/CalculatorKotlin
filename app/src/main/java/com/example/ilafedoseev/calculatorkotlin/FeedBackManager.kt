package com.example.ilafedoseev.calculatorkotlin

import android.content.Context
import android.os.Vibrator
import android.widget.TextView


/**
 * Created by ilafedoseev on 19/07/2017.
 */
class FeedBackManager {

    fun canNotDoThat(textView: TextView, str : String,context: Context) {
        textView.text = str
        errorVibration(context)
    }

    fun btnVibration(context: Context) {
        buttonVibration(context)
    }

    /** Vibration */
    private fun buttonVibration(context: Context) {
        val v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 15)
        v.vibrate(vibrationPattern, -1)
    }

    /** Error */
    private fun errorVibration(context: Context){
        var v = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 50, 100, 200)
        v.vibrate(vibrationPattern, -1)
    }
}