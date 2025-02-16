package com.example.myinstagramproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstagramproject.R
import com.example.myinstagramproject.adapter.AdapterSearch
import com.example.myinstagramproject.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: AdapterSearch
    private lateinit var binding: FragmentSearchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { textView, actionId, event->
                Toast.makeText(requireContext(), "Mencari: ${textView.text}", Toast.LENGTH_SHORT).show()
                searchBar.setText(searchView.text)
                searchView.hide()
                true
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = binding.searchResult
        recycler.setHasFixedSize(true)
        recycler.layoutManager = GridLayoutManager(requireContext(), 3)

        val hasil = tampilanSearch()

        adapter = AdapterSearch(hasil)
        recycler.adapter = adapter

    }

    private fun tampilanSearch(): MutableList<Int> {
        val foto = resources.obtainTypedArray(R.array.data_postingan_poto)
        val list = mutableListOf<Int>()
        for (i in 0 until foto.length()) {
            list.add(foto.getResourceId(i, -1))
        }
        return list
    }
}