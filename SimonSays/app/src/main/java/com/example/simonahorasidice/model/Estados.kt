package com.example.simonahorasidice.model

/**
 * Clase enum para manejar los estados de la aplicación.
 */
enum class Estados(val startActivo: Boolean, val botonesColoresActivos: Boolean) {

    /**
     * Estado de la aplicación cuando se inicia y aún no se ha presionado el botón de inicio.
     */
    INICIO(startActivo = true, botonesColoresActivos = false),

    /**
     * Estado de la aplicación cuando el usuario está adivinando los números presionando los botones de colores.
     */
    ADIVINANDO(startActivo = false, botonesColoresActivos = true),
}
