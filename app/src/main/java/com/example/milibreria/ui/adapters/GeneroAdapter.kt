package com.example.practicaapipersonas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.milibreria.databinding.GeneroItemLayoutBinding
import com.example.milibreria.models.Genero
import com.example.milibreria.models.Generos
import com.example.milibreria.models.Libros

class GeneroAdapter(val generoList: Generos, val listener: OnGeneroClickListener) :
    RecyclerView.Adapter<GeneroAdapter.GeneroViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneroViewHolder {
        val binding =
            GeneroItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return GeneroViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return generoList.size
    }

    override fun onBindViewHolder(holder: GeneroViewHolder, position: Int) {
        val genero = generoList[position]
        holder.bind(genero, listener)
    }

    fun updateData(generoList: Generos) {
        this.generoList.clear()
        this.generoList.addAll(generoList)
        notifyDataSetChanged()
    }

    class GeneroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genero: Genero, listener: OnGeneroClickListener) {
            val binding = GeneroItemLayoutBinding.bind(itemView)
            binding.apply {
                lblGeneroName.text = genero.nombre
                btnDeleteGenero.setOnClickListener {
                    listener.onGeneroDelete(genero)
                }
                lblGeneroName.setOnClickListener {
                    listener.onGeneroClick(genero)
                }
                root.setOnClickListener { listener.onGeneroClick(genero) }
            }

        }
    }

    interface OnGeneroClickListener {
        fun onGeneroClick(genero: Genero)
        fun onGeneroDelete(genero: Genero)
    }
}