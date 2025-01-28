package com.example.simonahorasidice.modelview

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simonahorasidice.model.ColoresIluminados
import com.example.simonahorasidice.model.Datos
import com.example.simonahorasidice.model.Estados
import kotlin.random.Random
import com.example.simonahorasidice.model.Datos.recordP
import com.example.simonahorasidice.model.Datos.rondasP
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * ViewModel para manejar la lógica de la aplicación.
 */
class MyViewModel(): ViewModel() {

    var random = Random

    val estadosLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    private val recordData = MutableLiveData<Int>()
    val recordLiveData: LiveData<Int> get() = recordData

    private val rondasData = MutableLiveData<Int>()
    val rondasLiveData: LiveData<Int> get() = rondasData

    private var rojoData = MutableLiveData<Color>()
    val rojoLiveData : LiveData<Color> get() = rojoData

    private var verdeData = MutableLiveData<Color>()
    val verdeLiveData : LiveData<Color> get() = verdeData

    private var azulData = MutableLiveData<Color>()
    val azulLiveData : LiveData<Color> get() = azulData

    private var amarilloData = MutableLiveData<Color>()
    val amarilloLiveData : LiveData<Color> get() = amarilloData

    init {
        rojoData.value = ColoresIluminados.ROJO_PARPADEO.colorNormal
        verdeData.value = ColoresIluminados.VERDE_PARPADEO.colorNormal
        azulData.value = ColoresIluminados.AZUL_PARPADEO.colorNormal
        amarilloData.value = ColoresIluminados.AMARILLO_PARPADEO.colorNormal
    }

    init {
        recordData.value = recordP
        rondasData.value = rondasP
    }

    /**
     * Incrementa el número de rondas.
     */
    fun incrementoRondas() {
        rondasP += 1
        rondasData.value = rondasP
    }

    /**
     * Incrementa el récord si los aciertos actuales son mayores.
     */
    fun incrementoRecord() {
        if (getRecord() < getRondas()) {
            recordP = rondasP
            recordData.value = recordP
        }
    }

    /**
     * Incrementa aciertos, rondas y récord.
     */
    fun incrementarTodo(){
        incrementoRondas()
        incrementoRecord()
    }

    /**
     * Restaura los valores de aciertos y rondas a cero.
     */
    fun restaurarValores(){
        rondasP = 0
        rondasData.value = rondasP
    }

    /**
     * Obtiene el número de rondas.
     */
    fun getRondas():Int{
        return rondasP
    }

    /**
     * Obtiene el récord.
     */
    fun getRecord():Int{
        return recordP
    }

    /**
     * Genera una secuencia de números aleatorios.
     */
    fun setRandom(){
        Datos.random = random.nextInt(4) + 1
        Datos.listaRandom.add(Datos.random)
        estadosLiveData.value = Estados.ADIVINANDO
        cambiarColores(Datos.listaRandom)
        Log.d("Random", Datos.listaRandom.toString())
    }

    /**
     * Añade un color a la lista de colores seleccionados por el usuario.
     */
    fun añadirColor(numero:Int, listaColoresR: MutableList<Int>, lista_Random:MutableList<Int>){

        listaColoresR.add(numero)
        Datos.listaColores = listaColoresR
        resultado(lista_Random, listaColoresR)

    }

    /**
     * Devuelve la lista de números aleatorios.
     */
    fun getRandom():MutableList<Int>{
        return Datos.listaRandom
    }

    /**
     * Limpia la lista de números aleatorios.
     */
    fun borrarListaRandoms(){
        Datos.listaRandom.clear()
    }

    /**
     * Limpia la lista de colores seleccionados por el usuario.
     */
    fun borrarListaColores(lista:MutableList<Int>){
        lista.clear()
    }

    /**
     * Verifica si el usuario ha ganado o perdido la partida.
     */
    fun resultado(lista_Random: MutableList<Int>, listaColores: MutableList<Int>){
        if(listaColores.size <= lista_Random.size){
            resultadoAUX(lista_Random, listaColores)
        }
    }

    private fun resultadoAUX(lista_Random:MutableList<Int>, listaColores:MutableList<Int>){
        if(lista_Random == listaColores){
            ganar(listaColores)
            Log.d("random", "ganaste")
            Log.d("randomRe", getRecord().toString())
            Log.d("randomRor", getRondas().toString())
        }
        else if (lista_Random.subList(0, listaColores.size) == listaColores){
            Log.d("TAG", "CORRECTO")
        }
        else{
            Log.d("random", "perdiste")
            perder(listaColores)
        }
    }

    /**
     * Lógica para cuando el usuario gana una ronda.
     */
    fun ganar(listaColores: MutableList<Int>) {
        estadosLiveData.value = Estados.INICIO
        incrementarTodo()
        borrarListaColores(listaColores)
    }

    /**
     * Lógica para cuando el usuario pierde una ronda.
     */
    fun perder(listaColores: MutableList<Int>){
        estadosLiveData.value = Estados.INICIO
        restaurarValores()
        borrarListaColores(listaColores)
        borrarListaRandoms()
    }

    private fun cambiarColores(lista_Random: MutableList<Int>){
        viewModelScope.launch {
            for(i in 0 until lista_Random.size){
                if(lista_Random[i] == 1){
                    delay(100)
                    rojoData.value = Color(0xFFFF9999)
                    delay(350)
                    rojoData.value = ColoresIluminados.ROJO_PARPADEO.colorNormal
                    delay(350)
                }
                else if(lista_Random[i] == 2){
                    delay(100)
                    verdeData.value = Color(0xFFA8FFAA)
                    delay(350)
                    verdeData.value = ColoresIluminados.VERDE_PARPADEO.colorNormal
                    delay(350)
                }
                else if(lista_Random[i] == 3){
                    delay(100)
                    azulData.value = Color(0xFF5F85FF)
                    delay(350)
                    azulData.value = ColoresIluminados.AZUL_PARPADEO.colorNormal
                    delay(350)
                }
                else if (lista_Random[i] == 4){
                    delay(100)
                    amarilloData.value = Color(0xFFFCFFBE)
                    delay(350)
                    amarilloData.value = ColoresIluminados.AMARILLO_PARPADEO.colorNormal
                    delay(350)
                }
            }
        }
    }

    fun getRed():Color{
        return ColoresIluminados.ROJO_PARPADEO.colorNormal
    }
    fun getGreen():Color{
        return ColoresIluminados.VERDE_PARPADEO.colorNormal
    }
    fun getBlue():Color{
        return ColoresIluminados.AZUL_PARPADEO.colorNormal
    }
    fun getYellow():Color{
        return ColoresIluminados.AMARILLO_PARPADEO.colorNormal
    }
}

