package rojas.carlos.tappsi_test

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import com.android.volley.*
import com.android.volley.Response.Listener
import com.android.volley.Response.ErrorListener
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import android.util.Log
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_login.*

import rojas.carlos.tappsi_test.models.Tokens

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var colaPeticiones = Volley.newRequestQueue(this)

        email_sign_in_button.setOnClickListener({ _ ->
            val nombre = email.text.toString()
            val contraseña = password.text.toString()
            var credenciales: MutableMap<String,String> = mutableMapOf()
            credenciales.put("grant_type", getString(R.string.gfy_grant_type))
            credenciales.put("client_id", getString(R.string.gfy_client_id))
            credenciales.put("client_secret", getString(R.string.gfy_client_secret))
            credenciales.put("username", nombre)
            credenciales.put("password", contraseña)
            Log.v("credenciales", JSONObject(credenciales).toString())
            var peticion = JsonObjectRequest(Request.Method.POST, getString(R.string.gfy_auth_url),
                    JSONObject(credenciales) ,
                    Listener { x ->
                        print(x.toString())
                        Tokens.instancia.accesToken = x.getString("access_token")
                        Tokens.instancia.refreshToken = x.getString("refresh_token")
                        var intento = Intent(this, AlbumsActivity::class.java )
                        startActivity(intento)
                    },
                    ErrorListener { x ->
                        Toast.makeText (this,"Nombre de usuario o contraseña incorrectos", Toast.LENGTH_LONG).show()
                        email.text.clear()
                        password.text.clear()
                    })
            colaPeticiones.add(peticion)
        })
    }
}
