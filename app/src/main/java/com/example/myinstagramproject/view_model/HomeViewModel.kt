package com.example.myinstagramproject.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myinstagramproject.adapter.tampilan

class HomeViewModel: ViewModel() {
    val listData = MutableLiveData<MutableList<tampilan>>()

    init {
        listData.value = mutableListOf()
    }
}