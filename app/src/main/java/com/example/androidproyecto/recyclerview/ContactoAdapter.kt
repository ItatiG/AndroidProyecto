package com.example.androidproyecto.recyclerview

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproyecto.R
import com.example.androidproyecto.ui.contacto.NuevoContactoActivity
import kotlinx.android.synthetic.main.fragment_recyclerview_contacto_item.view.*

class ContactoAdapter(private val longItemClickListener: (Int) -> Unit) : RecyclerView.Adapter<ContactoAdapter.ViewHolder>()
{
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)
    {
        val tvNombre = v.txtNombreContacto
        val tvTelefono = v.txtTelefonoContacto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_recyclerview_contacto_item, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount() = Singleton.dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        holder.itemView.setOnClickListener {
            //Toast.makeText(holder.itemView.context, Singleton.dataSet.get(position).telefono, Toast.LENGTH_LONG).show()
            val intent = Intent(holder.itemView.context, NuevoContactoActivity:: class.java)
            intent.putExtra("nombre", Singleton.dataSet.get(position).nombre)
            intent.putExtra("telefono", Singleton.dataSet.get(position).telefono)
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            longItemClickListener.invoke(position)
            true
        }

        holder.tvNombre.text = Singleton.dataSet.get(position).nombre
        holder.tvTelefono.text = Singleton.dataSet.get(position).telefono
    }
}