package com.kevin.aduin.masyarakat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kevin.aduin.R
import kotlinx.android.synthetic.main.masyarakat_list.view.*

class RVAdapterMasyarakat(
    private val context: Context,
    private val arrayList: ArrayList<Masyarakats>
) :
    RecyclerView.Adapter<RVAdapterMasyarakat.Holder>() {

    class Holder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(
                parent.context
            ).inflate(R.layout.masyarakat_list, parent, false)
        )
    }

    override fun getItemCount(): Int = arrayList!!.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.view.cvListMasyarakat.setOnClickListener {
            val i = Intent(
                context,
                ManageMasyarakatActivity::class.java
            )
            i.putExtra("editmode", "1")
            i.putExtra("nik", arrayList?.get(position)?.nik)
            i.putExtra("nama", arrayList?.get(position)?.nama)
            i.putExtra("username", arrayList?.get(position)?.username)
            i.putExtra("password", arrayList?.get(position)?.password)
            i.putExtra("telp", arrayList?.get(position)?.telp)
            context.startActivity(i)
        }
        holder.view.lbNikList.text = "NIK : " + arrayList?.get(position)?.nik
        holder.view.lbNamaList.text = "Nama : " + arrayList?.get(position)?.nama
        holder.view.lbUsernameList.text = "Username : " + arrayList?.get(position)?.username
        holder.view.lbPasswordList.text = "Password : " + arrayList?.get(position)?.password
        holder.view.lbTelpList.text = "Nomor Telepon : " + arrayList?.get(position)?.telp
    }


}