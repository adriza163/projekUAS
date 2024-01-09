package com.example.kel4_projekuas

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class HalamanKlasifikasi : AppCompatActivity() {
    private lateinit var bentuk_paruh: EditText
    private lateinit var bentuk_gigi: EditText
    private lateinit var bentuk_kaki: EditText
    private lateinit var bentuk_mulut: EditText
    private lateinit var deteksi: Button
    private lateinit var hasil: TextView

    private val url = "https://adriza163.pythonanywhere.com/predict"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_halaman_klasifikasi)

        bentuk_paruh = findViewById(R.id.EditTextBentukParuh)
        bentuk_gigi = findViewById(R.id.EditTextBentukGigi)
        bentuk_kaki = findViewById(R.id.EditTextBentukKaki)
        bentuk_mulut = findViewById(R.id.EditTextBentukMulut)
        deteksi = findViewById(R.id.deteksi)
        hasil = findViewById(R.id.hasil)

        deteksi.setOnClickListener {
            val jsonObject = JSONObject()
            jsonObject.put("bentuk_paruh", mapBentukParuhToInt(bentuk_paruh.text.toString()))
            jsonObject.put("bentuk_gigi", mapBentukGigiToInt(bentuk_gigi.text.toString()))
            jsonObject.put("bentuk_kaki", mapBentukKakiToInt(bentuk_kaki.text.toString()))
            jsonObject.put("bentuk_mulut", mapBentukMulutToInt(bentuk_mulut.text.toString()))

            val requestBody = jsonObject.toString()

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST, url, jsonObject,
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
