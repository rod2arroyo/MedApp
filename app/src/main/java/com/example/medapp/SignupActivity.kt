package com.example.medapp

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.example.medapp.model.CitasManager
import com.example.medapp.model.ContactosManager
import com.example.medapp.model.LoginManager
import com.example.medapp.model.MedicinasManager

class SignupActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)

        //val txtUsuario =
        //val txtPassword =

        btnRegistrar.setOnClickListener{v : View ->
            LoginManager.instance.saveUser(
                findViewById<EditText>(R.id.txt_usuario).text.toString(),
                findViewById<EditText>(R.id.txt_password).text.toString(),
                {},
                {}
            )

            CitasManager.instance.createCitas(
                findViewById<EditText>(R.id.txt_usuario).text.toString(),
                "",
                "",
                "",
            )

            ContactosManager.instance.createContactos(
                findViewById<EditText>(R.id.txt_usuario).text.toString(),
                "",
                "",
                "",
            )

            MedicinasManager.instance.createMedicinas(
                findViewById<EditText>(R.id.txt_usuario).text.toString(),
                "",
                "",
                "",
            )
        }



    }
}