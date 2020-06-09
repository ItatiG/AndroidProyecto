package com.example.androidproyecto.ui.inicio

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproyecto.data.Usuario
import com.example.androidproyecto.data.UsuarioRepository
import kotlinx.coroutines.launch

class InicioViewModel : ViewModel()
{
    class UsuarioViewModel(val usuarioRepository: UsuarioRepository) : ViewModel()
    {
        fun guardarUsuario(usuario: Usuario)
        {
            viewModelScope.launch {
                usuarioRepository.guardarUsuario(usuario)
            }
        }

    }
}