package com.example.medapp.model

import android.content.Context
import com.example.medapp.Clases.CentroMedico
import com.example.medapp.Clases.ContactoMedico
import com.example.medapp.centroMedico
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactosDoctorManager (context: Context) {
    private val dbFirebase = Firebase.firestore

    fun getDoctorFB(callbackOK: (List<ContactoMedico>) -> Unit, callbackError:(String)->Unit){
        dbFirebase
            .collection("Hospitales")
            .document(centroMedico.nombre)
            .collection("Doctores")
            .get()
            .addOnSuccessListener { res ->
                val listDoctores = arrayListOf<ContactoMedico>()
                for(document in res){
                    val doc = ContactoMedico(
                        nombre = document.data["nombre"]!! as String,
                        especialidad = document.data["especialidad"]!! as String,
                        numero = document.data["numero"] as String,
                    )
                    listDoctores.add(doc)
                }
                callbackOK(listDoctores)
            }
            .addOnFailureListener{
                callbackError(it.message!!)
            }
    }
}