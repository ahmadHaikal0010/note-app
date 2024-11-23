package com.haikal.noteapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailPageActivity : AppCompatActivity() {

    private lateinit var txtJudulDetail: TextView
    private lateinit var txtContentDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_page)

        txtJudulDetail = findViewById(R.id.txtJudulDetail)
        txtContentDetail = findViewById(R.id.txtContentDetail)

        val judul = intent.getStringExtra("title")
        val kontent = intent.getStringExtra("content")

        txtJudulDetail.text = judul
        txtContentDetail.text = kontent

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}