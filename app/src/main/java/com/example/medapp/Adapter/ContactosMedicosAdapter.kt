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
import com.example.medapp.Clases.ContactoMedico
import com.example.medapp.R

class ContactosMedicosAdapter (
    private val listContactoMedico: List<ContactoMedico>,
    private val fragment: Fragment,
    val eliminar: (String) -> Unit):
    RecyclerView.Adapter<ContactosMedicosAdapter.ViewHolder>(){

    class ViewHolder(view: View,
                     val listContactoMedico: List<ContactoMedico>,
                     val eliminar: (String) -> Unit) : RecyclerView.ViewHolder(view),
                     View.OnClickListener{

        val nombre : TextView
        val especialidad : TextView
        val numero : TextView
        val bute: Button

        init{
            nombre = view.findViewById(R.id.doctornombretext)
            especialidad = view.findViewById(R.id.doctorespetext)
            numero = view.findViewById(R.id.doctornumerotext)
            bute = view.findViewById(R.id.doctorborrar)
            bute.setOnClickListener {
                val name = listContactoMedico[position].id
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
            .inflate(R.layout.item_contactomedico,parent,false)

        val viewHolder = ViewHolder(view,listContactoMedico,eliminar)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return listContactoMedico.size
    }

    override fun onBindViewHolder(holder: ContactosMedicosAdapter.ViewHolder, position: Int) {
        holder.especialidad.text = listContactoMedico[position].especialidad
        holder.nombre.text = listContactoMedico[position].nombre
        holder.numero.text = listContactoMedico[position].numero
    }
}