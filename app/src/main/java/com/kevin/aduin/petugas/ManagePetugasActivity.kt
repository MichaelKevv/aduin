package com.kevin.aduin.petugas

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
import kotlinx.android.synthetic.main.activity_manage_petugas.*
import org.json.JSONObject


class ManagePetugasActivity : AppCompatActivity() {
    lateinit var i: Intent
    private var role = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_petugas)
        i = intent

        if (i.hasExtra("editmode")) {

            if (i.getStringExtra("editmode").equals("1")) {

                onEditMode()

            }

        }

        rgRole.setOnCheckedChangeListener { radioGroup, i ->

            when (i) {

                R.id.radioAdmin -> {
                    role = "Admin"
                }

                R.id.radioPetugas -> {
                    role = "Petugas"
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

        txtIdPetugas.setText(i.getStringExtra("id_petugas"))
        txtNamaPetugas.setText(i.getStringExtra("nama_petugas"))
        txtUsername.setText(i.getStringExtra("username"))
        txtPassword.setText(i.getStringExtra("password"))
        txtTelp.setText(i.getStringExtra("telp"))
        txtIdPetugas.isEnabled = false

        btnCreate.visibility = View.GONE
        btnUpdate.visibility = View.VISIBLE
        btnDelete.visibility = View.VISIBLE

        if (role.equals("Admin")) {
            rgRole.check(R.id.radioAdmin)
        } else {
            rgRole.check(R.id.radioPetugas)
        }

    }

    private fun create() {
        val loading = ProgressDialog(this)
        loading.setMessage("Menambahkan data...")
        loading.show()

        AndroidNetworking.post(ApiEndPointP.CREATE)
            .addBodyParameter("id_petugas", txtIdPetugas.text.toString())
            .addBodyParameter("nama_petugas", txtNamaPetugas.text.toString())
            .addBodyParameter("username", txtUsername.text.toString())
            .addBodyParameter("password", txtPassword.text.toString())
            .addBodyParameter("telp", txtTelp.text.toString())
            .addBodyParameter("role", role)
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
                        this@ManagePetugasActivity.finish()
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

        AndroidNetworking.post(ApiEndPointP.UPDATE)
            .addBodyParameter("id_petugas", txtIdPetugas.text.toString())
            .addBodyParameter("nama_petugas", txtNamaPetugas.text.toString())
            .addBodyParameter("username", txtUsername.text.toString())
            .addBodyParameter("password", txtPassword.text.toString())
            .addBodyParameter("telp", txtTelp.text.toString())
            .addBodyParameter("role", role)
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
                        this@ManagePetugasActivity.finish()
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

        AndroidNetworking.get(ApiEndPointP.DELETE + "?id_petugas=" + txtIdPetugas.text.toString())
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
                        this@ManagePetugasActivity.finish()
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