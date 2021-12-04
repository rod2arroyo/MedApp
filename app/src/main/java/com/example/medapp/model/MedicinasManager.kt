package com.example.medapp.model

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MedicinasManager {
    companion object{
        var instance : MedicinasManager = MedicinasManager()
            private set
    }


    private val dbFirebase = Firebase.firestore

    fun createMedicinas(usuario: String,
                    nombre: String,
                    horario: String,
                    descripcion: String,
                    //callbackOK: (Long) -> Unit,
                    //callbackError: (String) -> Unit
    ){

        val data = hashMapOf<String,Any>(
            "nombre" to nombre,
            "horario" to horario,
            "descripcion" to descripcion
        )

        val userId = usuario

        dbFirebase.collection("Medicinas")
            .document(userId)
            .set(data)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }
}