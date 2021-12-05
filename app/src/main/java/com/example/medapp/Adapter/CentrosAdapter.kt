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
    private val fragment: Fragment
): RecyclerView.Adapter<CentrosAdapter.ViewHolder>(){

    class ViewHolder(view: View, val listCentros: List<CentroMedico>) : RecyclerView.ViewHolder(view){
        val nombre : TextView
        //val doctor : TextView
        //val horario : TextView


        init{
            nombre = view.findViewById(R.id.db_nombre_centro)
            //doctor = view.findViewById(R.id.db_doctor_cita)
            //horario = view.findViewById(R.id.db_horario_cita)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_instalacion,parent,false)

        val viewHolder = ViewHolder(view,listCentros)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return listCentros.size
    }

    override fun onBindViewHolder(holder: CentrosAdapter.ViewHolder, position: Int) {
        holder.nombre.text = listCentros[position].nombre
        //holder.doctor.text = listCitas[position].doctor
        //holder.horario.text = listCitas[position].horario
    }
}