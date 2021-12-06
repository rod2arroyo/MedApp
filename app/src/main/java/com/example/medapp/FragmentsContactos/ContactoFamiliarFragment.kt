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
import com.example.medapp.Adapter.MedicinasAdapter
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.Clases.Medicina
import com.example.medapp.FragmentsMedicina.AgregarMedicinaFragment
import com.example.medapp.R
import com.example.medapp.model.ContactosManager
import com.example.medapp.model.MedicinasManager
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ContactoFamiliarFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contactos,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAgregarFamiliar = view.findViewById<Button>(R.id.btn_agregar_familiar)
        val rviFamilires = view.findViewById<RecyclerView>(R.id.rviFamiliares)

        btnAgregarFamiliar.setOnClickListener{
            val fragment = AgregarContactoFamiliarFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }

        ContactosManager(requireActivity().applicationContext).getFamiliaresDB({ contList:List<ContactoFamiliar> ->

            rviFamilires.adapter = ContactosFamiliaresAdapter(
                contList,
                this

            ) {

                var x = 0
                ContactosManager(requireActivity().applicationContext).getFamiliaresDB({ contList: List<ContactoFamiliar> ->

                    for(i in 0..(contList.size-1)){
                        if(contList[i].id==it){
                            x=i
                        }
                    }

                    println("----------->>> id a eliminar -------->>>>" + it )
                    val dbFirebase = Firebase.firestore
                    dbFirebase.collection("Contactos").document(usuarioactual).collection("Familiares").document(it).delete()

                    //rviFamilires.adapter?.notifyItemRemoved(x)
                    rviFamilires.adapter?.notifyItemRemoved(x)

                    val fragment = ContactoFamiliarFragment()
                    activity?.getSupportFragmentManager()?.beginTransaction()
                        ?.replace(R.id.frame_container, fragment, "fragmnetId")
                        ?.addToBackStack(null)
                        ?.commit();
                    //rviFamilires.adapter?.notifyItemRangeChanged(x,contList.size-1)
                   // rviFamilires.adapter?.notifyDataSetChanged()
                }) { error ->
                    Log.e("PokemonFragment", error)
                    Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
                }



             //   rviFamilires.adapter?.notifyItemRemoved(x)
           //     rviFamilires.adapter?.notifyDataSetChanged()
            }
        }){error ->
            Log.e("PokemonFragment", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }
    }

}