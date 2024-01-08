package com.example.kel4_projekuas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.nio.charset.Charset

class PredictFragment : Fragment() {
    private lateinit var backButton: Button

    private lateinit var radioGroupBentukParuh: RadioGroup
    private lateinit var radioBentukParuh0: RadioButton
    private lateinit var radioBentukParuh1: RadioButton
    private lateinit var radioBentukParuh2: RadioButton
    private lateinit var radioBentukParuh3: RadioButton

    private lateinit var radioGroupBentukGigi: RadioGroup
    private lateinit var radioBentukGigi0: RadioButton
    private lateinit var radioBentukGigi1: RadioButton
    private lateinit var radioBentukGigi2: RadioButton
    private lateinit var radioBentukGigi3: RadioButton

    private lateinit var radioGroupBentukKaki: RadioGroup
    private lateinit var radioBentukKaki0: RadioButton
    private lateinit var radioBentukKaki1: RadioButton
    private lateinit var radioBentukKaki2: RadioButton
    private lateinit var radioBentukKaki3: RadioButton

    private lateinit var radioGroupBentukMulut: RadioGroup
    private lateinit var radioBentukMulut0: RadioButton
    private lateinit var radioBentukMulut1: RadioButton
    private lateinit var radioBentukMulut2: RadioButton
    private lateinit var radioBentukMulut3: RadioButton
    private lateinit var radioBentukMulut4: RadioButton
    private lateinit var radioBentukMulut5: RadioButton


