package com.example.myinstagramproject.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myinstagramproject.adapter.AdapterPostingan
import com.example.myinstagramproject.adapter.postingan
import com.example.myinstagramproject.adapter.tampilan

class HomeViewModel: ViewModel() {

    val listData = MutableLiveData<MutableList<tampilan>>()
    val listVideo = MutableLiveData<MutableList<postingan>>()

    init {
        listData.value = mutableListOf()
        listVideo.value = mutableListOf()
    }
}