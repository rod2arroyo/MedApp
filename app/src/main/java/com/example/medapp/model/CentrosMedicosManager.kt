package com.example.medapp.model

import android.content.Context
import com.example.medapp.Clases.CentroMedico
import com.example.medapp.Clases.Citas
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CentrosMedicosManager (context: Context) {
    private val dbFirebase = Firebase.firestore

    fun getCentrosFB(callbackOK: (List<CentroMedico>) -> Unit, callbackError:(String)->Unit){
        dbFirebase.collection("Hospitales")
            .get()
            .addOnSuccessListener { res ->
                val listCentros = arrayListOf<CentroMedico>()
                for(document in res){
                    val centro = CentroMedico(
                        nombre = document.data["nombre"]!! as String,
                    )
                    listCentros.add(centro)
                }
                callbackOK(listCentros)
            }
            .addOnFailureListener{
                callbackError(it.message!!)
            }
    }
}