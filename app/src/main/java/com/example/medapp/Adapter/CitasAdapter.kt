package com.example.medapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Clases.Citas
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.R

class CitasAdapter (
    private val listCitas: List<Citas>,
    private val fragment: Fragment
): RecyclerView.Adapter<CitasAdapter.ViewHolder>(){

    class ViewHolder(view: View, val listContactoFamiliar: List<Citas>) : RecyclerView.ViewHolder(view){
        val cMedico : TextView
        val doctor : TextView
        val horario : TextView


        init{
            cMedico = view.findViewById(R.id.db_nombre_cita)
            doctor = view.findViewById(R.id.db_doctor_cita)
            horario = view.findViewById(R.id.db_horario_cita)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cita,parent,false)

        val viewHolder = ViewHolder(view,listCitas)
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