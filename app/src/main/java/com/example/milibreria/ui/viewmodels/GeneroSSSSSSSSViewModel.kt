package com.example.milibreria.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.milibreria.models.Generos
import com.example.milibreria.repositories.GeneroRepository

class GeneroSSSSSSSSViewModel: ViewModel() {
    private val _generoList: MutableLiveData<Generos> by lazy {
        MutableLiveData<Generos>(Generos())
    }
    val generoList: LiveData<Generos> get() = _generoList

    fun fetchListaGeneros() {
        GeneroRepository.getGeneroList(
            success = { generos ->
                generos?.let {
                    _generoList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            }
        )
    }
}