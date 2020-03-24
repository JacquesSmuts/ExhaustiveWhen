package com.jacquessmuts.exhaustivewhenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jacquessmuts.exhaustivewhen.Force

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        whatever()
    }

    fun whatever() {

        val input = "ww"
        Force exhaustive when(input) {
            "ww" -> println("ha")
            else -> print("hoo")
        }
    }
}
