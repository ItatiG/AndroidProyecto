package com.example.androidproyecto.ui.contacto

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.androidproyecto.recyclerview.Singleton

class AgregarContactoDialogFragment : DialogFragment()
{
    internal lateinit var listener: AgregarContactoDialogListener

    interface AgregarContactoDialogListener
    {
        fun onDialogPositiveClick()
        fun onDialogNegativeClick()
    }

    override fun onAttach(context: Context)
    {
        super.onAttach(context)
        try
        {
            listener = context as AgregarContactoDialogListener
        }
        catch (e: ClassCastException)
        {
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            builder.setTitle("Nuevo contacto")
            builder.setMessage("¿Está seguro de que desea agregar el nuevo contacto?")
                .setPositiveButton("Si",
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogPositiveClick()
                    })
                .setNegativeButton("No",
                    DialogInterface.OnClickListener { dialog, id ->
                        listener.onDialogNegativeClick()

                        dismiss()
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}