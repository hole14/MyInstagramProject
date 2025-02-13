package com.example.myinstagramproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstagramproject.R
import com.example.myinstagramproject.adapter.AdapterStatus
import com.example.myinstagramproject.adapter.tampilan
import com.example.myinstagramproject.databinding.FragmentHomeBinding
import com.example.myinstagramproject.view_model.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var status: RecyclerView
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        status = binding.recyclerPp
        status.setHasFixedSize(true)
        status.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val adapter = AdapterStatus(mutableListOf())
        status.adapter = adapter

        if (viewModel.listData.value.isNullOrEmpty()){
            viewModel.listData.value = tampilin()
        }
        viewModel.listData.observe(viewLifecycleOwner){ list ->
            adapter.updateData(list)
        }
    }

    private fun tampilin(): MutableList<tampilan> {
        val dataJudul = resources.getStringArray(R.array.data_user)
        val dataPhoto = resources.obtainTypedArray(R.array.data_profile)
        val dataPoto = resources.obtainTypedArray(R.array.data_status)
        val listTampil = mutableListOf<tampilan>()
        for (i in dataJudul.indices) {
            val tampil = tampilan(dataJudul[i], dataPhoto.getResourceId(i, -1), dataPoto.getResourceId(i, -1))
            listTampil.add(tampil)
        }
        return listTampil
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}