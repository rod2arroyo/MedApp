package com.example.medapp.FragmentsMedicina

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.medapp.Adapter.MedicinasAdapter
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.Clases.Medicina
import com.example.medapp.FragmentsContactos.ContactoFamiliarFragment
import com.example.medapp.R
import com.example.medapp.model.ContactosManager
import com.example.medapp.model.MedicinasManager
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MedicinasFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_medicinas,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnRegistrarMedicina = view.findViewById<Button>(R.id.btn_agregar_medicina)
        val rViMedicinas = view.findViewById<RecyclerView>(R.id.rviMedicinas)

        btnRegistrarMedicina.setOnClickListener{
            val fragmentB = AgregarMedicinaFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragmentB, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }

   MedicinasManager(requireActivity().applicationContext).getMedicinaFB({medList:List<Medicina> ->
        rViMedicinas.adapter = MedicinasAdapter(
            medList,
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
                dbFirebase.collection("Medicinas").document(usuarioactual).collection("Farmacos").document(it).delete()


                //rviFamilires.adapter?.notifyItemRemoved(x)
                rViMedicinas.adapter?.notifyItemRemoved(x)
                val fragment = MedicinasFragment()
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