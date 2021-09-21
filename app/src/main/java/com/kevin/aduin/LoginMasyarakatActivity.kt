package com.kevin.aduin

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login_masyarakat.*

class LoginMasyarakatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_masyarakat)
        btnLoginM.setOnClickListener {
            val username = inputUsername.text.toString()
            val pass = inputPassword.text.toString()
            if (username.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Masukkan Username atau Password anda", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else if (username == "orang1" && pass == "orang1") {
                val loading = ProgressDialog(this)
                loading.setMessage("Login in...")
                loading.show()
                val intent = Intent(this, BerandaMasyarakatActivity::class.java)
                intent.putExtra("nik", "35465230832020001")
                startActivity(intent)
            } else if (username == "orang2" && pass == "orang2") {
                val loading = ProgressDialog(this)
                loading.setMessage("Login in...")
                loading.show()
                val intent = Intent(this, BerandaMasyarakatActivity::class.java)
                intent.putExtra("nik", "35465230832020002")
                startActivity(intent)
            } else {
                Toast.makeText(this, "Username atau Password anda salah", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

        }
    }
}