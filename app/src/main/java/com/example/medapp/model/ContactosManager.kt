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
                        usuarioPersona: String,
                        nombre: String,
                        celular: String,
                        parentesco: String,
                 //callbackOK: (Long) -> Unit,
                 //callbackError: (String) -> Unit
    ){

        val data = hashMapOf<String,Any>(
            "usuario" to usuarioPersona,
            "nombre" to nombre,
            "celular" to celular,
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