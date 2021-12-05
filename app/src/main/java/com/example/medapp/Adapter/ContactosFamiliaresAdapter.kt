package com.example.medapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.Clases.Medicina
import com.example.medapp.R

class ContactosFamiliaresAdapter (
    private val listContactoFamiliar: List<ContactoFamiliar>,
    private val fragment: Fragment
): RecyclerView.Adapter<ContactosFamiliaresAdapter.ViewHolder>(){

    class ViewHolder(view: View, val listContactoFamiliar: List<ContactoFamiliar>) : RecyclerView.ViewHolder(view){
        val usuario : TextView
        val nombre : TextView
        val numero : TextView


        init{
            usuario = view.findViewById(R.id.db_usuario_contacto)
            nombre = view.findViewById(R.id.db_nombre_contacto)
            numero = view.findViewById(R.id.db_numero_contacto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contactofamiliar,parent,false)

        val viewHolder = ViewHolder(view,listContactoFamiliar)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return listContactoFamiliar.size
    }

    override fun onBindViewHolder(holder: ContactosFamiliaresAdapter.ViewHolder, position: Int) {
        holder.usuario.text = listContactoFamiliar[position].usuario
        holder.nombre.text = listContactoFamiliar[position].nombre
        holder.numero.text = listContactoFamiliar[position].numero
    }
}