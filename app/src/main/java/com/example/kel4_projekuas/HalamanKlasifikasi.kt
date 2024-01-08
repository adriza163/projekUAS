package com.example.kel4_projekuas

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class HalamanKlasifikasi : AppCompatActivity() {
    private lateinit var nama_hewan: EditText
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

        nama_hewan = findViewById(R.id.EditTextNamaHewan)
        bentuk_paruh = findViewById(R.id.EditTextBentukParuh)
        bentuk_gigi = findViewById(R.id.EditTextBentukGigi)
        bentuk_kaki = findViewById(R.id.EditTextBentukKaki)
        bentuk_mulut = findViewById(R.id.EditTextBentukMulut)
        deteksi = findViewById(R.id.deteksi)
        hasil = findViewById(R.id.hasil)

        deteksi.setOnClickListener{
            val jsonObject = JSONObject()
            jsonObject.put("bentuk_paruh", bentuk_paruh.text.toString())
            jsonObject.put("bentuk_gigi", bentuk_gigi.text.toString())
            jsonObject.put("bentuk_kaki", bentuk_kaki.text.toString())
            jsonObject.put("bentuk_mulut", bentuk_mulut.text.toString())

//            jsonObject.put("bentuk_paruh", 2)
//            jsonObject.put("bentuk_gigi", 0)
//            jsonObject.put("bentuk_kaki", 1)
//            jsonObject.put("bentuk_mulut", 2)
            val requestBody = jsonObject.toString()

            val jsonObjectRequest = object : JsonObjectRequest(
                Method.POST, url, jsonObject,
                Response.Listener { response ->
                    try {
                        val data = response.getInt("kategori_makanan")
                        hasil.text = if (data == 0){
                            "Herbivora"
                        } else if (data == 1){
                            "Karnivora"
                        } else if (data == 2){
                            "Omnivora"
                        } else {
                            "Tidak aada kategori makanan"
                        }
                    } catch (e: JSONException){
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
}