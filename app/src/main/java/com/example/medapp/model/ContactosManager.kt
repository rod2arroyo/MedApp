package com.example.medapp.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactosManager {
    companion object{
        var instance : ContactosManager = ContactosManager()
            private set
    }


    private val dbFirebase = Firebase.firestore

    fun createContactos(usuario: String,
                 nombre: String,
                        apellido: String,
                        parentesco: String,
                 //callbackOK: (Long) -> Unit,
                 //callbackError: (String) -> Unit
    ){

        val data = hashMapOf<String,Any>(
            "nombre" to nombre,
            "apellido" to apellido,
            "parentesco" to parentesco
        )

        val userId = usuario

        dbFirebase.collection("Contactos")
            .document(userId)
            .set(data)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }
}