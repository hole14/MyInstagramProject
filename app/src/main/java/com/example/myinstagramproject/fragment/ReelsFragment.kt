package com.example.myinstagramproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import com.example.myinstagramproject.R
import com.example.myinstagramproject.adapter.AdapterPostingan
import com.example.myinstagramproject.databinding.FragmentReelsBinding
import de.hdodenhof.circleimageview.CircleImageView

class ReelsFragment : Fragment() {
    private lateinit var binding: FragmentReelsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReelsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val judul = arguments?.getString(AdapterPostingan.EXTRA_NAME)
        val photo = arguments?.getInt(AdapterPostingan.EXTRA_PHOTO)
        val video = arguments?.getInt(AdapterPostingan.EXTRA_VIDEO)

        val judulReels: TextView = view.findViewById(R.id.user_reels)
        val ppReels: CircleImageView = view.findViewById(R.id.pp_reels)
        val videoReels: VideoView = view.findViewById(R.id.videoku)

        judulReels.text = judul
        ppReels.setImageResource(photo!!)
        videoReels.setVideoPath("android.resource://${requireActivity().packageName}/$video")

        videoReels.start()
        videoReels.setOnPreparedListener { mp ->
            mp.isLooping = true
        }
        videoReels.setOnCompletionListener {
            videoReels.start()
        }
    }
}