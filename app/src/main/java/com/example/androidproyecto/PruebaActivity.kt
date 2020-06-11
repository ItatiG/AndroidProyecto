package com.example.androidproyecto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.androidproyecto.recyclerview.Contacto
import com.example.androidproyecto.recyclerview.Singleton
import com.example.androidproyecto.recyclerview.Singleton.dataSet
import kotlinx.android.synthetic.main.activity_prueba.*
import org.json.JSONArray
import org.json.JSONObject

class PruebaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba)

        val tvResultado = findViewById<TextView>(R.id.txtResultado)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        //val url = "http://www.google.com"
        val url = "http://tutti-frutti-notes.company/emergencia/obtenerTelefonos.php"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
                val jsonArray = JSONArray(response)

                for (i in 0 until jsonArray.length())
                {
                    val jsonObject = jsonArray[i]
                    tvResultado.text = jsonObject.toString()

                    //Toast.makeText(applicationContext, tvNombre.toString(), Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                tvResultado.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)

        /*val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                //textView.text = "Response: %s".format(response.toString())
                for(i in 0 until response.length())
                {
                    var jsonObject = response.getJSONObject(i)
                    var nombreContacto: String = jsonObject.getString("nombre")
                    txtResultado.text = jsonObject.getString("nombre")
                    //Singleton.dataSet.add(nombreContacto.toString())
                }
            },
            Response.ErrorListener { error ->
            }
        )

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest)*/
    }
}
