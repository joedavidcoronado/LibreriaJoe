package com.example.milibreria.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.milibreria.R
import com.example.milibreria.databinding.ActivityGeneroDetailBinding
import com.example.milibreria.ui.viewmodels.RegistroGeneroViewModel

class GeneroDetailActivity : AppCompatActivity() {
    var id: Int = 0
    lateinit var binding: ActivityGeneroDetailBinding
    private val model: RegistroGeneroViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGeneroDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setupEventListeners()
        setupViewModelObservers()

        id = intent.getIntExtra("gneroId", -1)
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
        model.genero2.observe(this) {
            if (it == null) {
                return@observe
            }
            binding.lblGeneroName.editText?.setText(it.nombre)
        }
    }

    private fun setupEventListeners() {
        binding.btnSaveGenero.setOnClickListener {
            model.saveGenero(
                binding.lblGeneroName.editText?.text.toString(),
                id
            )
        }
    }
}