    private lateinit var deteksi: Button
    private lateinit var label_hasil: TextView
    private lateinit var teks_hasil: TextView
    private val url = "https://adriza163.pythonanywhere.com/predict"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_predict_klasifikasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)

        backButton.setOnClickListener {
            navigateToDashboardFragment()
        }
        deteksi.setOnClickListener {
            performPrediction()
        }
    }

    private fun initViews(view: View) {
//        backButton = view.findViewById(R.id.back_button)

        radioGroupBentukParuh = view.findViewById(R.id.radioGroupBentukParuh)
        radioBentukParuh0 = view.findViewById(R.id.radiobentukparuh0)
        radioBentukParuh1 = view.findViewById(R.id.radiobentukparuh1)
        radioBentukParuh2 = view.findViewById(R.id.radiobentukparuh2)
        radioBentukParuh3 = view.findViewById(R.id.radiobentukparuh3)


        radioGroupBentukGigi = view.findViewById(R.id.radioGroupBentukGigi)
        radioBentukGigi0 = view.findViewById(R.id.radiobentukgigi0)
        radioBentukGigi1 = view.findViewById(R.id.radiobentukgigi1)
        radioBentukGigi2 = view.findViewById(R.id.radiobentukgigi2)
        radioBentukGigi3 = view.findViewById(R.id.radiobentukgigi3)

        radioGroupBentukKaki = view.findViewById(R.id.radioGroupBentukKaki)
        radioBentukKaki0 = view.findViewById(R.id.radiobentukkaki0)
        radioBentukKaki1 = view.findViewById(R.id.radiobentukkaki1)
        radioBentukKaki2 = view.findViewById(R.id.radiobentukkaki2)
        radioBentukKaki3 = view.findViewById(R.id.radiobentukkaki3)

        radioGroupBentukMulut = view.findViewById(R.id.radioBentukMulut)
        radioBentukMulut0 = view.findViewById(R.id.radiobentukmulut0)
        radioBentukMulut1 = view.findViewById(R.id.radiobentukmulut1)
        radioBentukMulut2 = view.findViewById(R.id.radiobentukmulut2)
        radioBentukMulut3 = view.findViewById(R.id.radiobentukmulut3)
        radioBentukMulut4 = view.findViewById(R.id.radiobentukmulut4)
        radioBentukMulut5 = view.findViewById(R.id.radiobentukmulut5)

        deteksi = view.findViewById(R.id.deteksi)
        label_hasil = view.findViewById(R.id.labelHasil)
        teks_hasil = view.findViewById(R.id.teksHasil)

    }

    private fun performPrediction() {
        val jsonObject = JSONObject()

        val checkRadioBentukParuhId = radioGroupBentukParuh.checkedRadioButtonId
        val radioButtonBentukParuh = view?.findViewById<RadioButton>(checkRadioBentukParuhId)
        val radioBahanSampah = when (radioButtonBentukParuh) {
            radioBentukParuh0 -> 0
            radioBentukParuh1 -> 1
            radioBentukParuh2 -> 2
            radioBentukParuh3 -> 3
            else -> 0
        }

        val checkRadioBentukGigiId = radioGroupBentukGigi.checkedRadioButtonId
        val radioButtonBentukGigi = view?.findViewById<RadioButton>(checkRadioBentukGigiId)
        val radioAsalOrganisme = when (radioButtonBentukGigi) {
            radioBentukGigi0 -> 0
            radioBentukGigi1 -> 1
            radioBentukGigi2 -> 2
            radioBentukGigi3 -> 3
            else -> 0
        }

        val checkRadioBentukKakiId = radioGroupBentukKaki.checkedRadioButtonId
        val radioButtonBentukKaki = view?.findViewById<RadioButton>(checkRadioBentukKakiId)
        val radioBentukKaki = when (radioButtonBentukKaki) {
            radioBentukKaki0 -> 0
            radioBentukKaki1 -> 1
            radioBentukKaki2 -> 2
            radioBentukKaki3 -> 3
            else -> 0
        }
        val checkRadioBentukMulutId = radioGroupBentukMulut.checkedRadioButtonId
        val radioButtonBentukMulut = view?.findViewById<RadioButton>(checkRadioBentukMulutId)
        val radioBentukMulut = when (radioButtonBentukMulut) {
            radioBentukMulut0 -> 0
            radioBentukMulut1 -> 1
            radioBentukMulut2 -> 2
            radioBentukMulut3 -> 3
            radioBentukMulut4 -> 4
            radioBentukMulut5 -> 5
            else -> 0
        }


        jsonObject.put("bahan_sampah",radio)
        jsonObject.put("asal_organisme", radioAsalOrganisme)
        jsonObject.put("mudah_membusuk", radioMudahMembusuk)
        jsonObject.put("mudah_terbakar", radioMudahTerbakar)
        jsonObject.put("lama_terurai", lama_terurai.text.toString())

        val requestBody = jsonObject.toString()

        val jsonObjectRequest = object : JsonObjectRequest(
            Method.POST, url, jsonObject,
            Response.Listener { response ->
                try {
                    val data = response.getString("Kategori Sampah")
                    val label = "Kategori Sampah"
                    var deskripsi = ""

                    if (teks_hasil.text == "Anorganik") {
                        deskripsi =
                            "Sampah ini sulit terurai, jangan campurkan dengan sampah organik lainnya " +
                                    "seperti makanan yang mudah membusuk karena tidak bagus untuk kesehatan"
                    } else {
                        deskripsi =
                            "Sampah ini mudah terurai, jangan campurkan dengan sampah anorganik lainnya " +
                                    "seperti plastik, kaca, kaleng, atau styrofoam karena tidak bagus untuk kesehatan"
                    }

                    label_hasil.text = label
                    teks_hasil.text = data


                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                val errorMessage = error?.message ?: "Mohon Lengkapi Data"
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                Log.e("NetworkError", "Volley error: $error")
            }) {
            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun getBody(): ByteArray {
                return requestBody.toByteArray(Charset.defaultCharset())
            }
        }

        val queue = Volley.newRequestQueue(requireContext())
        queue.add(jsonObjectRequest)
    }

    private fun navigateToDashboardFragment() {
        // Buat objek FragmentManager
        val fragmentManager = requireActivity().supportFragmentManager

        // Buat objek FragmentTransaction
        val fragmentTransaction = fragmentManager.beginTransaction()

        // Ganti dengan DashboardFragment
        val dashboardFragment = DashboardFragment()
        fragmentTransaction.replace(R.id.container, dashboardFragment)
        updateMainActivityText()
        // Lakukan transaksi
        fragmentTransaction.commit()
    }

    private lateinit var mainActivityTextView: TextView
    private fun updateMainActivityText() {
        if (activity is MainActivity) {
            mainActivityTextView = (activity as MainActivity).findViewById(R.id.header_text_title)
            mainActivityTextView.text = getString(R.string.dashboard_header)
            mainActivityTextView = (activity as MainActivity).findViewById(R.id.header_text_desc)
            mainActivityTextView.text = getString(R.string.dashboard_header_desc)
        }
    }
}