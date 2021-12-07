package com.example.medapp.FragmentsMensajes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Adapter.MensajesAdapter
import com.example.medapp.Clases.Citas
import com.example.medapp.Clases.Mensaje
import com.example.medapp.FragmentsCitas.AgregarCitaFragment
import com.example.medapp.FragmentsCitas.CitasFragment
import com.example.medapp.R
import com.example.medapp.model.MensajesManager
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MensajesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_mensajes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAgregarMensaje = view.findViewById<Button>(R.id.btn_agregar_mensaje)
        val rviMensajes = view.findViewById<RecyclerView>(R.id.rviMensajes)

        btnAgregarMensaje.setOnClickListener{
            val fragment = AgregarMensajeFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }


        MensajesManager(requireActivity().applicationContext).getMensajesFB({ mensajeList:List<Mensaje> ->

            rviMensajes.adapter =MensajesAdapter(
                mensajeList,this
            ){

                val dbFirebase = Firebase.firestore
                dbFirebase.collection("Mensajes").document(usuarioactual).collection("dm").document(it).delete()

                //rviMensajes.adapter?.notifyItemRemoved(x)


                val fragment = MensajesFragment()
                activity?.getSupportFragmentManager()?.beginTransaction()
                    ?.replace(R.id.frame_container, fragment, "fragmnetId")
                    ?.addToBackStack(null)
                    ?.commit();

            }
        }){error ->
            Log.e("PokemonFragment", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }




    }
}
