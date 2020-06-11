package com.example.androidproyecto.ui.contacto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.androidproyecto.R
import com.example.androidproyecto.recyclerview.Contacto
import com.example.androidproyecto.recyclerview.ContactoAdapter
import com.example.androidproyecto.recyclerview.Singleton
import kotlinx.android.synthetic.main.activity_prueba.*
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import org.json.JSONArray
import org.json.JSONObject

class ContactoRecyclerviewFragment : Fragment()
{
    val onLongItemClickListener: (Int) -> Unit = { position ->

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState).apply {
            LoadData()

            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = ContactoAdapter(onLongItemClickListener)
        }
    }

    private fun LoadData()
    {
        for (x in 0..20) {
            Singleton.dataSet.add(
                Contacto(
                    "Nombre: ${x.toString().padStart(2, '0')}",
                    "Teléfono: ${x.toString().padStart(5, '2')}"
                )
            )
        }
    }
}