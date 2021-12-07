package com.example.medapp.FragmentsMensajes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.*
import com.example.medapp.Adapter.ContactosFamiliaresAdapter
import com.example.medapp.Adapter.EnvioAdapter
import com.example.medapp.Adapter.MensajesAdapter
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.FragmentsContactos.ContactoFamiliarFragment
import com.example.medapp.model.ContactosManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarMensajeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_enviarmensaje, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val enviarmensaje = view.findViewById<Button>(R.id.enviarmensaje)
        val rvicontactosmensaje = view.findViewById<RecyclerView>(R.id.rvicontactosmensaje)
        val imageViewmensaje = view.findViewById<ImageView>(R.id.imageViewmensaje)
        val usermen = view.findViewById<TextView>(R.id.usermen)

        ContactosManager(requireActivity().applicationContext).getFamiliaresDB({ contList:List<ContactoFamiliar> ->

            rvicontactosmensaje.adapter = EnvioAdapter(contList,this,{
                println("----------->>> id a eliminar -------->>>>" + nombreacual )
            }){ con : ContactoFamiliar ->

                //imageViewmensaje.setImageResource(R.drawable.baseperfil)
                conteo=conteo+1
                nombreacual=con.nombre
                usuariodestino=con.usuario
                usermen.text= nombreacual

                println("----------->>> id a eliminar -------->>>>" + nombreacual )
                println("----------->>> id a eliminar -------->>>>" + usermen.text )
            }


        }){error ->
            Log.e("PokemonFragment", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }





        enviarmensaje.setOnClickListener{
            val dbFirebase = Firebase.firestore
            val nuevomensajetext = view.findViewById<EditText>(R.id.nuevomensajetext)



            println("----------->>> id a emisor -------->>>>" + usuarioactual )
            println("----------->>> id a receptor -------->>>>" + usuariodestino )
            println("----------->>> id a contenido -------->>>>" + nuevomensajetext.text.toString() )

            // meter
            val data = hashMapOf<String,Any>(
                "emisor" to usuarioactual,
                "receptor" to usuariodestino,
                "contenido" to nuevomensajetext.text.toString()
            )

            dbFirebase.collection("Mensajes")
                .document(usuariodestino)
                .collection("dm")
                .document(System.currentTimeMillis().toString())
                .set(data)
                .addOnSuccessListener {
                    println("----------->>> ssssssssssssssssssssss -------->>>>"  )
                }
                .addOnFailureListener{
                    println("----------->>> nnnnnnnnnnnnnnnnnnnnnnnn -------->>>>"  )
                }
            Toast.makeText(activity, "Se envio el mensaje correctamente" , Toast.LENGTH_SHORT).show()
            val fragment = MensajesFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }
    }
}
