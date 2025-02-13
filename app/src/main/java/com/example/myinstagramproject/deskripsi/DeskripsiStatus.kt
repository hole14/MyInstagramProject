package com.example.myinstagramproject.deskripsi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myinstagramproject.R
import com.example.myinstagramproject.adapter.AdapterStatus
import de.hdodenhof.circleimageview.CircleImageView

class DeskripsiStatus : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deskripsi_status)

        supportActionBar?.hide()

        val judul = intent.getStringExtra(AdapterStatus.EXTRA_NAME)
        val photo = intent.getIntExtra(AdapterStatus.EXTRA_PHOTO, 0)

        val user: TextView = findViewById(R.id.textView)
        val profile: CircleImageView = findViewById(R.id.circleImageView2)
        val status: ImageView = findViewById(R.id.image_status)

        user.text = judul
        profile.setImageResource(photo)
        status.setImageResource(photo)

        val share: ImageView = findViewById(R.id.share)

        share.setOnClickListener{
            val bagikan = Intent(Intent.ACTION_SEND)
            bagikan.putExtra(Intent.EXTRA_TEXT, "Saya sedang melihat $judul")
            bagikan.type = "text/plain"
            startActivity(Intent.createChooser(bagikan, "Bagikan"))
        }

        val like: ImageView = findViewById(R.id.like)
        var ini = false

        like.setOnClickListener{
            ini = !ini
            like.isSelected = ini
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}