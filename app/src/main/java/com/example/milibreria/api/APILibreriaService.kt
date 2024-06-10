package com.example.milibreria.api

import com.example.milibreria.models.Genero
import com.example.milibreria.models.Generos
import com.example.milibreria.models.Libro
import com.example.milibreria.models.Libros
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface APILibreriaService {
    @GET("generos")
    fun getGeneros(): Call<Generos>

    @GET("generos/{id}")
    fun getGeneroById(
        @Path("id") id: Int
    ): Call<Genero?>

    @POST("generos")
    fun insertGenero(
        @Body genero: Genero
    ): Call<Genero>

    @PUT("generos/{id}")
    fun updateGenero(
        @Body genero: Genero,
        @Path("id") id: Int
    ): Call<Genero>

    @DELETE("generos/{id}")
    fun deleteGenero(
        @Path("id") id: Int
    ): Call<Void>


////////////////////////////////////////////////////////


    @GET("libros")
    fun getLibros(): Call<Libros>

    @GET("libros/{id}")
    fun getLibroById(
        @Path("id") id: Int
    ): Call<Libro?>

    @DELETE("libros/{id}")
    fun deleteLibro(
        @Path("id") id: Int
    ): Call<Void>

    @POST("libros")
    fun insertLibro(
        @Body libro: Libro
    ): Call<Libro>

    @PUT("libros/{id}")
    fun updateLibro(
        @Body categoria: Libro,
        @Path("id") id: Int
    ): Call<Libro>
}