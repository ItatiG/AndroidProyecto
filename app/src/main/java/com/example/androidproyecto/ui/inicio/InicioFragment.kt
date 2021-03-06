package com.example.androidproyecto.ui.inicio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidproyecto.R

class InicioFragment : Fragment()
{
    private lateinit var inicioViewModel: InicioViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inicioViewModel =
            ViewModelProviders.of(this).get(InicioViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_inicio, container, false)
        //val textView: TextView = root.findViewById(R.id.text_home)
        /*inicioViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/
        return root
    }
}
