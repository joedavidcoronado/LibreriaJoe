package com.example.milibreria.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.milibreria.models.Libros
import com.example.practiacapipersonas.repositories.LibroRepository

class MainViewModel : ViewModel() {
    private val _libroList: MutableLiveData<Libros> by lazy {
        MutableLiveData<Libros>(Libros())
    }
    val libroList: LiveData<Libros> get() = _libroList


    fun fetchListaLibros() {
        LibroRepository.getLibroList(
            success = { libros ->
                libros?.let {

                    _libroList.value = it
                }
            },
            failure = {
                it.printStackTrace()
            })
    }
}