package com.example.medapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Clases.CentroMedico
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.Clases.ContactoMedico
import com.example.medapp.R

class DoctoresAdapter (
    private val listDoctores: List<ContactoMedico>,
    private val fragment: Fragment,
    val agregar: (String) -> Unit):
    RecyclerView.Adapter<DoctoresAdapter.ViewHolder>(){

        class ViewHolder(view: View,
                         val listDoctores: List<ContactoMedico>,
                         val agregar: (String) -> Unit) : RecyclerView.ViewHolder(view),
                         View.OnClickListener{

            val nombre : TextView
            val especialidad : TextView
            val numero : TextView
            val bute: Button

            init{
                nombre = view.findViewById(R.id.db_doctor_contacto)
                especialidad = view.findViewById(R.id.db_especialidad_contacto)
                numero = view.findViewById(R.id.db_numero_doctor_contacto)
                bute = view.findViewById(R.id.btn_agregar_doctor)
                bute.setOnClickListener {

                }
                view.setOnClickListener(this)
            }

            override fun onClick(p0: View?) {

            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_contactoagregardoctor,parent,false)

            val viewHolder = ViewHolder(view,listDoctores,agregar)
            return viewHolder
        }


        override fun getItemCount(): Int {
            return listDoctores.size
        }

        override fun onBindViewHolder(holder: DoctoresAdapter.ViewHolder, position: Int) {
            holder.nombre.text = listDoctores[position].nombre
            holder.especialidad.text = listDoctores[position].especialidad
            holder.numero.text = listDoctores[position].numero
        }
}