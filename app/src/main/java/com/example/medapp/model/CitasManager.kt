package com.example.medapp.model

import android.content.Context
import com.example.medapp.Clases.Citas
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CitasManager(context: Context) {
    private val dbFirebase = Firebase.firestore

    fun getCitasFB(callbackOK: (List<Citas>) -> Unit, callbackError:(String)->Unit){
        dbFirebase.collection("Citas")
            .document(usuarioactual)
            .collection("Agenda")
            .get()
            .addOnSuccessListener { res ->
                val listCitas = arrayListOf<Citas>()
                for(document in res){
                    val conta = Citas(
                        cMedico = document.data["cMedico"]!! as String,
                        doctor = document.data["doctor"]!! as String,
                        horario = document.data["horario"]!! as String,
                    )
                    listCitas.add(conta)
                }
                callbackOK(listCitas)
            }
            .addOnFailureListener{
                callbackError(it.message!!)
            }
    }

    /*fun createCitas(usuario: String,
                 nombre: String,
                 doctor: String,
                 especialidad: String,
                 horario: String,
                 //callbackOK: (Long) -> Unit,
                 //callbackError: (String) -> Unit
    ){

        val data = hashMapOf<String,Any>(
            "nombre" to nombre,
            "doctor" to doctor,
            "especialidad" to especialidad,
            "horario" to horario,
        )

        val userId = usuario

        dbFirebase.collection("Citas")
            .document(userId)
            .set(data)
            .addOnSuccessListener {

            }
            .addOnFailureListener{

            }
    }*/
}