package com.example.myinstagramproject.fragment

import android.content.Intent
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
        binding = FragmentReelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val judul = arguments?.getString(AdapterPostingan.EXTRA_NAME)
        val photo = arguments?.getInt(AdapterPostingan.EXTRA_PHOTO)
        val video = arguments?.getInt(AdapterPostingan.EXTRA_VIDEO)

        binding.userReels.text = judul
        photo?.let {
            binding.ppReels.setImageResource(it)
        }
        video?.let {
            binding.videoku.setVideoPath("android.resource://${requireActivity().packageName}/$it")
            binding.videoku.start()
            binding.videoku.setOnPreparedListener { mp ->
                mp.isLooping = true
            }
            binding.videoku.setOnCompletionListener {
                binding.videoku.start()
            }
        }
    }
}