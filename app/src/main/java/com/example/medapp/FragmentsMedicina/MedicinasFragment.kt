package com.example.medapp.FragmentsMedicina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.medapp.R

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
        var btnRegistrarMedicina = view.findViewById<Button>(R.id.btn_agregar_medicina)

        btnRegistrarMedicina.setOnClickListener{
            val fragmentB = AgregarMedicinaFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragmentB, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();
        }
    }
}