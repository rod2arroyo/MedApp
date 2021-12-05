package com.example.medapp.FragmentsPerfil

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.example.medapp.FragmentsCitas.AgregarCitaFragment
import com.example.medapp.LoginActivity
import com.example.medapp.R

class PerfilFragment  : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_perfil,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnEditarDatos = view.findViewById<Button>(R.id.btn_editar_datos)
        val btnEditarFoto = view.findViewById<Button>(R.id.btn_editar_foto)
        val btnLogout = view.findViewById<Button>(R.id.btn_desconectarse)

        btnEditarDatos.setOnClickListener{
            val fragment = EditarPerfilFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }

        btnEditarFoto.setOnClickListener{
            val fragment = EditarFotoFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragment, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }

        btnLogout.setOnClickListener{
            val intent = Intent(this.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }
}