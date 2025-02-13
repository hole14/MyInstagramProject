package com.example.myinstagramproject.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstagramproject.R
import com.example.myinstagramproject.deskripsi.DeskripsiStatus
import de.hdodenhof.circleimageview.CircleImageView

class AdapterStatus(private val list: ArrayList<tampilan>): RecyclerView.Adapter<AdapterStatus.listViewHolder>() {

    interface itemDiklik{
        fun clikItem(data: tampilan)
    }

    private var click: itemDiklik? = null

    fun set(itemAja: itemDiklik){
        this.click = itemAja
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_STATUS = "extra_status"
    }

    class listViewHolder(lihat: View): RecyclerView.ViewHolder(lihat) {
        val judul: TextView = lihat.findViewById(R.id.judul)
        val pp: CircleImageView = lihat.findViewById(R.id.pp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listViewHolder {
        val tampil: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_pp, parent, false)
        return listViewHolder(tampil)
    }

    override fun onBindViewHolder(holder: listViewHolder, position: Int) {
        val (judul, image) = list[position]
        holder.judul.text = judul
        holder.pp.setImageResource(image)

        holder.itemView.setOnClickListener{
            click?.clikItem(list[holder.adapterPosition])
            val intent = Intent(holder.itemView.context, DeskripsiStatus::class.java)
            intent.putExtra(EXTRA_NAME, judul)
            intent.putExtra(EXTRA_PHOTO, image)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size
}
data class tampilan(
    val judul: String,
    val image: Int
)