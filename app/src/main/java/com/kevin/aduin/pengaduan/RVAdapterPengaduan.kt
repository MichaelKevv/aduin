package com.kevin.aduin.pengaduan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.aduin.R
import kotlinx.android.synthetic.main.pengaduan_list.view.*


class RVAdapterPengaduan(private val context: Context, private val arrayList: ArrayList<Pengaduans>) :
    RecyclerView.Adapter<RVAdapterPengaduan.Holder>() {

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterPengaduan.Holder {
        return RVAdapterPengaduan.Holder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.pengaduan_list, parent, false)
        )
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: RVAdapterPengaduan.Holder, position: Int) {
        holder.view.cvListPengaduan.setOnClickListener {
            val i = Intent(context,
                ManagePengaduanActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("id_pengaduan",arrayList?.get(position)?.id_pengaduan)
            i.putExtra("nik",arrayList?.get(position)?.nik)
            i.putExtra("nama",arrayList?.get(position)?.nama)
            i.putExtra("telp",arrayList?.get(position)?.telp)
            i.putExtra("subjek",arrayList?.get(position)?.subjek)
            i.putExtra("isi_laporan",arrayList?.get(position)?.isi_laporan)
            i.putExtra("tgl_pengaduan",arrayList?.get(position)?.tgl_pengaduan)
            i.putExtra("status",arrayList?.get(position)?.status)
            context.startActivity(i)
        }
        holder.view.lbIdPengaduanList.text = "ID Pengaduan : " + arrayList?.get(position)?.id_pengaduan
        holder.view.lbNikList.text = "NIK : "+arrayList?.get(position)?.nik
        holder.view.lbNamaList.text = "Nama : "+arrayList?.get(position)?.nama
        holder.view.lbTelpList.text = "Nomor Telepon : "+arrayList?.get(position)?.telp
        holder.view.lbSubjekList.text = "Subjek Laporan : "+arrayList?.get(position)?.subjek
        holder.view.lbIsiList.text = "Isi Laporan : "+arrayList?.get(position)?.isi_laporan
        holder.view.lbTglList.text = "Tanggal Pengaduan : "+arrayList?.get(position)?.tgl_pengaduan
        holder.view.lbStatusList.text = "Status : "+arrayList?.get(position)?.status
    }
}