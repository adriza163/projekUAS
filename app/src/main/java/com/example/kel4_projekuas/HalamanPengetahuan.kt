package com.example.kel4_projekuas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HalamanPengetahuan : AppCompatActivity() {
        @SuppressLint("MissingInflatedId")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_halaman_pengetahuan)

            val pengetahuan = findViewById<CardView>(R.id.pengetahuan)
            val pengetahuan2 = findViewById<CardView>(R.id.pengetahuan2)
            val pengetahuan3 = findViewById<CardView>(R.id.pengetahuan3)

            val intentPengetahuan = Intent(this@HalamanPengetahuan, DetailPengetahuan::class.java)
            val intentPengetahuan2 = Intent(this@HalamanPengetahuan, DetailPengetahuan2::class.java)
            val intentPengetahuan3 = Intent(this@HalamanPengetahuan, DetailPengetahuan3::class.java)

            pengetahuan.setOnClickListener{startActivity(intentPengetahuan)}
            pengetahuan2.setOnClickListener{startActivity(intentPengetahuan2)}
            pengetahuan3.setOnClickListener{startActivity(intentPengetahuan3)}
        }
    }
