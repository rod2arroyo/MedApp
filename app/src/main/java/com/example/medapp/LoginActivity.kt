package com.example.medapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button

class LoginActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnRegistrar : Button = findViewById(R.id.btn_registrar)
        val btnIngresar : Button = findViewById(R.id.btn_ingresar)

        btnRegistrar.setOnClickListener{v : View ->
            val intent: Intent = Intent()
            intent.setClass(this, SignupActivity::class.java)
            startActivity(intent)
        }

        btnIngresar.setOnClickListener{
            val intent: Intent = Intent()
            intent.setClass(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}