package com.example.medapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.medapp.Clases.CentroMedico
import com.example.medapp.FragmentsCitas.CitasFragment
import com.example.medapp.FragmentsContactos.AgregarContactoMedicoFragment
import com.example.medapp.FragmentsContactos.ContactoMedicoFragment
import com.example.medapp.FragmentsContactos.ContactosFragment
import com.example.medapp.FragmentsMedicina.MedicinasFragment
import com.example.medapp.FragmentsMensajes.MensajesFragment
import com.example.medapp.FragmentsPerfil.PerfilFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
var usuarioactual : String = ""
var centroMedico = CentroMedico("")
var nombreacual = "xxxx"
var conteo = 0
var usuariodestino = "xxxx"

class MainActivity : AppCompatActivity() , ContactoMedicoFragment.OnCentroMedicoSelected, AgregarContactoMedicoFragment.OnMenuClicked{
    val medicinasFragment = MedicinasFragment()
    val contactosFragment = ContactosFragment()
    val perfilFragment = PerfilFragment()
    val citasFragment = CitasFragment()
    val mensajesFragment = MensajesFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(medicinasFragment)
        var bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.firstFragment -> replaceFragment(medicinasFragment)
                R.id.secondFragment -> replaceFragment(contactosFragment)
                R.id.thirdFragment -> replaceFragment(perfilFragment)
                R.id.fourthFragment -> replaceFragment(citasFragment)
                R.id.fifthFragment -> replaceFragment(mensajesFragment)
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


    private fun changeCentroDetailFragment(centro: CentroMedico) {
        val fragment = AgregarContactoMedicoFragment()
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frame_container, fragment)
        ft.addToBackStack(null)
        centroMedico=centro
        ft.commit()
    }

    private fun replaceFragment(fragment : Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun onSelect(centro: CentroMedico) {
        changeCentroDetailFragment(centro)
    }

    override fun OnClick(menuName: String) {
        if(menuName == "verinfo"){
            //
        }
        else if(menuName == "favoritoop"){

        }
        else if(menuName == "pokes"){

        }
    }


}