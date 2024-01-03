package com.example.kel4_projekuas

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fiturKlasifikasi = findViewById<ImageButton>(R.id.klasifikasi)
        val fiturKasus = findViewById<ImageButton>(R.id.kasus)
        val fiturPengetahuan = findViewById<ImageButton>(R.id.pengetahuan)

        val intentKlasifikasi = Intent(this@MainActivity, HalamanKlasifikasi::class.java)
        val intentKasus = Intent(this@MainActivity, HalamanKasus::class.java)
        val intentPengetahuan = Intent(this@MainActivity, HalamanPengetahuan::class.java)

        fiturKlasifikasi.setOnClickListener{startActivity(intentKlasifikasi)}
        fiturKasus.setOnClickListener{startActivity(intentKasus)}
        fiturPengetahuan.setOnClickListener{startActivity(intentPengetahuan)}
    }
}