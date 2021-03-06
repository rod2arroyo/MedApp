package com.example.medapp.FragmentsContactos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.medapp.FragmentsMedicina.MedicinasFragment
import com.example.medapp.R
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarContactoFamiliarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agregarcontacto,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbFirebase = Firebase.firestore
        val agregarFamiliar = view.findViewById<Button>(R.id.btn_ingresar_contacto)


        agregarFamiliar.setOnClickListener{v: View ->
            val usuario = view.findViewById<EditText>(R.id.txt_usuario_nuevo).text.toString()
            val nombre = view.findViewById<EditText>(R.id.txt_nombre_nuevo).text.toString()
            val numero =  view.findViewById<EditText>(R.id.txt_numero_nuevo).text.toString()

            val data = hashMapOf<String,Any>(
                "usuario" to usuario,
                "nombre" to nombre,
                "numero" to numero
            )

            dbFirebase.collection("Contactos")
                .document(usuarioactual)
                .collection("Familiares")
                .document(System.currentTimeMillis().toString())
                .set(data)
                .addOnSuccessListener {}
                .addOnFailureListener{}

            Toast.makeText(context,"Contacto Familiar Agregado correctamente", Toast.LENGTH_SHORT).show()

            val fragmentB = ContactoFamiliarFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragmentB, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }
    }
}