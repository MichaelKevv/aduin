package com.kevin.aduin.tanggapan

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.kevin.aduin.R
import kotlinx.android.synthetic.main.activity_tanggapan.*
import org.json.JSONObject

class TanggapanActivity : AppCompatActivity() {
    var arrayList = ArrayList<Tanggapans>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tanggapan)
        supportActionBar?.title = "Data Tanggapan"

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener {
            startActivity(Intent(this, ManageTanggapanActivity::class.java))
        }
    }
    private fun loadAllTanggapan() {
        val loading = ProgressDialog(this)
        loading.setMessage("Memuat data...")
        loading.show()

        AndroidNetworking.get(ApiEndPointT.READ)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    arrayList.clear()
                    val jsonArray = response?.optJSONArray("result")

                    if (jsonArray?.length() == 0) {
                        loading.dismiss()
                        Toast.makeText(applicationContext, "Data masih kosong!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    for (i in 0 until jsonArray?.length()!!) {

                        val jsonObject = jsonArray?.optJSONObject(i)
                        arrayList.add(
                            Tanggapans(
                                jsonObject.getString("id_tanggapan"),
                                jsonObject.getString("id_pengaduan"),
                                jsonObject.getString("tanggapan"),
                                jsonObject.getString("tgl_tanggapan"),
                                jsonObject.getString("status"),
                                jsonObject.getString("id_petugas")
                            )
                        )

                        if (jsonArray?.length() - 1 == i) {

                            loading.dismiss()
                            val adapter =
                                RVAdapterTanggapan(
                                    applicationContext,
                                    arrayList
                                )
                            adapter.notifyDataSetChanged()
                            mRecyclerView.adapter = adapter

                        }

                    }
                }

                override fun onError(anError: ANError?) {
                    loading.dismiss()
                    Log.d("ONERROR", anError?.errorDetail.toString())
                    Toast.makeText(applicationContext, "Connection Failure", Toast.LENGTH_SHORT)
                        .show()
                }
            })
    }

    override fun onResume() {
        super.onResume()
        loadAllTanggapan()
    }
}