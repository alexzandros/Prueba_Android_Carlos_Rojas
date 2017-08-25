package rojas.carlos.tappsi_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.android.volley.*
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.Response.Listener
import com.android.volley.toolbox.Volley
import org.json.JSONArray

import kotlinx.android.synthetic.main.activity_albums.*
import kotlinx.android.synthetic.main.content_albums.view.*

import rojas.carlos.tappsi_test.adapters.AlbumArrayAdapter
import rojas.carlos.tappsi_test.models.Album
import rojas.carlos.tappsi_test.servicios.convertJSONArrayToAlbumList
import rojas.carlos.tappsi_test.models.Tokens

class AlbumsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        setSupportActionBar(toolbar)

        var cola:RequestQueue = Volley.newRequestQueue(this)
        var listadoAlbumes: List<Album> = listOf()
        val url="https://api.gfycat.com/v1/me/album-folders"
        val peticion =  object : JsonArrayRequest(Request.Method.GET,
                url,null,
                Listener { x:JSONArray ->
                    listadoAlbumes = convertJSONArrayToAlbumList(x)
                    val adaptador:ArrayAdapter<Album> = AlbumArrayAdapter(
                            this, listadoAlbumes, 0)
                    contenido.listaAlbumesGrilla?.adapter = adaptador
                    contenido.listaAlbumes?.adapter = adaptador
                },
                Response.ErrorListener { x ->
                    VolleyLog.e("%s", x.toString())}){
            override fun getHeaders(): MutableMap<String, String> {
                var parametros: MutableMap<String, String>  =  HashMap()
                parametros.put("Authorization","Bearer " + Tokens.instancia.accesToken)
                parametros.put("Content-Type","application/json")
                parametros.put("Accept","application/json")
                return parametros
            }
        }
        cola.add(peticion)
    }
}
