package com.example.medapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.R



class EnvioAdapter (
    private val listContactoFamiliarmensaje: List<ContactoFamiliar>,
    private val fragment: Fragment,
    val eliminar: (String) -> Unit,
    private val listener : (ContactoFamiliar) -> Unit):
    RecyclerView.Adapter<EnvioAdapter.ViewHolder>(){
    class ViewHolder(view: View, val listener :(ContactoFamiliar) -> Unit,val listContactoFamiliarmensaje: List<ContactoFamiliar>, val eliminar: (String) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener{

        val nombre : TextView



        init{

            nombre = view.findViewById(R.id.nombrecontactomentajetext)
            view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            listener(listContactoFamiliarmensaje[adapterPosition])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contactomensaje,parent,false)

        val viewHolder = ViewHolder(view,listener,listContactoFamiliarmensaje,eliminar)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return listContactoFamiliarmensaje.size
    }

    override fun onBindViewHolder(holder: EnvioAdapter.ViewHolder, position: Int) {

        holder.nombre.text = listContactoFamiliarmensaje[position].nombre

    }
}