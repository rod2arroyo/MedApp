package com.example.medapp.FragmentsMedicina

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.medapp.Alarm.MyBroadcastReceiver
import com.example.medapp.R
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarMedicinaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agregarmedicina,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbFirebase = Firebase.firestore
        val agregarFarmaco = view.findViewById<Button>(R.id.btn_agregar_farmaco)


        agregarFarmaco.setOnClickListener{v: View ->
            val nombre = view.findViewById<EditText>(R.id.txt_nombre_med).text.toString()
            val horario = view.findViewById<EditText>(R.id.txt_horario).text.toString()
            val descripcion =  view.findViewById<EditText>(R.id.txt_desc).text.toString()

            val data = hashMapOf<String,Any>(
                "nombre" to nombre,
                "horario" to horario,
                "descripcion" to descripcion
            )

            dbFirebase.collection("Medicinas")
                .document(usuarioactual)
                .collection("Farmacos")
                .document(System.currentTimeMillis().toString())
                .set(data)
                .addOnSuccessListener {

                }
                .addOnFailureListener{

                }

            Toast.makeText(context,"reminder",Toast.LENGTH_SHORT).show()
            var intent : Intent = Intent(context,MyBroadcastReceiver::class.java)
            var pendingIntent : PendingIntent = PendingIntent.getBroadcast(context,0,intent,0)

            var alarmManager : AlarmManager =  activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            var timeAtButtonClick : Long = System.currentTimeMillis()

            var secondsMIllis : Long = (1000 * (horario.toLong()))* 60

            alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonClick + secondsMIllis,
                pendingIntent)
        }
    }
}
