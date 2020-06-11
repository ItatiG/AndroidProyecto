package com.example.androidproyecto

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.androidproyecto.background.MusicaService
import com.example.androidproyecto.ui.inicio.InicioFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Botón para registrarse
        btnRegistrarse.setOnClickListener { view->
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        //Botón para iniciar sesión
        btnIniciarSesion.setOnClickListener { view->
            IniciarSesion()
        }
    }

    fun IniciarSesion()
    {
        val strEmail: String = edtLoginCorreoElectronico.text.toString().trim()
        val strPassword: String = edtLoginContraseña.text.toString().trim()

        val sharedPref = getSharedPreferences("mi_usuario", Context.MODE_PRIVATE)
        var correo: String? = sharedPref.getString("correo","")
        var contrasena: String? = sharedPref.getString("contraseña","")

        if (strEmail == correo && strPassword == contrasena)
        {
            startActivity(Intent(this, NavigationActivity::class.java))
        }
        else
        {
            Toast.makeText(this, "El usuario y/o contraseña son incorrectas.", Toast.LENGTH_LONG).show()
        }
    }


}
