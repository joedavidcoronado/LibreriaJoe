package com.example.practicaapipersonas.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.milibreria.databinding.LibroItemLayoutBinding
import com.example.milibreria.models.Libro
import com.example.milibreria.models.Libros

class LibroAdapter(val libroList: Libros, val listener: OnLibroClickListener) :
    RecyclerView.Adapter<LibroAdapter.LibroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibroViewHolder {
        val binding =
            LibroItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return LibroViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return libroList.size
    }

    override fun onBindViewHolder(holder: LibroViewHolder, position: Int) {
        val libro = libroList[position]
        holder.bind(libro, listener)
    }

    fun updateData(libroList: Libros) {
        this.libroList.clear()
        this.libroList.addAll(libroList)
        notifyDataSetChanged()
    }

    class LibroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(libro: Libro, listener: OnLibroClickListener) {
            val binding = LibroItemLayoutBinding.bind(itemView)
            binding.apply {
                Glide.with(itemView.context)
                    .load(libro.imagen.toString())
                    .into(imgFoto)
                txtLibroName.text = libro.nombre
                btn.setOnClickListener {
                    listener.onLibroDelete(libro)
                }

                txtLibroName.setOnClickListener {
                    listener.onLibroClick(libro)
                }
                root.setOnClickListener { listener.onLibroClick(libro) }
            }

        }
    }

    interface OnLibroClickListener {
        fun onLibroClick(libro: Libro)
        fun onLibroDelete(libro: Libro)
    }
}