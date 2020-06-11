package com.example.androidproyecto

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.FileProvider
import com.example.androidproyecto.data.Usuario
import com.example.androidproyecto.data.UsuarioDatabase
import com.example.androidproyecto.data.UsuarioRepository
import com.example.androidproyecto.viewmodels.UsuarioViewModel
import com.example.androidproyecto.viewmodels.UsuarioViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class RegistroActivity : AppCompatActivity()
{
    companion object
    {
        private val REQUEST_IMAGE_CAPTURE = 1
        private val REQUEST_TAKE_PHOTO = 1
        private var currentPhotoPath: String = ""
    }

    //Conexión
    private val usuarioViewModel: UsuarioViewModel by viewModels {
        UsuarioViewModelFactory(
            //Repositorio
            UsuarioRepository(UsuarioDatabase.getDatabase(this).usuarioDao())
        )
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        imgFoto.setOnClickListener{ view->
            //Intención implicita
            dispatchTakePictureIntent()
        }

        //Botón para registrar un nuevo usuario
        btnRegRegistrarse.setOnClickListener { view->
            val strUsername: String = edtRegistrarseUsuario.text.toString().trim()
            val strNombreCompleto: String = edtRegistrarseNombreCompleto.text.toString().trim()
            val strCorreoElectronico: String = edtRegistrarseEmail.text.toString().trim()
            val strContrasena: String = edtRegistrarseContrasena.text.toString().trim()
            val strRepetirContrasena: String = edtRegistrarseRepContrasena.text.toString().trim()

            val strFotoPerfil: String = "Foto"

            //Validar que los campos
            when
            {
                TextUtils.isEmpty(strUsername) -> {
                    edtRegistrarseUsuario.error = "Introduzca un usuario"
                }
                TextUtils.isEmpty(strNombreCompleto) -> {
                    edtRegistrarseNombreCompleto.error = "Introduzca su nombre completo"
                }
                TextUtils.isEmpty(strCorreoElectronico) -> {
                    edtRegistrarseEmail.error = "Introduzca un correo electrónico"
                }
                TextUtils.isEmpty(strContrasena) -> {
                    edtRegistrarseContrasena.error = "Introduzca una contraseña"
                }
                strContrasena !=  strRepetirContrasena -> {
                    edtRegistrarseRepContrasena.error = "La contraseña no coincide"
                }
                else -> {
                    val nuevoUsuario = Usuario(1, strUsername, strNombreCompleto, strCorreoElectronico, strContrasena, strFotoPerfil)

                    usuarioViewModel.guardarUsuario(nuevoUsuario)
                    crearPreferencia()
                    Snackbar.make(view, "Usuario agregado", Snackbar.LENGTH_LONG)
                        .setAction("Regresar") { onBackPressed() }.show()
                }
            }
        }
    }

    //Obtener la foto y mostrarla en un ImageView
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            Log.e("TAG", "setPic")
            //val imageBitmap = data?.extras?.get("data") as Bitmap
            //imgRegistrarseUsuario.setImageBitmap(imageBitmap)
            setPic()
        }
    }

    //@Throws(IOException::class)
    private fun crearArchivoImagen(): File
    {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    @Throws(IOException::class)
    private fun dispatchTakePictureIntent()
    {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                /*val photoFile: File? = try
                {
                    crearArchivoImagen()
                }
                catch (ex: IOException)
                {
                    null
                }
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        this,
                        "com.example.androidproyecto.fileprovider",
                        it
                    )
                    agregarFotoAGaleria()
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)*/

                var photoFile: File? = null
                try
                {
                    photoFile = crearArchivoImagen()
                }
                catch (ex: IOException)
                {
                    // Error occurred while creating the File
                }
                // Continue only if the File was successfully created
                // Continue only if the File was successfully created
                if (photoFile != null)
                {
                    val photoURI = FileProvider.getUriForFile(
                        this,
                        "com.example.androidproyecto.fileprovider",
                        photoFile
                    )
                    agregarFotoAGaleria()
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                    Log.e("TAG", "photoURI: $photoURI")
                }
            }
        }
    }

    private fun agregarFotoAGaleria()
    {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)
        }
    }

    private fun setPic()
    {
        // Get the dimensions of the View
        val targetW: Int = imgRegistrarseUsuario.width
        val targetH: Int = imgRegistrarseUsuario.height

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true

            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
            inPurgeable = true
        }
        BitmapFactory.decodeFile(currentPhotoPath, bmOptions)?.also { bitmap->
            imgRegistrarseUsuario.setImageBitmap(bitmap)
            Log.e("TAG", "currenPhotoPath: $currentPhotoPath")
        }
    }

    private fun crearPreferencia()
    {
        val sharedPref = getSharedPreferences("mi_usuario", Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString("correo", edtRegistrarseEmail.text.toString().trim())
            putString("contraseña", edtRegistrarseContrasena.text.toString().trim())
            commit()
        }
    }
}
