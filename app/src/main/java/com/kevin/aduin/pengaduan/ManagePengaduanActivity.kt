package com.kevin.aduin.pengaduan

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
import kotlinx.android.synthetic.main.activity_manage_pengaduan.*
import org.json.JSONObject

class ManagePengaduanActivity : AppCompatActivity() {
    lateinit var i: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_pengaduan)
        i = intent


        if (i.hasExtra("editmode")) {

            if (i.getStringExtra("editmode").equals("1")) {

                onEditMode()

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

        txtIdPengaduan.setText(i.getStringExtra("id_pengaduan"))
        txtNIK.setText(i.getStringExtra("nik"))
        txtNama.setText(i.getStringExtra("nama"))
        txtTelp.setText(i.getStringExtra("telp"))
        txtSubjek.setText(i.getStringExtra("subjek"))
        txtIsiLaporan.setText(i.getStringExtra("isi_laporan"))
        txtTgl.setText(i.getStringExtra("tgl_pengaduan"))
        txtStatus.setText(i.getStringExtra("status"))
        txtIdPengaduan.isEnabled = false

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE

    }

    private fun create() {
        val loading = ProgressDialog(this)
        loading.setMessage("Mengirim Laporan...")
        loading.show()

        AndroidNetworking.post(ApiEndPointPengaduan.CREATE)
            .addBodyParameter("id_pengaduan", txtIdPengaduan.text.toString())
            .addBodyParameter("nik", txtNIK.text.toString())
            .addBodyParameter("nama", txtNama.text.toString())
            .addBodyParameter("telp", txtTelp.text.toString())
            .addBodyParameter("subjek", txtSubjek.text.toString())
            .addBodyParameter("isi_laporan", txtIsiLaporan.text.toString())
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
                        this@ManagePengaduanActivity.finish()
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
        loading.setMessage("Mengubah data...")
        loading.show()

        AndroidNetworking.post(ApiEndPointPengaduan.UPDATE)
            .addBodyParameter("id_pengaduan", txtIdPengaduan.text.toString())
            .addBodyParameter("nik", txtNIK.text.toString())
            .addBodyParameter("nama", txtNama.text.toString())
            .addBodyParameter("telp", txtTelp.text.toString())
            .addBodyParameter("subjek", txtSubjek.text.toString())
            .addBodyParameter("isi_laporan", txtIsiLaporan.text.toString())
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
                        this@ManagePengaduanActivity.finish()
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

        AndroidNetworking.get(ApiEndPointPengaduan.DELETE + "?id_pengaduan=" + txtIdPengaduan.text.toString())
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
                        this@ManagePengaduanActivity.finish()
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