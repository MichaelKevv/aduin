package com.kevin.aduin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login_petugas.*

class LoginPetugasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_petugas)

        btnLoginP.setOnClickListener {
            val username = inputUsername.text.toString()
            val pass = inputPassword.text.toString()
            if (username.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Masukkan Username atau Password anda", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (username == "admin" && pass == "admin") {
                val loading = ProgressDialog(this)
                loading.setMessage("Login in...")
                loading.show()
                val i = Intent(this, BerandaActivity::class.java)
                i.putExtra("role", "admin")
                startActivity(i)
            } else if (username == "petugas" && pass == "petugas") {
                val loading = ProgressDialog(this)
                loading.setMessage("Login in...")
                loading.show()
                val i = Intent(this, BerandaActivity::class.java)
                i.putExtra("role", "petugas")
                startActivity(i)
            } else {
                Toast.makeText(this, "Username atau Password anda salah", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

        }
    }
}