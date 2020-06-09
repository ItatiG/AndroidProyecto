package com.example.androidproyecto.ui.contacto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidproyecto.R

class ContactoFragment : Fragment() {

    private lateinit var contactoViewModel: ContactoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contactoViewModel =
            ViewModelProviders.of(this).get(ContactoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_recyclerview_contacto, container, false)
        //val textView: TextView = root.findViewById(R.id.text_gallery)
        contactoViewModel.text.observe(viewLifecycleOwner, Observer {
            //textView.text = it
        })
        return root
    }
}
