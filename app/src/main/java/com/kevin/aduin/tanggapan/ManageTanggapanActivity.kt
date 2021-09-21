package com.kevin.aduin.tanggapan

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.kevin.aduin.R
import kotlinx.android.synthetic.main.activity_manage_tanggapan.*
import org.json.JSONObject

class ManageTanggapanActivity : AppCompatActivity() {
    lateinit var i: Intent
    private var status = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_tanggapan)
        i = intent
        if (i.hasExtra("editmode")) {
            if (i.getStringExtra("editmode").equals("1")) {
                onEditMode()
            }
        }
        rgStatus.setOnCheckedChangeListener { radioGroup, i ->
            when (i) {
                R.id.radioP -> {
                    status = "Proses"
                }
                R.id.radioS -> {
                    status = "Selesai"
                }
            }
        }
        btnCreate.setOnClickListener {
            create()
        }
        btnUpdate.setOnClickListener {
            update()
        }
        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi")
                .setMessage("Hapus data ini ?")
                .setPositiveButton("HAPUS", DialogInterface.OnClickListener { dialogInterface, i ->
                    delete()
                })
                .setNegativeButton("BATAL", DialogInterface.OnClickListener { dialogInterface, i ->
                    dialogInterface.dismiss()
                })
                .show()
        }
    }

    private fun onEditMode() {
        txtIdTanggapan.setText(i.getStringExtra("id_tanggapan"))
        txtIdPengaduan.setText(i.getStringExtra("id_pengaduan"))
        txtTanggapan.setText(i.getStringExtra("tanggapan"))
        txtTglTanggapan.setText(i.getStringExtra("tgl_tanggapan"))
        txtIdPetugas.setText(i.getStringExtra("id_petugas"))
        txtTglTanggapan.isEnabled = false

        if (status.equals("Proses")) {
            rgStatus.check(R.id.radioP)
        } else {
            rgStatus.check(R.id.radioS)
        }

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE
    }

    private fun create() {
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPointT.CREATE)
            .addBodyParameter("id_tanggapan", txtIdTanggapan.text.toString())
            .addBodyParameter("id_pengaduan", txtIdPengaduan.text.toString())
            .addBodyParameter("tanggapan", txtTanggapan.text.toString())
            .addBodyParameter("tgl_tanggapan", txtTglTanggapan.text.toString())
            .addBodyParameter("id_petugas", txtIdPetugas.text.toString())
            .addBodyParameter("status", status)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(
                        applicationContext,
                        response?.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                    if (response?.getString("message")?.contains("successfully")!!) {
                        this@ManageTanggapanActivity.finish()
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

    private fun update() {
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPointT.UPDATE)
            .addBodyParameter("id_tanggapan", txtIdTanggapan.text.toString())
            .addBodyParameter("id_pengaduan", txtIdPengaduan.text.toString())
            .addBodyParameter("tanggapan", txtTanggapan.text.toString())
            .addBodyParameter("tgl_tanggapan", txtTglTanggapan.text.toString())
            .addBodyParameter("id_petugas", txtIdPetugas.text.toString())
            .addBodyParameter("status", status)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    loading.dismiss()
                    Toast.makeText(
                        applicationContext,
                        response?.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()
                    if (response?.getString("message")?.contains("successfully")!!) {
                        this@ManageTanggapanActivity.finish()
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

    private fun delete() {
        val loading = ProgressDialog(this)
        loading.setMessage("Menghapus data...")
        loading.show()

        AndroidNetworking.get(ApiEndPointT.DELETE + "?id_tanggapan=" + txtIdTanggapan.text.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject?) {

                    loading.dismiss()
                    Toast.makeText(
                        applicationContext,
                        response?.getString("message"),
                        Toast.LENGTH_SHORT
                    ).show()

                    if (response?.getString("message")?.contains("successfully")!!) {
                        this@ManageTanggapanActivity.finish()
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
}