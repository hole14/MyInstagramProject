package com.example.myinstagramproject.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstagramproject.R
import com.example.myinstagramproject.adapter.AdapterPostingan
import com.example.myinstagramproject.adapter.AdapterStatus
import com.example.myinstagramproject.adapter.postingan
import com.example.myinstagramproject.adapter.tampilan
import com.example.myinstagramproject.databinding.FragmentHomeBinding
import com.example.myinstagramproject.view_model.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var border: ImageView

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var status: RecyclerView
    private lateinit var posting: RecyclerView

    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        status = binding.recyclerPp
        status.setHasFixedSize(true)
        status.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter = AdapterStatus(mutableListOf())
        status.adapter = adapter

        viewModel.listData.value = tampilin()

        viewModel.listData.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        posting = binding.recyclerPosting
        val adapterPosting = AdapterPostingan(mutableListOf())
        posting.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            posting.adapter = adapterPosting
        }
        viewModel.listVideo.value = tampilinVideo()
        viewModel.listVideo.observe(viewLifecycleOwner) { list ->
            adapterPosting.list.apply {
                clear()
                addAll(list)
            }
            adapterPosting.notifyDataSetChanged()
        }
    }

    private fun tampilinVideo(): MutableList<postingan> {
        val dataJudul = resources.getStringArray(R.array.data_user)
        val dataPhoto = resources.obtainTypedArray(R.array.data_profile)
        val dataVideo = resources.obtainTypedArray(R.array.data_postingan)

        val listTampil = mutableListOf<postingan>()
        for (i in dataJudul.indices) {
            val tampil = postingan(dataJudul[i], dataPhoto.getResourceId(i, -1), dataVideo.getResourceId(i, -1))
            listTampil.add(tampil)
        }
        dataVideo.recycle()
        dataPhoto.recycle()
        return listTampil
    }

    private fun tampilin(): MutableList<tampilan> {
        val dataJudul = resources.getStringArray(R.array.data_user)
        val dataPhoto = resources.obtainTypedArray(R.array.data_profile)
        val dataPoto = resources.obtainTypedArray(R.array.data_status)

        return List(dataJudul.size){ i ->
            tampilan(dataJudul[i], dataPhoto.getResourceId(i, -1), dataPoto.getResourceId(i, -1))
        }.toMutableList().also {
            dataPhoto.recycle()
            dataPoto.recycle()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}