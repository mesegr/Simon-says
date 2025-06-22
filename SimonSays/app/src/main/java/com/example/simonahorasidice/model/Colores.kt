package com.example.simonahorasidice.model

import androidx.compose.ui.graphics.Color
/**
 * Clase enum que representa diferentes colores con sus valores enteros correspondientes.
 *
 * @property valorColor El valor entero asociado con el color.
 */
enum class Colores(val valorColor: Int) {
    ROJO(1),
    VERDE(2),
    AZUL(3),
    AMARILLO(4)
}

/**
 * Clase enum que representa colores iluminados con sus estados normal e iluminado.
 *
 * @property colorNormal El estado normal del color.
 * @property colorIluminado El estado iluminado del color, por defecto es transparente.
 */
enum class ColoresIluminados(var colorNormal: Color, var colorIluminado: Color = Color.Transparent) {
    ROJO_PARPADEO(colorNormal = Color(0xFFFF0000)),
    VERDE_PARPADEO(colorNormal = Color(0xFF00FE08)),
    AZUL_PARPADEO(colorNormal = Color(0xFF0017FF)),
    AMARILLO_PARPADEO(colorNormal = Color(0xFFF0FF00))
}