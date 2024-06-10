package com.example.milibreria.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.milibreria.models.Libro
import com.example.practiacapipersonas.repositories.LibroRepository

class LibroDetailViewModel: ViewModel() {
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    private val _libro: MutableLiveData<Libro?> by lazy {
        MutableLiveData<Libro?>(null)
    }
    val libro: LiveData<Libro?> get() = _libro


    fun saveLibro(nombre: String, autor: String, calificacion: String, imagen: String, isbn: String, editorial: String, sinopsis: String, id: Int) {
        val libro = Libro(
            nombre = nombre,
            autor = autor,
            editorial = editorial,
            imagen = imagen,
            sinopsis = sinopsis,
            isbn = isbn,
            calificacion = calificacion
        )

        if(id == -1){
            LibroRepository.insertLibro(libro,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
        else if (id != 0) {
            libro.id = id
            LibroRepository.updateLibro(libro,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }


    fun loadCategory(id: Int) {
        LibroRepository.getLibro(id,
            success = {
                _libro.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }
}