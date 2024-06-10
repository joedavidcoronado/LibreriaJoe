package com.example.milibreria.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.milibreria.models.Genero
import com.example.milibreria.repositories.GeneroRepository

class RegistroGeneroViewModel: ViewModel() {
    private val _closeActivity: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val closeActivity: LiveData<Boolean> get() = _closeActivity
    private val _genero: MutableLiveData<Genero?> by lazy {
        MutableLiveData<Genero?>(null)
    }
    val genero2: LiveData<Genero?> get() = _genero


    fun saveGenero(nombre: String, id: Int) {
        val genero = Genero(
            nombre = nombre,
            id = id
        )

        if(id == -1){
            GeneroRepository.insertGenero(genero,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
        else if (id != 0) {
            genero.id = id
            GeneroRepository.updateGenero(genero,
                success = {
                    _closeActivity.value = true
                },
                failure = {
                    it.printStackTrace()
                })
        }
    }


    fun loadCategory(id: Int) {
        GeneroRepository.getGenero(id,
            success = {
                _genero.value = it
            },
            failure = {
                it.printStackTrace()
            })
    }
}