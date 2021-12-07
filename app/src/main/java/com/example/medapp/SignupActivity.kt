package com.example.medapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.medapp.Clases.Medicina
import com.example.medapp.model.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SignupActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        val userActual = findViewById<EditText>(R.id.txt_usuario)

        var nombre = ""
        var final = false
        var nombreActual = findViewById<EditText>(R.id.txt_usuario).text.toString()
        val dbFirebase = Firebase.firestore
        btnRegistrar.setOnClickListener{v : View ->

            dbFirebase.collection("Usuarios")
                .get()
                .addOnSuccessListener { res ->
                    val listNames = arrayListOf<String>()
                    for (document in res) {
                        nombre = document.data["usuario"]!! as String
                        listNames.add(nombre)
                    }

                    for(i in 0 until listNames.size){
                        if(findViewById<EditText>(R.id.txt_usuario).text.toString() == listNames[i]){
                            final = true
                        }
                    }

                    if(userActual.text.toString() == ""){
                        Toast.makeText(this,"Los datos estan vacios", Toast.LENGTH_SHORT).show()
                    }else if(final!!){
                        final = false
                        println("SALIO ESTA COSA $final")
                        Toast.makeText(this,"Ya esta registrada", Toast.LENGTH_SHORT).show()
                    }else if(!final!!){
                        Toast.makeText(this,"Cuenta correctamente creada",Toast.LENGTH_SHORT).show()
                        final = true
                        LoginManager.instance.saveUser(
                            findViewById<EditText>(R.id.txt_usuario).text.toString(),
                            findViewById<EditText>(R.id.txt_password).text.toString(),
                            {},
                            {}
                        )

                        val intent: Intent = Intent()
                        intent.setClass(this, LoginActivity::class.java)
                        startActivity(intent)

                        /*CitasManager.instance.createCitas(
                            findViewById<EditText>(R.id.txt_usuario).text.toString(),
                            "",
                            "",
                            "",
                            "",
                        )

                        ContactosDoctorManager.instance.createContactoDoctor(
                            findViewById<EditText>(R.id.txt_usuario).text.toString(),
                            "",
                            "",
                            "",
                        )*/

                        /*LoginManager.instance.createMedicinas(
                            findViewById<EditText>(R.id.txt_usuario).text.toString(),
                            "",
                            "",
                            "",
                        )
                        LoginManager.instance.createContactos(
                            findViewById<EditText>(R.id.txt_usuario).text.toString(),
                            "",
                            "",
                            "",
                        )*/
                    }
                }
        }
    }
}