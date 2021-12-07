package com.example.medapp.FragmentsCitas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medapp.FragmentsContactos.AgregarContactoFamiliarFragment
import com.example.medapp.FragmentsMedicina.MedicinasFragment
import com.example.medapp.R
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarCitaFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agregarcita,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbFirebase = Firebase.firestore
        val btnAgregarCita = view.findViewById<Button>(R.id.btn_nueva_cita)

        btnAgregarCita.setOnClickListener{v: View ->
            val cMedico = view.findViewById<EditText>(R.id.txt_centro_nuevo).text.toString()
            val doctor = view.findViewById<EditText>(R.id.txt_doctor_nuevo).text.toString()
            val horario =  view.findViewById<EditText>(R.id.txt_horario_nuevo).text.toString()

            val data = hashMapOf<String,Any>(
                "cMedico" to cMedico,
                "doctor" to doctor,
                "horario" to horario
            )

            dbFirebase.collection("Citas")
                .document(usuarioactual)
                .collection("Agenda")
                .document(System.currentTimeMillis().toString())
                .set(data)
                .addOnSuccessListener {}
                .addOnFailureListener{}

            Toast.makeText(activity, "Se agrego la cita correctamente" ,Toast.LENGTH_SHORT).show()
            val fragmentB = CitasFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragmentB, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }
    }

}