package com.example.medapp.FragmentsMedicina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.medapp.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarMedicinaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agregarmedicina,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbFirebase = Firebase.firestore
        val agregarFarmaco = view.findViewById<Button>(R.id.btn_agregar_farmaco)


        agregarFarmaco.setOnClickListener{v: View ->
            val nombre = view.findViewById<EditText>(R.id.txt_nombre_med).text.toString()
            val horario = view.findViewById<EditText>(R.id.txt_horario).text.toString()
            val descripcion =  view.findViewById<EditText>(R.id.txt_desc).text.toString()

            val data = hashMapOf<String,Any>(
                "nombre" to nombre,
                "horario" to horario,
                "descripcion" to descripcion
            )

            dbFirebase.collection("Medicinas")
                .document("wenas")
                .collection("Farmacos")
                .document(System.currentTimeMillis().toString())
                .set(data)
                .addOnSuccessListener {

                }
                .addOnFailureListener{

                }
        }
    }
}
