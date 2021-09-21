package com.kevin.aduin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kevin.aduin.pengaduan.PengaduanActivity
import kotlinx.android.synthetic.main.activity_beranda_masyarakat.*

class BerandaMasyarakatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda_masyarakat)

        val nik = intent.getStringExtra("nik")
        if (nik == "35465230832020001") {
            btn_pengaduan.setOnClickListener {
                startActivity(Intent(this, PengaduanActivity::class.java))
            }
        } else if (nik == "35465230832020002") {
            btn_pengaduan.setOnClickListener {
                startActivity(Intent(this, PengaduanActivity::class.java))
            }
        }
    }
}