package com.example.medapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medapp.Fragments.ContactosFragment
import com.example.medapp.Fragments.MedicinasFragment
import com.example.medapp.Fragments.PerfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val medicinasFragment = MedicinasFragment()
    val contactosFragment = ContactosFragment()
    val perfilFragment = PerfilFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(contactosFragment)
        var bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.firstFragment -> replaceFragment(medicinasFragment)
                R.id.secondFragment -> replaceFragment(contactosFragment)
                R.id.thirdFragment -> replaceFragment(perfilFragment)
            }
            true
        }
        val dbFirebase = Firebase.firestore



        dbFirebase.collection("Medicinas")
            .get()
            .addOnSuccessListener { res ->
                for(document in res){
                    println(" wenas " + document.data["Nombre"])
                }
            }
            .addOnFailureListener {  }
    }


    private fun replaceFragment(fragment : Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_container, fragment)
            transaction.commit()
        }
    }



}