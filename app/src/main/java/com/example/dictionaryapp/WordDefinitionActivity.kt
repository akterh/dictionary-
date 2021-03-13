package com.example.dictionaryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class WordDefinitionActivity : AppCompatActivity() {

    private val KEY = "WORD_DEFINITION"
    lateinit var textView: TextView
    lateinit var back:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word_definition)
        textView = findViewById(R.id.txt_def)
        back = findViewById(R.id.back_arr)


        textView.text = intent.getStringExtra(KEY)

        back.setOnClickListener { finish() }

    }
}