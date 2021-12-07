package com.example.medapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Clases.CentroMedico
import com.example.medapp.Clases.Citas
import com.example.medapp.R

class CentrosAdapter (
    private val listCentros: List<CentroMedico>,
    private val fragment: Fragment,
    private val listener : (CentroMedico) -> Unit
): RecyclerView.Adapter<CentrosAdapter.ViewHolder>(){

    class ViewHolder(view: View,
                     val listener : (CentroMedico) -> Unit,
                     val listCentros: List<CentroMedico>) : RecyclerView.ViewHolder(view),
                     View.OnClickListener{
        val nombre : TextView

        init{
            nombre = view.findViewById(R.id.db_nombre_centro)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener(listCentros[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_instalacion,parent,false)

        val viewHolder = ViewHolder(view,listener,listCentros)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return listCentros.size
    }

    override fun onBindViewHolder(holder: CentrosAdapter.ViewHolder, position: Int) {
        holder.nombre.text = listCentros[position].nombre
    }
}