package com.kevin.aduin.petugas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.aduin.R
import kotlinx.android.synthetic.main.petugas_list.view.*

class RVAdapterPetugas(private val context: Context, private val arrayList: ArrayList<Petugass>) :
    RecyclerView.Adapter<RVAdapterPetugas.Holder>() {

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.petugas_list, parent, false)
        )
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.cvListPetugas.setOnClickListener {
            val i = Intent(context,
                ManagePetugasActivity::class.java)
            i.putExtra("editmode","1")
            i.putExtra("id_petugas",arrayList?.get(position)?.id_petugas)
            i.putExtra("nama_petugas",arrayList?.get(position)?.nama_petugas)
            i.putExtra("username",arrayList?.get(position)?.username)
            i.putExtra("password",arrayList?.get(position)?.password)
            i.putExtra("telp",arrayList?.get(position)?.telp)
            i.putExtra("role",arrayList?.get(position)?.role)
            context.startActivity(i)
        }
        holder.view.lbIdPetugasList.text = "ID Petugas : " + arrayList?.get(position)?.id_petugas
        holder.view.lbNamaList.text = "Nama : "+arrayList?.get(position)?.nama_petugas
        holder.view.lbUsernameList.text = "Username : "+arrayList?.get(position)?.username
        holder.view.lbPasswordList.text = "Password : "+arrayList?.get(position)?.password
        holder.view.lbTelpList.text = "Nomor Telepon : "+arrayList?.get(position)?.telp
        holder.view.lbRoleList.text = "Role : "+arrayList?.get(position)?.role
    }


}