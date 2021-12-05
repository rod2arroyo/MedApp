package com.example.medapp.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactosDoctorManager {
    companion object{
        var instance : ContactosDoctorManager = ContactosDoctorManager()
            private set
    }


    private val dbFirebase = Firebase.firestore

    fun createContactoDoctor(usuario: String,
                 nombre: String,
                 especialidad: String,
                 ubicacion: String,
    ){

        val data = hashMapOf<String,Any>(
            "nombre" to nombre,
            "especialidad" to especialidad,
            "ubicacion" to ubicacion
        )
        val userId = usuario
        dbFirebase.collection("ContactosMedicos")
            .document(userId)
            .set(data)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }
}