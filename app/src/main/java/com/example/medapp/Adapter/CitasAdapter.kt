package com.example.medapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Clases.Citas
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.R

class CitasAdapter (
    private val listCitas: List<Citas>,
    private val fragment: Fragment,
    val eliminar: (String) -> Unit):
    RecyclerView.Adapter<CitasAdapter.ViewHolder>(){

    class ViewHolder(view: View, val listContactoFamiliar: List<Citas>, val eliminar: (String) -> Unit) : RecyclerView.ViewHolder(view), OnClickListener{
        val cMedico : TextView
        val doctor : TextView
        val horario : TextView
        val bute: Button

        init{
            cMedico = view.findViewById(R.id.db_nombre_cita)
            doctor = view.findViewById(R.id.db_doctor_cita)
            horario = view.findViewById(R.id.db_horario_cita)
            bute = view.findViewById(R.id.citaborrar)
            bute.setOnClickListener {
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
            .inflate(R.layout.item_cita,parent,false)

        val viewHolder = ViewHolder(view,listCitas,eliminar)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return listCitas.size
    }

    override fun onBindViewHolder(holder: CitasAdapter.ViewHolder, position: Int) {
        holder.cMedico.text = listCitas[position].cMedico
        holder.doctor.text = listCitas[position].doctor
        holder.horario.text = listCitas[position].horario
    }
}