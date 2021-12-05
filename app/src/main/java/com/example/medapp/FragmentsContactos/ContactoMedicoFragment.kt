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
import com.example.medapp.Adapter.CentrosAdapter
import com.example.medapp.Adapter.ContactosFamiliaresAdapter
import com.example.medapp.Clases.CentroMedico
import com.example.medapp.Clases.ContactoFamiliar
import com.example.medapp.FragmentsMedicina.AgregarMedicinaFragment
import com.example.medapp.R
import com.example.medapp.model.CentrosMedicosManager
import com.example.medapp.model.ContactosManager

class ContactoMedicoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_instalaciones,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rviInstalaciones = view.findViewById<RecyclerView>(R.id.rviInstalaciones)

        CentrosMedicosManager(requireActivity().applicationContext).getCentrosFB({ centList:List<CentroMedico> ->
            rviInstalaciones.adapter = CentrosAdapter(
                centList,
                this
            )
        }){error ->
            Log.e("PokemonFragment", error)
            Toast.makeText(activity, "Error" + error, Toast.LENGTH_SHORT).show()
        }
        /*val btnAgregarMedico = view.findViewById<Button>(R.id.btn_agregar_medico)
        btnAgregarMedico.setOnClickListener{
            val fragment = AgregarContactoMedicoFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }*/
    }
}