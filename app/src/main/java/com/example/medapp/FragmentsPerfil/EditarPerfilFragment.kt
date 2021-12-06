package com.example.medapp.FragmentsPerfil

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medapp.Clases.Usuario
import com.example.medapp.MainActivity
import com.example.medapp.R
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarPerfilFragment :  Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val btnguardar = view.findViewById<Button>(R.id.btn_editar_datos)
        return inflater.inflate(R.layout.fragment_editarperfil,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val guardarcambios = view.findViewById<Button>(R.id.guardarcambios)
        val editarnombretexto = view.findViewById<TextView>(R.id.editarnombretexto)
        val efitaredadtext = view.findViewById<TextView>(R.id.efitaredadtext)
        val editarnumerotext = view.findViewById<TextView>(R.id.editarnumerotext)

        var x =""
        val dbFirebase = Firebase.firestore
        dbFirebase
            .collection("Usuarios")
            .get()
            .addOnSuccessListener {res ->
                val listUsuarios = arrayListOf<Usuario>()
                for(document in res){
                    val user = Usuario(
                        id = document.id ,
                        usuario = document.data["usuario"]!! as String,
                        password = document.data["password"]!! as String,
                        nombre = document.data["nombre"]!! as String,
                        edad = document.data["edad"]!! as String,
                        numero = document.data["numero"]!! as String,
                        )
                    listUsuarios.add(user)
                }

                for(i in 0..(listUsuarios.size-1)){
                    if(listUsuarios[i].usuario== usuarioactual)
                    {
                        editarnombretexto.text=listUsuarios[i].nombre
                        efitaredadtext.text=listUsuarios[i].edad
                        editarnumerotext.text=listUsuarios[i].numero
                        x = listUsuarios[i].id
                    }
                }
            }.addOnFailureListener{}

            guardarcambios.setOnClickListener { _ : View ->

                val nombre = hashMapOf("nombre" to editarnombretexto.text.toString())
                val edad = hashMapOf("edad" to efitaredadtext.text.toString())
                val numero = hashMapOf("numero" to editarnumerotext.text.toString())
                dbFirebase.collection("Usuarios").document(x).set(nombre, SetOptions.merge())
                dbFirebase.collection("Usuarios").document(x).set(edad, SetOptions.merge())
                dbFirebase.collection("Usuarios").document(x).set(numero, SetOptions.merge())

                val fragment = PerfilFragment()
                activity?.getSupportFragmentManager()?.beginTransaction()
                    ?.replace(R.id.frame_container, fragment, "fragmnetId")
                    ?.addToBackStack(null)
                    ?.commit();
            }





    }

}