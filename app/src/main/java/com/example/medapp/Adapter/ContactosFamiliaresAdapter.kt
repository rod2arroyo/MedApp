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
import com.example.medapp.Clases.Medicina
import com.example.medapp.R

class ContactosFamiliaresAdapter (
    private val listContactoFamiliar: List<ContactoFamiliar>,
    private val fragment: Fragment,
    val eliminar: (String) -> Unit):
    RecyclerView.Adapter<ContactosFamiliaresAdapter.ViewHolder>(){

    class ViewHolder(view: View, val listContactoFamiliar: List<ContactoFamiliar>,val eliminar: (String) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val usuario : TextView
        val nombre : TextView
        val numero : TextView
        val bute: Button

        init{
            usuario = view.findViewById(R.id.db_usuario_contacto)
            nombre = view.findViewById(R.id.db_nombre_contacto)
            numero = view.findViewById(R.id.db_numero_contacto)
            bute = view.findViewById(R.id.contactoborrar)
            bute.setOnClickListener {
                //val posi = position
                //listContactoFamiliar.drop(position)
                val name = listContactoFamiliar[position].id
                eliminar(name)
                bute.setBackgroundColor(Color.RED)



            }
            view.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_contactofamiliar,parent,false)

        val viewHolder = ViewHolder(view,listContactoFamiliar,eliminar)
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