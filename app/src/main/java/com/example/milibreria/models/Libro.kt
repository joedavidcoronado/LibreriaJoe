package com.example.milibreria.models

typealias Libros = ArrayList<Libro>

data class Libro(
    var nombre: String,
    var autor: String,
    var editorial: String,
    var imagen: String,
    var sinopsis: String,
    var isbn: String,
    var calificacion: String
){
    var id: Int? = null
    var generos: List<Genero> = emptyList()
}

