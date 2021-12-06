package com.example.medapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.medapp.Clases.Citas
import com.example.medapp.Clases.Usuario
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnRegistrar : Button = findViewById(R.id.btn_registrar)
        val btnIngresar : Button = findViewById(R.id.btn_ingresar)
        val edtUser : EditText = findViewById(R.id.edt_user_login)
        val edtPassword : EditText = findViewById(R.id.edt_password_login)
        var final = false
        val dbFirebase = Firebase.firestore


        btnRegistrar.setOnClickListener{v : View ->
            val intent: Intent = Intent()
            intent.setClass(this, SignupActivity::class.java)
            startActivity(intent)
        }

        btnIngresar.setOnClickListener{
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

                    for(i in 0 until listUsuarios.size){
                        if(findViewById<EditText>(R.id.edt_user_login).text.toString() == listUsuarios[i].usuario &&
                            findViewById<EditText>(R.id.edt_password_login).text.toString()==listUsuarios[i].password){
                            final = true
                        }
                    }

                    if(final){
                        usuarioactual = findViewById<EditText>(R.id.edt_user_login).text.toString()
                        final = false
                        Toast.makeText(this,"Si esta registrado",Toast.LENGTH_SHORT).show()
                        val intent: Intent = Intent()
                        intent.setClass(this, MainActivity::class.java)
                        startActivity(intent)
                    }else if(findViewById<EditText>(R.id.edt_user_login).text.toString() == ""){
                        Toast.makeText(this,"Llene los espacios",Toast.LENGTH_SHORT).show()
                    }else if(!final){
                        Toast.makeText(this,"Cuenta no registrada",Toast.LENGTH_SHORT).show()
                    }

                }
                .addOnFailureListener{

                }

        }
    }
}