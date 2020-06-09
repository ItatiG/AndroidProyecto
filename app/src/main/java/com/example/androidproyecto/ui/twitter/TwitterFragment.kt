package com.example.androidproyecto.ui.twitter

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.androidproyecto.R

class TwitterFragment : Fragment() {

    companion object {
        fun newInstance() = TwitterFragment()
    }

    private lateinit var viewModel: TwitterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_twitter, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TwitterViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
