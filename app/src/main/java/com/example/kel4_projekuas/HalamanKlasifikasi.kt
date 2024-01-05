package com.example.kel4_projekuas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
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
    private lateinit var nama_hewan: TextInputLayout
    private lateinit var bentuk_paruh: TextInputLayout
    private lateinit var bentuk_gigi: TextInputLayout
    private lateinit var bentuk_kaki: TextInputLayout
    private lateinit var bentuk_mulut: TextInputLayout
    private lateinit var deteksi: Button
    private lateinit var hasil: TextView

    private val url = "https://adriza163.pythonanywhere.com/predict"
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_halaman_klasifikasi)

        nama_hewan = findViewById(R.id.textInputLayoutNamaHewan)
        bentuk_paruh = findViewById(R.id.textInputLayoutBentukParuh)
        bentuk_gigi = findViewById(R.id.textInputLayoutBentukGigi)
        bentuk_kaki = findViewById(R.id.textInputLayoutBentukKaki)
        bentuk_mulut = findViewById(R.id.textInputLayoutBentukMulut)
        deteksi = findViewById(R.id.deteksi)
        hasil = findViewById(R.id.hasil)

        deteksi.setOnClickListener{
            val jsonObject = JSONObject()
            jsonObject.put("bentuk_paruh", bentuk_paruh.toString())
            jsonObject.put("bentuk_gigi", bentuk_gigi.toString())
            jsonObject.put("bentuk_kaki", bentuk_kaki.toString())
            jsonObject.put("bentuk_mulut", bentuk_mulut.toString())

            val requestBody = jsonObject.toString()

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST, url, jsonObject,
                Response.Listener { response ->
                    try {
                        val data = response.getString("kategori_makanan")
                        hasil.text = if (data == "0"){
                            "Herbivora"
                        } else if (data == "1"){
                            "Karnivora"
                        } else if (data == "2"){
                            "Omnivora"
                        } else {
                            "Tidak aada kategori makanan"
                        }
                    } catch (e: JSONException){
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this@HalamanKlasifikasi, error.message, Toast.LENGTH_SHORT).show()
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
}