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
//        val pengetahuan2 = findViewById<CardView>(R.id.pengetahuan2)
//        val pengetahuan3 = findViewById<CardView>(R.id.pengetahuan3)

        val intentKasus = Intent(this@HalamanKasus, DetailKasus::class.java)
//        val intentPengetahuan2 = Intent(this@HalamanPengetahuan, DetailPengetahuan2::class.java)
//        val intentPengetahuan3 = Intent(this@HalamanPengetahuan, DetailPengetahuan3::class.java)

        kasus.setOnClickListener{startActivity(intentKasus)}
//        pengetahuan2.setOnClickListener{startActivity(intentPengetahuan2)}
//        pengetahuan3.setOnClickListener{startActivity(intentPengetahuan3)}
    }
}