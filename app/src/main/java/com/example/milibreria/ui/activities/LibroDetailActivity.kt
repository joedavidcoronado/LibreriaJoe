package com.example.milibreria.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.milibreria.R
import com.example.milibreria.databinding.ActivityLibroDetailBinding
import com.example.milibreria.ui.viewmodels.LibroDetailViewModel

class LibroDetailActivity : AppCompatActivity() {
    var id: Int = 0
    lateinit var binding: ActivityLibroDetailBinding
    private val model: LibroDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLibroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

        id = intent.getIntExtra("libroId", -1)
        if (id != -1) {
            model.loadCategory(id)
        }
    }

    private fun setupViewModelObservers() {
        model.closeActivity.observe(this) {
            if (it) {
                finish()
            }
        }
        model.libro.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.lblLibroName.editText?.setText(it.nombre)
            binding.lblLibroAutor.editText?.setText(it.autor)
            binding.lblLibroCalificacion.editText?.setText(it.calificacion)
            binding.lblLibroImagen.editText?.setText(it.imagen)
            binding.lblLibroISBN.editText?.setText(it.isbn)
            binding.lblLibroEditorial.editText?.setText(it.editorial)
            binding.lblLibroSinopsis.editText?.setText(it.sinopsis)
        }
    }

    private fun setupEventListeners() {
        binding.btnSaveLibro.setOnClickListener {
            model.saveLibro(
                binding.lblLibroName.editText?.text.toString(),
                binding.lblLibroAutor.editText?.text.toString(),
                binding.lblLibroCalificacion.editText?.text.toString(),
                binding.lblLibroImagen.editText?.text.toString(),
                binding.lblLibroISBN.editText?.text.toString(),
                binding.lblLibroEditorial.editText?.text.toString(),
                binding.lblLibroSinopsis.editText?.text.toString(),
                id
            )
        }
    }
}