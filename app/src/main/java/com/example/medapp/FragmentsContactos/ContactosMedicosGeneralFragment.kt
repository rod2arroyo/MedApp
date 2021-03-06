package com.example.medapp.FragmentsContactos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Adapter.ContactosFamiliaresAdapter
import com.example.medapp.Adapter.ContactosMedicosAdapter
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.Clases.ContactoMedico
import com.example.medapp.FragmentsMensajes.MensajesFragment
import com.example.medapp.R
import com.example.medapp.model.ContactosManager
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactosMedicosGeneralFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_medicos,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var btn : Button = view.findViewById(R.id.btn_agregar_medico)
        var rviDoctores = view.findViewById<RecyclerView>(R.id.rviMedicos)


        ContactosManager(requireActivity().applicationContext).getMedicosContactosFB({ contList:List<ContactoMedico> ->

            rviDoctores.adapter = ContactosMedicosAdapter(
                contList,
                this
            ){
                val dbFirebase = Firebase.firestore
                dbFirebase.collection("ContactosMedicos").document(usuarioactual).collection("Doctores").document(it).delete()

                val fragment = ContactosMedicosGeneralFragment()
                activity?.getSupportFragmentManager()?.beginTransaction()
                    ?.replace(R.id.frame_container, fragment, "fragmnetId")
                    ?.addToBackStack(null)
                    ?.commit();
            }
        }){error ->
            Log.e("PokemonFragment", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }

        btn.setOnClickListener{v: View ->
            val fragment = ContactoMedicoFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }


    }
}