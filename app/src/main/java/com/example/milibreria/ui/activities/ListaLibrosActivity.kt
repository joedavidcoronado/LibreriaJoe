package com.example.milibreria.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.milibreria.R
import com.example.milibreria.databinding.ActivityListaLibrosBinding
import com.example.milibreria.models.Libro
import com.example.milibreria.models.Libros
import com.example.milibreria.ui.viewmodels.GeneroSSSSSSSSViewModel
import com.example.milibreria.ui.viewmodels.MainViewModel

class ListaLibrosActivity : AppCompatActivity() {
    var generoId: Int = 0
    var titulo: String = ""
    private val model: MainViewModel by viewModels()
    lateinit var binding: ActivityListaLibrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaLibrosBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupListaLibros()
        setupViewModelListener()
    }

    private fun setupViewModelListener() {
        /*model.libroList.observe(this){ libros ->
            var filteredLibros = generoId?.let { id->
                libros.filter { libro -> libro.generos.any { genero -> genero.id == id } }
            } ?: libros
            var filteredLibros = if (generoId != -1){
                libros.filter { libro -> libro.generos.any{ genero -> genero.id == generoId }
            } else {
                    libros
                }

            }

        }*/
    }

    private fun setupListaLibros() {
        generoId = intent.getIntExtra("generoId", -1)
        if(generoId != -1){
            titulo = "libros del genero $generoId"
        }
    }
}