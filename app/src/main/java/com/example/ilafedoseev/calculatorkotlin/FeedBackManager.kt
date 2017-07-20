package com.example.ilafedoseev.calculatorkotlin

import android.content.Context
import android.os.Vibrator
import android.widget.TextView


/**
 * Created by ilafedoseev on 19/07/2017.
 */
class FeedBackManager  {

    fun canNotDoThat(textView: TextView, str : String) {
        textView.text = str
        errorVibration(context)
    }

    fun btnVibration() {
        vibratePhone(context)
    }

    fun vibratePhone(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationPattern = longArrayOf(0, 15)
        vibrator.vibrate(vibrationPattern, -1)
    }

    /** Vibration */
    private fun buttonVibration() {
        val v = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
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