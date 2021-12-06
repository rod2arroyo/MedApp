package com.example.medapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Clases.Medicina
import com.example.medapp.R
import org.w3c.dom.Text

class MedicinasAdapter (
    private val listMedicina: List<Medicina>,
    private val fragment: Fragment,
    val eliminar: (String) -> Unit):
    RecyclerView.Adapter<MedicinasAdapter.ViewHolder>(){

    class ViewHolder(view: View,val listMedicina: List<Medicina>,val eliminar: (String) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener{
        val nombre : TextView
        val horario : TextView
        val descripcion : TextView
        val bute: Button

        init{
            nombre = view.findViewById(R.id.txt_nombre_medicina)
            horario = view.findViewById(R.id.txt_horario)
            descripcion = view.findViewById(R.id.txt_descripcion)
            bute = view.findViewById(R.id.medicinaborrar)
            bute.setOnClickListener {
                //val posi = position
                //listContactoFamiliar.drop(position)
                val name = listMedicina[position].id
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
            .inflate(R.layout.item_medicina,parent,false)

        val viewHolder = ViewHolder(view,listMedicina,eliminar)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombre.text = listMedicina[position].nombre
        holder.horario.text = listMedicina[position].horario
        holder.descripcion.text = listMedicina[position].descripcion
    }

    override fun getItemCount(): Int {
        return listMedicina.size
    }


}