package com.example.medapp.FragmentsContactos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.medapp.FragmentsMedicina.AgregarMedicinaFragment
import com.example.medapp.R

class ContactosFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_eleccion,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnContactoFamiliar = view.findViewById<Button>(R.id.btn_contacto_familiar)
        val btnContactoMedico = view.findViewById<Button>(R.id.btn_contacto_medico)

        btnContactoFamiliar.setOnClickListener{
            val fragment = ContactoFamiliarFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }

        btnContactoMedico.setOnClickListener{
            val fragment = ContactoMedicoFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }
    }
}