package com.kevin.aduin

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kevin.aduin.masyarakat.MasyarakatActivity
import com.kevin.aduin.pengaduan.PengaduanActivity

import com.kevin.aduin.petugas.PetugasActivity
import com.kevin.aduin.tanggapan.TanggapanActivity

import kotlinx.android.synthetic.main.activity_beranda.*

class BerandaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        val role = intent.getStringExtra("role")
        if (role == "admin") {
            Admin()
        } else if (role == "petugas") {
            Petugas()
        }
    }

    private fun Admin() {
        btn_petugas.visibility = View.VISIBLE
        btn_masyarakat.visibility = View.VISIBLE
        btn_tanggapan.visibility = View.VISIBLE
        btn_pengaduan.visibility = View.VISIBLE

        btn_petugas.setOnClickListener {
            startActivity(Intent(this, PetugasActivity::class.java))
        }
        btn_masyarakat.setOnClickListener {
            startActivity(Intent(this, MasyarakatActivity::class.java))
        }
        btn_tanggapan.setOnClickListener {
            startActivity(Intent(this, TanggapanActivity::class.java))
        }
        btn_pengaduan.setOnClickListener {
            startActivity(Intent(this, PengaduanActivity::class.java))
        }

    }

    private fun Petugas() {
        btn_petugas.visibility = View.GONE
        btn_masyarakat.visibility = View.VISIBLE
        btn_pengaduan.visibility = View.VISIBLE
        btn_tanggapan.visibility = View.VISIBLE

        btn_masyarakat.setOnClickListener {
            startActivity(Intent(this, MasyarakatActivity::class.java))
        }
        btn_tanggapan.setOnClickListener {
            startActivity(Intent(this, TanggapanActivity::class.java))
        }
        btn_pengaduan.setOnClickListener {
            startActivity(Intent(this, PengaduanActivity::class.java))
        }
    }
}