package rojas.carlos.tappsi_test.servicios

/**
 * Created by carlos on 25/08/2017.
 */

import org.json.JSONArray

import rojas.carlos.tappsi_test.models.Album


fun convertJSONArrayToAlbumList (arregloJson:JSONArray):List<Album>{
    var listadoAlbumes:MutableList<Album> = mutableListOf()
    var objetoJson =  arregloJson.getJSONObject(0)
    var nodos = objetoJson.getJSONArray("nodes")
    var longitud = nodos.length()
    var i = 0
    while (i<longitud){
        var nodo = nodos.getJSONObject(i)
        var albumId = nodo.getString("id")
        var albumTitle = nodo.getString("title")
        var coverImageUrl = nodo.getString("coverImageUrl")
        var coverImageUrlMobile = nodo.getString("coverImageUrl-mobile")
        listadoAlbumes.add(Album(albumId, albumTitle, coverImageUrl, coverImageUrlMobile))
        i++
    }
    return listadoAlbumes
}
