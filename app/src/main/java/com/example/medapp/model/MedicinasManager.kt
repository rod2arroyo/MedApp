package com.example.medapp.model

import android.content.Context
import com.example.medapp.Clases.Medicina
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MedicinasManager(context: Context) {
    private val dbFirebase = Firebase.firestore

    fun getMedicinaFB(callbackOK: (List<Medicina>) -> Unit,callbackError:(String)->Unit){
        dbFirebase.collection("Medicinas")
            .document(usuarioactual)
            .collection("Farmacos")
            .get()
            .addOnSuccessListener { res ->
                val listMedicina = arrayListOf<Medicina>()
                for(document in res){
                    val medi = Medicina(
                        id = document.id ,
                        nombre = document.data["nombre"]!! as String,
                        horario = document.data["horario"]!! as String,
                        descripcion = document.data["descripcion"]!! as String,
                    )
                    listMedicina.add(medi)
                }
                callbackOK(listMedicina)
            }
            .addOnFailureListener{
                callbackError(it.message!!)
            }
    }
}