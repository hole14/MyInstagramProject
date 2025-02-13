package com.example.myinstagramproject.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstagramproject.R
import com.example.myinstagramproject.adapter.AdapterStatus
import com.example.myinstagramproject.adapter.tampilan
import com.example.myinstagramproject.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var status: RecyclerView
    private val list = ArrayList<tampilan>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        status = binding.recyclerPp
        status.setHasFixedSize(true)

            list.addAll(tampilin())
            daftarnya()

    }
    private fun daftarnya(){
        status.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            status.adapter = AdapterStatus(list)
    }

    private fun tampilin(): ArrayList<tampilan> {
        val dataJudul = resources.getStringArray(R.array.data_user)
        val dataPhoto = resources.obtainTypedArray(R.array.data_profile)
        val listTampil = ArrayList<tampilan>()
        for (i in dataJudul.indices) {
            val tampil = tampilan(dataJudul[i], dataPhoto.getResourceId(i, -1))
            listTampil.add(tampil)
        }
        return listTampil
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}