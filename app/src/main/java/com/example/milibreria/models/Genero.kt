package com.example.milibreria.models

typealias Generos = ArrayList<Genero>

data class Genero(
    val nombre: String,
    var id: Int
){

    val createdAt: String? = null
    val updatedAt: String? = null
}
