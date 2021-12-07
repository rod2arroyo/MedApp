package com.example.medapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Clases.Citas
import com.example.medapp.Clases.Mensaje
import com.example.medapp.R



class MensajesAdapter (
    private val listMensajes: List<Mensaje>,
    private val fragment: Fragment,
    val eliminar: (String) -> Unit):
    RecyclerView.Adapter<MensajesAdapter.ViewHolder>(){

    class ViewHolder(view: View, val listMensajes: List<Mensaje>, val eliminar: (String) -> Unit) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        val emisor : TextView
        val contenido : TextView

        val bute: Button

        init{
            emisor = view.findViewById(R.id.mensajenombre)
            contenido = view.findViewById(R.id.mensajetexto)

            bute = view.findViewById(R.id.mensajeborrar)
            bute.setOnClickListener {
                val name = listMensajes[position].id
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
            .inflate(R.layout.item_mensaje,parent,false)

        val viewHolder = ViewHolder(view,listMensajes,eliminar)
        return viewHolder
    }


    override fun getItemCount(): Int {
        return listMensajes.size
    }

    override fun onBindViewHolder(holder: MensajesAdapter.ViewHolder, position: Int) {
        holder.emisor.text = listMensajes[position].emisor
        holder.contenido.text = listMensajes[position].contenido
    }
}