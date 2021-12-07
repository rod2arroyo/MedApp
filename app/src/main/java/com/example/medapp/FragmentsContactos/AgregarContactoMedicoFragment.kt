package com.example.medapp.FragmentsContactos

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Adapter.ContactosFamiliaresAdapter
import com.example.medapp.Adapter.DoctoresAdapter
import com.example.medapp.Adapter.MedicinasAdapter
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.Clases.ContactoMedico
import com.example.medapp.Clases.Medicina
import com.example.medapp.FragmentsMedicina.MedicinasFragment
import com.example.medapp.R
import com.example.medapp.centroMedico
import com.example.medapp.model.ContactosDoctorManager
import com.example.medapp.model.ContactosManager
import com.example.medapp.model.MedicinasManager
import com.example.medapp.usuarioactual
import com.example.medapp.usuariodestino
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarContactoMedicoFragment : Fragment(){
    interface OnMenuClicked{
        fun OnClick(menuName: String)
    }

    private var listener: OnMenuClicked? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_listadoctor,container,false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as OnMenuClicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rviDoctores = view.findViewById<RecyclerView>(R.id.rviMedicosagregar)

        ContactosDoctorManager(requireActivity().applicationContext).getDoctorFB({ docList:List<ContactoMedico> ->
            rviDoctores.adapter = DoctoresAdapter(
                docList,
                this
            ){
                var valid = 0
                ContactosManager(requireActivity().applicationContext).getMedicosContactosFB({ contList:List<ContactoMedico> ->

                    for(i in 0..(contList.size-1)){
                        if(contList[i].nombre==it.nombre){
                            valid += 1
                        }
                    }
                    if(valid==0){

                        val dbFirebase = Firebase.firestore
                        // meter
                        val data = hashMapOf<String,Any>(
                            "nombre" to it.nombre,
                            "especialidad" to it.especialidad,
                            "numero" to it.numero
                        )

                        dbFirebase.collection("ContactosMedicos")
                            .document(usuarioactual)
                            .collection("Doctores")
                            .document(System.currentTimeMillis().toString())
                            .set(data)
                            .addOnSuccessListener {
                                println("----------->>> ssssssssssssssssssssss -------->>>>"  )
                            }
                            .addOnFailureListener{
                                println("----------->>> nnnnnnnnnnnnnnnnnnnnnnnn -------->>>>"  )
                            }

                        Toast.makeText(activity, "Doctor agregado a contactos!!", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(activity, "Doctor ya en contactos", Toast.LENGTH_SHORT).show()
                    }


                }){error ->
                    Log.e("PokemonFragment", error)
                    Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
                }



            }
        }){error ->
            Log.e("PokemonFragment", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }

        println(" Centro medico $centroMedico")

    }

}