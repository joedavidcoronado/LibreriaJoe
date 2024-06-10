package com.example.milibreria.ui.activities


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.milibreria.R
import com.example.milibreria.databinding.ActivityMainBinding
import com.example.milibreria.models.Libro
import com.example.milibreria.models.Libros
import com.example.milibreria.ui.viewmodels.MainViewModel
import com.example.practiacapipersonas.repositories.LibroRepository
import com.example.practicaapipersonas.ui.adapters.LibroAdapter

class MainActivity : AppCompatActivity(), LibroAdapter.OnLibroClickListener {
    lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupRecyclerView()
        setupViewModelListeners()
    }


    override fun onResume() {
        super.onResume()
        model.fetchListaLibros()
    }

    private fun setupEventListeners() {
        binding.fabAddLibro.setOnClickListener {
            val intent = Intent(this, LibroDetailActivity::class.java)
            startActivity(intent)
        }
        binding.fabGoToGeneros.setOnClickListener {
            val intent = Intent(this, GeneroActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupViewModelListeners() {
        model.libroList.observe(this) {
            val adapter = (binding.lstLibros.adapter as LibroAdapter)
            adapter.updateData(it)
        }
    }


    private fun setupRecyclerView() {
        binding.lstLibros.apply {
            this.adapter = LibroAdapter(Libros(), this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun onLibroClick(libro: Libro) {
        val intent = Intent(this, LibroDetailActivity::class.java)
        intent.putExtra("libroId", libro.id)
        startActivity(intent)
    }

    override fun onLibroDelete(libro: Libro) {
        LibroRepository.deleteLibro(libro.id!!,
            success = {
                model.fetchListaLibros()
            },
            failure = {
                it.printStackTrace()
            })
    }
}