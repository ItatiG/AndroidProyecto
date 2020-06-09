package com.example.androidproyecto.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidproyecto.data.UsuarioRepository

//Clase para pasar el repositorio a la Main
class UsuarioViewModelFactory(private val usuarioRepository: UsuarioRepository) : ViewModelProvider.NewInstanceFactory()
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T
    {
        return UsuarioViewModel(usuarioRepository) as T
    }
}