package rojas.carlos.tappsi_test.adapters

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso

import rojas.carlos.tappsi_test.R
import  rojas.carlos.tappsi_test.models.Album
/**
 * Created by carlos on 24/08/2017.
 */
class AlbumArrayAdapter (private val contexto:Context,
                         private val albumes:List<Album>,
                         private var resource: Int):
        ArrayAdapter<Album>(contexto,resource,albumes) {

    override fun getView(posicion: Int, convertView:View?, parent:ViewGroup): View{
        var album = albumes[posicion]
        var inflater:LayoutInflater = contexto.getSystemService(Activity.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater
        var vista = convertView ?: inflater.inflate(R.layout.item_lista, null)
        var itemLista = vista.findViewById<TextView>(R.id.itemLista)
        itemLista.setText(album.title)
        var itemImagen = vista.findViewById<ImageView>(R.id.imageViewItem)
        Picasso.with(contexto).
                load(album.coverImageUrlMobile).
                resize(125, 125).
                into(itemImagen)
        return vista
    }
}