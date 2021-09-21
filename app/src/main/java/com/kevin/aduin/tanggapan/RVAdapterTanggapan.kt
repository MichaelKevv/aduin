package com.kevin.aduin.tanggapan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.aduin.R
import kotlinx.android.synthetic.main.tanggapan_list.view.*

class RVAdapterTanggapan(private val context: Context, private val arrayList: ArrayList<Tanggapans>) :
    RecyclerView.Adapter<RVAdapterTanggapan.Holder>() {

    class Holder(val view: View) : RecyclerView.ViewHolder(view)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapterTanggapan.Holder {
        return RVAdapterTanggapan.Holder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.tanggapan_list, parent, false)
        )
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: RVAdapterTanggapan.Holder, position: Int) {
        holder.view.cvListTanggapan.setOnClickListener {
            val i = Intent(context,
                ManageTanggapanActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("id_tanggapan",arrayList?.get(position)?.id_tanggapan)
            i.putExtra("id_pengaduan",arrayList?.get(position)?.id_pengaduan)
            i.putExtra("tanggapan",arrayList?.get(position)?.tanggapan)
            i.putExtra("tgl_tanggapan",arrayList?.get(position)?.tgl_tanggapan)
            i.putExtra("id_petugas",arrayList?.get(position)?.id_petugas)
            context.startActivity(i)
        }
        holder.view.lbIdTanggapanList.text = "ID Tanggapan : "+arrayList?.get(position)?.id_tanggapan
        holder.view.lbIdPengaduanList.text = "ID Pengaduan : " + arrayList?.get(position)?.id_pengaduan
        holder.view.lbTanggapanList.text = "Tanggapan : "+arrayList?.get(position)?.tanggapan
        holder.view.lbTglList.text = "Nomor Telepon : "+arrayList?.get(position)?.tgl_tanggapan
        holder.view.lbIdPetugasList.text = "ID Petugas : "+arrayList?.get(position)?.id_petugas
    }
}
