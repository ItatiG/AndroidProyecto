package com.example.androidproyecto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        Log.e("TAG", "Email: $strEmail")
        Log.e("TAG", "Password: $strPassword")

        startActivity(Intent(this, NavigationActivity::class.java))
    }
}
