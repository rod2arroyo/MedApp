package com.example.medapp.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginManager {
    companion object{
        var instance : LoginManager = LoginManager()
            private set
    }


    private val dbFirebase = Firebase.firestore

    fun saveUser(usuario: String,
                 password: String,
                 callbackOK: (Long) -> Unit,
                 callbackError: (String) -> Unit){

        val data = hashMapOf<String,Any>(
            "usuario" to usuario,
            "password" to password
        )

        dbFirebase.collection("Usuarios")
            .document(System.currentTimeMillis().toString())
            .set(data)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }
}