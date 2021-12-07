package com.example.medapp.FragmentsMedicina

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.medapp.Alarm.MyBroadcastReceiver
import com.example.medapp.R
import com.example.medapp.usuarioactual
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Math.abs
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class AgregarMedicinaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_agregarmedicina,container,false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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

            val currentDateTime = LocalDateTime.now()
            val horarioActual : String = currentDateTime.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))

            var split : List<String> = horarioActual.split(":")
            var horaActual : Long = split[0].toLong()
            var minutoActual : Long = (split[1].substring(0,split[1].indexOf(" "))).toLong()

            println(" hora actual: " + horaActual)
            println(" minutos actual: " + minutoActual)

            var split2 : List<String> = horario.split(":")
            var horaFijada : Long = split2[0].toLong()
            var minutoFijado : Long = split2[1].toLong()

            println(" hora fijada: " + horaFijada)
            println(" minutos fijada: " + minutoFijado)

            var horaOficial : Long = 0
            var minutoOficial : Long = 0

            if(horaActual == horaFijada){
                horaOficial = 0
                minutoOficial = abs(minutoActual-minutoFijado)
                println("minuto ificial $minutoOficial")
            }else if(horaFijada > horaActual){
                horaOficial = abs(horaFijada - (horaActual+1))
                minutoOficial = abs((60 -minutoActual) + minutoFijado)
                println("minuto oficial $minutoOficial")
                println("hora oficial $horaOficial")
            }

            dbFirebase.collection("Medicinas")
                .document(usuarioactual)
                .collection("Farmacos")
                .document(System.currentTimeMillis().toString())
                .set(data)
                .addOnSuccessListener {}
                .addOnFailureListener{}

            Toast.makeText(context,"Alarma para la medicina establecida",Toast.LENGTH_SHORT).show()
            var intent : Intent = Intent(context,MyBroadcastReceiver::class.java)
            var pendingIntent : PendingIntent = PendingIntent.getBroadcast(context,0,intent,0)
            var alarmManager : AlarmManager =  activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            var timeAtButtonClick : Long = System.currentTimeMillis()
            var tiempo : Long = (1000 * 3600 * horaOficial ) + (1000 * 60 * minutoOficial)
            //var secondsMIllis : Long = (1000 * (horario.toLong())) * 3600
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                timeAtButtonClick + tiempo,
                pendingIntent)

            val fragmentB = MedicinasFragment()
            activity?.getSupportFragmentManager()?.beginTransaction()
                ?.replace(R.id.frame_container, fragmentB, "fragmnetId")
                ?.addToBackStack(null)
                ?.commit();

        }
    }
}
