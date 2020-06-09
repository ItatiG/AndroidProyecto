package com.example.androidproyecto.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsuarioDao
{
    //Insertar un nuevo usuario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun guardarUsuario(usuario: Usuario)

    //Seleccionar un solo usuario por su ID
    @Query("SELECT * FROM datos_usuario WHERE id = :id")
    fun obtenerUsuario(id: Int): LiveData<Usuario>

    @Query("SELECT * FROM datos_usuario")
    fun obtenerUsuarios(): LiveData<List<Usuario>>

    //Para validar el Login
    @Query("SELECT * FROM datos_usuario WHERE email = :email AND password = :password")
    fun iniciarSesionUsuario(email: String, password: String): Boolean
}