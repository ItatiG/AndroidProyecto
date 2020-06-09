package com.example.androidproyecto.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproyecto.data.Usuario
import com.example.androidproyecto.data.UsuarioRepository
import kotlinx.coroutines.launch

class UsuarioViewModel(val usuarioRepository: UsuarioRepository): ViewModel()
{
    fun guardarUsuario(usuario: Usuario)
    {
        viewModelScope.launch {
            usuarioRepository.guardarUsuario(usuario)
        }
    }

    /*
    fun iniciarSesion(email: String, password: String): Boolean
    {
        try {
            Log.e("TAG", "Login: ")
            viewModelScope.launch {
                usuarioRepository.iniciarSesionLogin(email, contrasena)
            }
        }
        catch (ex: Exception)
        {
            Log.e("TAG", "Error $ex")
        }
        return true
    }*/
}