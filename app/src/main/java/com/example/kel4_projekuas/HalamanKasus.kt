package com.example.kel4_projekuas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class HalamanKasus : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_halaman_kasus)

        val kasus = findViewById<CardView>(R.id.kasus)
        val kasus2 = findViewById<CardView>(R.id.kasus2)
        val kasus3 = findViewById<CardView>(R.id.kasus3)
        val kasus4 = findViewById<CardView>(R.id.kasus4)

        val intentKasus = Intent(this@HalamanKasus, DetailKasus::class.java)
        val intentKasus2 = Intent(this@HalamanKasus, DetailKasus2::class.java)
        val intentKasus3 = Intent(this@HalamanKasus, DetailKasus3::class.java)
        val intentKasus4 = Intent(this@HalamanKasus, DetailKasus4::class.java)

        kasus.setOnClickListener{startActivity(intentKasus)}
        kasus2.setOnClickListener{startActivity(intentKasus2)}
        kasus3.setOnClickListener{startActivity(intentKasus3)}
        kasus4.setOnClickListener{startActivity(intentKasus4)}
    }
}