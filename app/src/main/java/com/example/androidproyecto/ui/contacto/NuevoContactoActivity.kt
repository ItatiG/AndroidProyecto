package com.example.androidproyecto.ui.contacto

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.androidproyecto.R
import com.example.androidproyecto.recyclerview.Contacto
import com.example.androidproyecto.recyclerview.Singleton
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_nuevo_contacto.*
import kotlinx.android.synthetic.main.fragment_recycler_view.*

class NuevoContactoActivity : AppCompatActivity(), AgregarContactoDialogFragment.AgregarContactoDialogListener
{
    val onLongItemClickListener: (Int) -> Unit = {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_contacto)

        //Botón para agregar
        btnAgrearNuevoContacto.setOnClickListener {
            DialogAgregarContacto()
        }
    }

    private fun DialogAgregarContacto()
    {
        val dialog = AgregarContactoDialogFragment()
        dialog.show(supportFragmentManager, "NoticeDialogFragment")
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDialogPositiveClick()
    {
        val contacto = Contacto("${edtNombreNuevoContacto.text}", "${edtTelefonoNuevoContacto.text}")
        Singleton.dataSet.add(contacto)

        Toast.makeText(this, "Contacto agregado correctamente.", Toast.LENGTH_LONG).show()
    }

    override fun onDialogNegativeClick()
    {
        Toast.makeText(this, "No se agrego el contacto.", Toast.LENGTH_SHORT).show()
    }
}
