package com.example.kel4_projekuas

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.text.HtmlCompat

class HalamanKlasifikasi : AppCompatActivity() {
    private lateinit var dropdownBentukParuh: AutoCompleteTextView
    private lateinit var dropdownBentukGigi: AutoCompleteTextView
    private lateinit var dropdownBentukKaki: AutoCompleteTextView
    private lateinit var dropdownBentukMulut: AutoCompleteTextView
    private lateinit var deteksi: Button
    private lateinit var hasil: TextView

    private val url = "https://adriza163.pythonanywhere.com/predict"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_halaman_klasifikasi)

        deteksi = findViewById(R.id.deteksi)
        hasil = findViewById(R.id.hasil)

        val spinnerBentukParuh: Spinner = findViewById(R.id.SpinnerBentukParuh)
        val spinnerBentukGigi: Spinner = findViewById(R.id.SpinnerBentukGigi)
        val spinnerBentukKaki: Spinner = findViewById(R.id.SpinnerBentukKaki)
        val spinnerBentukMulut: Spinner = findViewById(R.id.SpinnerBentukMulut)

        val itemsBentukParuh = resources.getStringArray(R.array.bentuk_paruh)
        val itemsBentukGigi = resources.getStringArray(R.array.bentuk_gigi)
        val itemsBentukKaki = resources.getStringArray(R.array.bentuk_kaki)
        val itemsBentukMulut = resources.getStringArray(R.array.bentuk_mulut)

        val adapterBentukParuh = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            itemsBentukParuh.map {
                HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
            })

        val adapterBentukGigi = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            itemsBentukGigi.map {
                HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
            })

        val adapterBentukKaki = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            itemsBentukKaki.map {
                HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
            })

        val adapterBentukMulut = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            itemsBentukMulut.map {
                HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY)
            })

        adapterBentukParuh.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterBentukGigi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterBentukKaki.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterBentukMulut.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerBentukParuh.adapter = adapterBentukParuh
        spinnerBentukGigi.adapter = adapterBentukGigi
        spinnerBentukKaki.adapter = adapterBentukKaki
        spinnerBentukMulut.adapter = adapterBentukMulut

        spinnerBentukParuh.setSelection(0)
        spinnerBentukGigi.setSelection(0)
        spinnerBentukKaki.setSelection(0)
        spinnerBentukMulut.setSelection(0)

        deteksi.setOnClickListener {
            val jsonObject = JSONObject()
            jsonObject.put("bentuk_paruh", mapBentukParuhToInt(spinnerBentukParuh.selectedItem.toString()))
            jsonObject.put("bentuk_gigi", mapBentukGigiToInt(spinnerBentukGigi.selectedItem.toString()))
            jsonObject.put("bentuk_kaki", mapBentukKakiToInt(spinnerBentukKaki.selectedItem.toString()))
            jsonObject.put("bentuk_mulut", mapBentukMulutToInt(spinnerBentukMulut.selectedItem.toString()))

            val requestBody = jsonObject.toString()

            val jsonObjectRequest = object : JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                Response.Listener { response ->
                    try {
                        val data = response.getInt("kategori_makanan")
                        hasil.text = when (data) {
                            0 -> "Herbivora"
                            1 -> "Karnivora"
                            2 -> "Omnivora"
                            else -> "Tidak ada kategori makanan"
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    val errorMessage = error?.message ?: "Mohon Lengkapi Data"
                    Toast.makeText(this@HalamanKlasifikasi, errorMessage, Toast.LENGTH_SHORT).show()
                    Log.e("Network Error", "Volley Error: $error")
                }) {
                override fun getBodyContentType(): String {
                    return "application/json"
                }

                override fun getBody(): ByteArray {
                    return requestBody.toByteArray(Charset.defaultCharset())
                }
            }
            val queue = Volley.newRequestQueue(this@HalamanKlasifikasi)
            queue.add(jsonObjectRequest)
        }


//        dropdownBentukParuh = findViewById(R.id.dropdownBentukParuh)
//        dropdownBentukGigi = findViewById(R.id.dropdownBentukGigi)
//        dropdownBentukKaki = findViewById(R.id.dropdownBentukKaki)
//        dropdownBentukMulut = findViewById(R.id.dropdownBentukMulut)
//        deteksi = findViewById(R.id.deteksi)
//        hasil = findViewById(R.id.hasil)
//
//        // Inisialisasi daftar pilihan untuk bentuk paruh
//        val bentukParuhOptions = arrayOf("Panjang", "Pendek", "Kecil", "Lebih Panjang")
//        val adapterBentukParuh = ArrayAdapter(this, R.layout.dropdown_item, bentukParuhOptions)
//        dropdownBentukParuh.setAdapter(adapterBentukParuh)
//
//        // Inisialisasi daftar pilihan untuk bentuk gigi
//        val bentukGigiOptions = arrayOf("Tidak Ada", "Tumpul", "Runcing", "Tajam")
//        val adapterBentukGigi = ArrayAdapter(this, R.layout.dropdown_item, bentukGigiOptions)
//        dropdownBentukGigi.setAdapter(adapterBentukGigi)
//
//        // Inisialisasi daftar pilihan untuk bentuk kaki
//        val bentukKakiOptions = arrayOf("0", "2", "4", "6")
//        val adapterBentukKaki = ArrayAdapter(this, R.layout.dropdown_item, bentukKakiOptions)
//        dropdownBentukKaki.setAdapter(adapterBentukKaki)
//
//        // Inisialisasi daftar pilihan untuk bentuk mulut
//        val bentukMulutOptions = arrayOf("Tidak Ada", "Lebih Kecil", "Kecil", "Sedang", "Luas", "Lebar")
//        val adapterBentukMulut = ArrayAdapter(this, R.layout.dropdown_item, bentukMulutOptions)
//        dropdownBentukMulut.setAdapter(adapterBentukMulut)
//
//        deteksi.setOnClickListener {
//            val jsonObject = JSONObject()
//            jsonObject.put("bentuk_paruh", mapBentukParuhToInt(dropdownBentukParuh.text.toString()))
//            jsonObject.put("bentuk_gigi", mapBentukGigiToInt(dropdownBentukGigi.text.toString()))
//            jsonObject.put("bentuk_kaki", mapBentukKakiToInt(dropdownBentukKaki.text.toString()))
//            jsonObject.put("bentuk_mulut", mapBentukMulutToInt(dropdownBentukMulut.text.toString()))
//
//            val requestBody = jsonObject.toString()
//
//            val jsonObjectRequest = object : JsonObjectRequest(
//                Request.Method.POST, url, jsonObject,
//                Response.Listener { response ->
//                    try {
//                        val data = response.getInt("kategori_makanan")
//                        hasil.text = when (data) {
//                            0 -> "Herbivora"
//                            1 -> "Karnivora"
//                            2 -> "Omnivora"
//                            else -> "Tidak ada kategori makanan"
//                        }
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                },
//                Response.ErrorListener { error ->
//                    val errorMessage = error?.message ?: "Mohon Lengkapi Data"
//                    Toast.makeText(this@HalamanKlasifikasi, errorMessage, Toast.LENGTH_SHORT).show()
//                    Log.e("Network Error", "Volley Error: $error")
//                }) {
//                override fun getBodyContentType(): String {
//                    return "application/json"
//                }
//
//                override fun getBody(): ByteArray {
//                    return requestBody.toByteArray(Charset.defaultCharset())
//                }
//            }
//            val queue = Volley.newRequestQueue(this@HalamanKlasifikasi)
//            queue.add(jsonObjectRequest)
//        }
    }

    private fun mapBentukParuhToInt(bentukParuhText: String): Int {
        return when (bentukParuhText.toLowerCase()) {
            "panjang" -> 0
            "pendek" -> 1
            "kecil" -> 2
            "lebih panjang" -> 3
            else -> -1 // Handle invalid input as needed
        }
    }

    private fun mapBentukGigiToInt(bentukGigiText: String): Int {
        return when (bentukGigiText.toLowerCase()) {
            "tidak ada" -> 0
            "tumpul" -> 1
            "runcing" -> 2
            "tajam" -> 3
            else -> -1 // Handle invalid input as needed
        }
    }

    private fun mapBentukKakiToInt(bentukKakiText: String): Int {
        return when (bentukKakiText.toLowerCase()) {
            "0" -> 0
            "2" -> 1
            "4" -> 2
            "6" -> 3
            else -> -1 // Handle invalid input as needed
        }
    }

    private fun mapBentukMulutToInt(bentukMulutText: String): Int {
        return when (bentukMulutText.toLowerCase()) {
            "tidak ada" -> 0
            "lebih kecil" -> 1
            "kecil" -> 2
            "sedang" -> 3
            "luas" -> 4
            "lebar" -> 5
            else -> -1 // Handle invalid input as needed
        }
    }
}