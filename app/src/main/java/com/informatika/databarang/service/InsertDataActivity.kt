package com.informatika.databarang.service

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import com.informatika.databarang.R
import com.informatika.databarang.adapter.ListContent
import com.informatika.databarang.model.ResponseActionBarang
import com.informatika.databarang.model.ResponseBarang
import com.informatika.databarang.network.koneksi
import kotlinx.android.synthetic.main.activity_insert_data.*
import kotlinx.android.synthetic.main.activity_update_data.*
import kotlinx.android.synthetic.main.activity_update_data.btn_submit
import kotlinx.android.synthetic.main.activity_update_data.toolbar
import retrofit2.Response
import javax.security.auth.callback.Callback

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val etNamaBarang = et_nama_barang.text
            val etJmlBarang = et_jumlah_barang.text
            if (etJmlBarang.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Jumlah Barang Tidak Boleh kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (etNamaBarang.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Nama Barang Tidak Boleh kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                actionData(etNamaBarang.toString(), etJmlBarang.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }

        getData()
    }

    fun fromClear() {
        et_nama_barang.text.clear()
        et_jumlah_barang.text.clear()
    }

    fun actionData(namaBarang: String, jmlBarang: String) {
        koneksi.service.insertBarang(namaBarang, jmlBarang)
            .enqueue(object : Callback<ResponseActionBarang> {
                override fun onFailure(call: Call<ResponseActionBarang>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseActionBarang>,
                    response: Response<ResponseActionBarang>

                ) {

                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getBarang().enqueue(object : Callback<ResponseBarang> {
            override fun onFailure(call: Call<ResponseBarang>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseBarang>,
                response: Response<ResponseBarang>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity)

                    rv_data_barang.apply {
                       // adapter = rvAdapter
                      //  layoutManager = LinerLayoutManager(this@InsertDataActivity)

                    }
                }
            }

        })
    }
}



