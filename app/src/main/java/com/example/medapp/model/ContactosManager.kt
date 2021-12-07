package com.example.medapp.model

import android.content.Context
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.Clases.ContactoMedico
import com.example.medapp.Clases.Medicina
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactosManager (context: Context){
    private val dbFirebase = Firebase.firestore

    fun getFamiliaresDB(callbackOK: (List<ContactoFamiliar>) -> Unit, callbackError:(String)->Unit){
        dbFirebase.collection("Contactos")
            .document(usuarioactual)
            .collection("Familiares")
            .get()
            .addOnSuccessListener { res ->
                val listContactos = arrayListOf<ContactoFamiliar>()
                for(document in res){
                    val conta = ContactoFamiliar(
                        id = document.id ,
                        usuario = document.data["usuario"]!! as String,
                        nombre = document.data["nombre"]!! as String,
                        numero = document.data["numero"]!! as String,
                    )
                    listContactos.add(conta)
                }
                callbackOK(listContactos)
            }
            .addOnFailureListener{
                callbackError(it.message!!)
            }
    }

    fun getMedicosContactosFB(callbackOK: (List<ContactoMedico>) -> Unit, callbackError:(String)->Unit){
        dbFirebase
            .collection("ContactosMedicos")
            .document(usuarioactual)
            .collection("Doctores")
            .get()
            .addOnSuccessListener { res ->
                val listContactos = arrayListOf<ContactoMedico>()
                for(document in res){
                    val conta = ContactoMedico(
                        id = document.id ,
                        nombre = document.data["nombre"]!! as String,
                        especialidad = document.data["especialidad"]!! as String,
                        numero = document.data["numero"]!! as String,
                    )
                    listContactos.add(conta)
                }
                callbackOK(listContactos)
            }
            .addOnFailureListener{
                callbackError(it.message!!)
            }
    }
}