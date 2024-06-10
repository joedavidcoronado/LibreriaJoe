package com.example.milibreria.repositories

import android.util.Log
import com.example.milibreria.api.APILibreriaService
import com.example.milibreria.models.Genero
import com.example.milibreria.models.Generos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GeneroRepository {
    fun getGeneroList(success: (Generos?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreriaService =
            retrofit.create(APILibreriaService::class.java)
        service.getGeneros().enqueue(object : Callback<Generos> {
            override fun onResponse(res: Call<Generos>, response: Response<Generos>) {
                val postList = response.body()
                success(postList)
            }

            override fun onFailure(res: Call<Generos>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun insertGenero(
        genero: Genero,
        success: (Genero) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreriaService =
            retrofit.create(APILibreriaService::class.java)
        service.insertGenero(genero).enqueue(object : Callback<Genero> {
            override fun onResponse(res: Call<Genero>, response: Response<Genero>) {
                val objGenero = response.body()
                success(objGenero!!)
            }

            override fun onFailure(res: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun getGenero(id: Int, success: (Genero?) -> Unit, failure: (Throwable) -> Unit) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreriaService =
            retrofit.create(APILibreriaService::class.java)
        service.getGeneroById(id).enqueue(object : Callback<Genero?> {
            override fun onResponse(res: Call<Genero?>, response: Response<Genero?>) {
                success(response.body())
            }

            override fun onFailure(res: Call<Genero?>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun updateGenero(
        genero: Genero,
        success: (Genero) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()

        val service: APILibreriaService =
            retrofit.create(APILibreriaService::class.java)
        val generoId = genero.id ?: 0
        service.updateGenero(genero, generoId).enqueue(object : Callback<Genero> {
            override fun onResponse(res: Call<Genero>, response: Response<Genero>) {
                val objGenero = response.body()!!
                success(objGenero)
            }

            override fun onFailure(res: Call<Genero>, t: Throwable) {
                failure(t)
            }
        })
    }

    fun deleteGenero(
        id: Int,
        success: () -> Unit,
        failure: (Throwable) -> Unit
    ) {
        val retrofit = RetrofitRepository.getRetrofitInstance()
        Log.d("Aca entro", "puede ser xd $id")
        val service: APILibreriaService =
            retrofit.create(APILibreriaService::class.java)
        service.deleteGenero(id).enqueue(object : Callback<Void> {
            override fun onResponse(res: Call<Void>, response: Response<Void>) {
                success()
            }

            override fun onFailure(res: Call<Void>, t: Throwable) {
                failure(t)
            }
        })
    }
}