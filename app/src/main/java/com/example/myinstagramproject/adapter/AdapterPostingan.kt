package com.example.myinstagramproject.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstagramproject.R
import com.example.myinstagramproject.fragment.ReelsFragment
import de.hdodenhof.circleimageview.CircleImageView

class AdapterPostingan(var list: MutableList<postingan>): RecyclerView.Adapter<AdapterPostingan.listViewHolder>() {
    interface itemDiklik{
        fun clikItem(data: postingan)
    }

    private var click: itemDiklik? = null

    fun set(itemAja: itemDiklik){
        this.click = itemAja
    }

    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_VIDEO = "extra_video"
    }
    inner class listViewHolder(posting: View): RecyclerView.ViewHolder(posting) {
        val judul: TextView = posting.findViewById(R.id.judul_lagi)
        val pp: CircleImageView = posting.findViewById(R.id.circleImageView3)
        val video: VideoView = posting.findViewById(R.id.postingan)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): listViewHolder {
        val tampil: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_postingan, parent, false)
        return listViewHolder(tampil)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: listViewHolder, position: Int) {
        val (judul, image, video) = list[position]
        val uri = Uri.parse("android.resource://${holder.itemView.context.packageName}/$video")
        holder.judul.text = judul
        holder.pp.setImageResource(image)
        holder.video.setVideoURI(uri)

        holder.video.start()
        holder.video.setOnPreparedListener { mp ->
            mp.isLooping = true
        }
        holder.video.setOnCompletionListener {
            holder.video.start()
        }

        holder.video.setOnClickListener {
            click?.clikItem(list[holder.adapterPosition])
            val intent = Intent(holder.itemView.context, ReelsFragment::class.java)
            intent.putExtra(EXTRA_NAME, judul)
            intent.putExtra(EXTRA_PHOTO, image)
            intent.putExtra(EXTRA_VIDEO, video)
            holder.itemView.context.startActivity(intent)
        }

    }

}
data class postingan(
    val judul: String,
    val image: Int,
    val video: Int

)