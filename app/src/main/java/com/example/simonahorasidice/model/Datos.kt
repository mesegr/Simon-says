package com.example.simonahorasidice.model

/**
 * Singleton con los datos de la aplicación.
 */
object Datos {
    /**
     * Las rondas que lleva el usuario.
     */
    var rondasP = 0

    /**
     * Los números aleatorios para meter en la lista a adivinar.
     */
    var random = 0

    /**
     * El récord del jugador.
     */
    var recordP = 0

    /**
     * La lista de números aleatorios a adivinar.
     */
    val listaRandom: MutableList<Int> = mutableListOf()

    /**
     * La lista de colores seleccionados por el usuario.
     */
    var listaColores: MutableList<Int> = mutableListOf()
}