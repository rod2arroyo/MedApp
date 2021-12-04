package com.example.medapp.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CitasManager {
    companion object{
        var instance : CitasManager = CitasManager()
            private set
    }


    private val dbFirebase = Firebase.firestore

    fun createCitas(usuario: String,
                 nombre: String,
                 doctor: String,
                 especialidad: String,
                 //callbackOK: (Long) -> Unit,
                 //callbackError: (String) -> Unit
    ){

        val data = hashMapOf<String,Any>(
            "nombre" to nombre,
            "doctor" to doctor,
            "especialidad" to especialidad
        )

        val userId = usuario

        dbFirebase.collection("Citas")
            .document(userId)
            .set(data)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }
}