package com.example.milibreria.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milibreria.R
import com.example.milibreria.databinding.ActivityGeneroBinding
import com.example.milibreria.databinding.ActivityGeneroBinding.*
import com.example.milibreria.models.Genero
import com.example.milibreria.repositories.GeneroRepository
import com.example.milibreria.ui.viewmodels.GeneroSSSSSSSSViewModel
import com.example.practicaapipersonas.ui.adapters.GeneroAdapter

class GeneroActivity : AppCompatActivity(), GeneroAdapter.OnGeneroClickListener {
    lateinit var binding: ActivityGeneroBinding
    private val model: GeneroSSSSSSSSViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListener()
        setupRecyclerView()
        setupViewModelObservers()
    }

    override fun onResume() {
        super.onResume()
        model.fetchListaGeneros()
    }

    private fun setupEventListener() {
        binding.fabAddGenero.setOnClickListener {
            val intent = Intent(this, GeneroDetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        binding.lstGeneros.apply {
            layoutManager = LinearLayoutManager(this@GeneroActivity)
            adapter = GeneroAdapter(arrayListOf(), this@GeneroActivity)
        }
    }

    private fun setupViewModelObservers() {
        model.generoList.observe(this) {
            binding.lstGeneros.adapter = GeneroAdapter(it, this)

        }
    }


    override fun onGeneroClick(genero: Genero) {
        val intent = Intent(this, ListaLibrosActivity::class.java)
        intent.putExtra("generoId", genero.id)
        startActivity(intent)
    }

    override fun onGeneroDelete(genero: Genero) {
        Log.d("Aca entro igual", "puede ser xd $genero")
        GeneroRepository.deleteGenero(genero.id!!,
            success = {
                model.fetchListaGeneros()
            },
            failure = {
                it.printStackTrace()
            })
    }
}