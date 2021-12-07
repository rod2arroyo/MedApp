package com.example.medapp.model

import android.content.Context
import com.example.medapp.Clases.Citas
import com.example.medapp.Clases.Mensaje
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MensajesManager(context: Context) {
    private val dbFirebase = Firebase.firestore

    fun getMensajesFB(callbackOK: (List<Mensaje>) -> Unit, callbackError:(String)->Unit){
        dbFirebase.collection("Mensajes")
            .document(usuarioactual)
            .collection("dm")
            .get()
            .addOnSuccessListener { res ->
                val listMensajes = arrayListOf<Mensaje>()
                for(document in res){
                    val conta = Mensaje(
                        id = document.id ,
                        emisor = document.data["emisor"]!! as String,
                        receptor = document.data["receptor"]!! as String,
                        contenido = document.data["contenido"]!! as String,
                    )
                    listMensajes.add(conta)
                }
                callbackOK(listMensajes)
            }
            .addOnFailureListener{
                callbackError(it.message!!)
            }
    }

}