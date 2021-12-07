package com.example.medapp.FragmentsPerfil

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.medapp.Clases.Usuario
import com.example.medapp.FragmentsCitas.AgregarCitaFragment
import com.example.medapp.LoginActivity
import com.example.medapp.R
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PerfilFragment  : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnEditarDatos = view.findViewById<Button>(R.id.btn_editar_datos)
        //val btnEditarFoto = view.findViewById<Button>(R.id.btn_editar_foto)
        val btnLogout = view.findViewById<Button>(R.id.btn_desconectarse)

        val nombreperfiltext = view.findViewById<TextView>(R.id.nombreperfiltext)
        val edadperfiltext = view.findViewById<TextView>(R.id.edadperfiltext)
        val numeroperfiltext2 = view.findViewById<TextView>(R.id.numeroperfiltext2)


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
                        nombreperfiltext.text=listUsuarios[i].nombre
                        edadperfiltext.text=listUsuarios[i].edad
                        numeroperfiltext2.text=listUsuarios[i].numero
                    }
                }
            }.addOnFailureListener{}

        btnEditarDatos.setOnClickListener{
            val fragment = EditarPerfilFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }

        /*btnEditarFoto.setOnClickListener{
            val fragment = EditarFotoFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }*/

        btnLogout.setOnClickListener{
            val intent = Intent(this.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }
}