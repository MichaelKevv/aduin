package com.kevin.aduin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_halaman_awal.*

class HalamanAwalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_halaman_awal)

        btn_login_petugas.setOnClickListener {
            startActivity(Intent(this, LoginPetugasActivity::class.java))
        }

        btn_login_masyarakat.setOnClickListener {
            startActivity(Intent(this, LoginMasyarakatActivity::class.java))
        }
    }
}