package rojas.carlos.tappsi_test.models

/**
 * Created by carlos on 25/08/2017.
 */
public class Tokens private constructor(){
    init {

    }

    private object Holder {val INSTANCIA = Tokens()}

    companion object {
        val instancia: Tokens by lazy { Holder.INSTANCIA }
    }

    var accesToken:String? = null
    var refreshToken:String? = null

}