package com.example.androidproyecto.data

class UsuarioRepository(val usuarioDao: UsuarioDao)
{
    suspend fun guardarUsuario(usuario: Usuario) = usuarioDao.guardarUsuario(usuario)
    fun obtenerUsuario(id: Int) = usuarioDao.obtenerUsuario(id)
    fun obtenerUsuarios() = usuarioDao.obtenerUsuarios()
    fun iniciarSesionUsuario(email: String, password: String): Boolean = usuarioDao.iniciarSesionUsuario(email, password)
}