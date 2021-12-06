package com.example.medapp.model

import com.example.medapp.Clases.Medicina
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
            "password" to password,
            "nombre" to "",
            "edad" to "",
            "numero" to "",
        )

        dbFirebase.collection("Usuarios")
            .document(System.currentTimeMillis().toString())
            .set(data)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }

    /*fun createMedicinas(usuario: String,
                        nombre: String,
                        horario: String,
                        descripcion: String,
    ){

        val data = hashMapOf<String,Any>(
            /*"nombre" to nombre,
            "horario" to horario,
            "descripcion" to descripcion*/
        )

        val userId = usuario
        dbFirebase.collection("Medicinas")
            .document(userId)
            .collection("Farmacos")
            .document(System.currentTimeMillis().toString())
            .set(data)
            .addOnSuccessListener {}
            .addOnFailureListener{}
    }

    fun createContactos(usuario: String,
                        usuarioPersona: String,
                        nombre: String,
                        numero: String,
    ){

        val data = hashMapOf<String,Any>(
            /*"usuario" to usuarioPersona,
            "nombre" to nombre,
            "numero" to numero,*/
        )

        val userId = usuario
        dbFirebase.collection("Contactos")
            .document(userId)
            .collection("Familiares")
            .document(System.currentTimeMillis().toString())
            .set(data)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }*/

}