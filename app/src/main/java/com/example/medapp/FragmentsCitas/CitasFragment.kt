package com.example.medapp.FragmentsCitas

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Adapter.CitasAdapter
import com.example.medapp.Adapter.ContactosFamiliaresAdapter
import com.example.medapp.Clases.Citas
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.FragmentsContactos.AgregarContactoFamiliarFragment
import com.example.medapp.FragmentsContactos.ContactoFamiliarFragment
import com.example.medapp.R
import com.example.medapp.model.CitasManager
import com.example.medapp.model.ContactosManager
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CitasFragment  : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_citas,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAgregarCita = view.findViewById<Button>(R.id.btn_agregar_cita)
        val rviCitas = view.findViewById<RecyclerView>(R.id.rviCitas)

        btnAgregarCita.setOnClickListener{
            val fragment = AgregarCitaFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }

        CitasManager(requireActivity().applicationContext).getCitasFB({ citasList:List<Citas> ->
            rviCitas.adapter = CitasAdapter(
                citasList,
                this
            ){

                var x = 0
                ContactosManager(requireActivity().applicationContext).getFamiliaresDB({ contList: List<ContactoFamiliar> ->

                    for(i in 0..(contList.size-1)){
                        if(contList[i].id==it){
                            x=i
                        }
                    }

                    println("----------->>> id a eliminar -------->>>>" + it )
                    val dbFirebase = Firebase.firestore
                    dbFirebase.collection("Citas").document(usuarioactual).collection("Agenda").document(it).delete()

                    rviCitas.adapter?.notifyItemRemoved(x)


                    val fragment = CitasFragment()
                    activity?.getSupportFragmentManager()?.beginTransaction()
                        ?.replace(R.id.frame_container, fragment, "fragmnetId")
                        ?.addToBackStack(null)
                        ?.commit();
                }) { error ->
                    Log.e("PokemonFragment", error)
                    Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
                }

            }
        }){error ->
            Log.e("PokemonFragment", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }
    }
}