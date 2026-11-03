package com.example.simonahorasidice.modelview

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simonahorasidice.model.ColoresIluminados
import com.example.simonahorasidice.model.Estados
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

data class JuegoUiState(
    val record: Int = 0,
    val rondas: Int = 0,
    val secuencia: List<Int> = emptyList(),
    val coloresSeleccionados: List<Int> = emptyList(),
    val estado: Estados = Estados.INICIO,
    val rojo: Color = ColoresIluminados.ROJO_PARPADEO.colorNormal,
    val verde: Color = ColoresIluminados.VERDE_PARPADEO.colorNormal,
    val azul: Color = ColoresIluminados.AZUL_PARPADEO.colorNormal,
    val amarillo: Color = ColoresIluminados.AMARILLO_PARPADEO.colorNormal
)

/** ViewModel y única fuente del estado del juego. */
class MyViewModel(initialRecord: Int = 0) : ViewModel() {
    private val _uiState = MutableStateFlow(JuegoUiState(record = initialRecord))
    val uiState = _uiState.asStateFlow()

    fun setRandom() {
        val secuencia = _uiState.value.secuencia + Random.nextInt(1, 5)
        _uiState.update { it.copy(secuencia = secuencia, estado = Estados.ADIVINANDO) }
        cambiarColores(secuencia)
    }

    fun añadirColor(numero: Int) {
        val state = _uiState.value
        if (!state.estado.botonesColoresActivos || state.coloresSeleccionados.size >= state.secuencia.size) return

        val seleccionados = state.coloresSeleccionados + numero
        _uiState.update { it.copy(coloresSeleccionados = seleccionados) }

        if (seleccionados != state.secuencia.take(seleccionados.size)) {
            _uiState.update {
                it.copy(rondas = 0, secuencia = emptyList(), coloresSeleccionados = emptyList(), estado = Estados.INICIO)
            }
        } else if (seleccionados == state.secuencia) {
            val rondas = state.rondas + 1
            _uiState.update {
                it.copy(rondas = rondas, record = maxOf(it.record, rondas), coloresSeleccionados = emptyList(), estado = Estados.INICIO)
            }
        }
    }

    private fun cambiarColores(secuencia: List<Int>) {
        viewModelScope.launch {
            secuencia.forEach { numero ->
                delay(100)
                _uiState.update { state -> state.withColor(numero, illuminated = true) }
                delay(350)
                _uiState.update { state -> state.withColor(numero, illuminated = false) }
                delay(350)
            }
        }
    }

    private fun JuegoUiState.withColor(numero: Int, illuminated: Boolean): JuegoUiState {
        val color = when (numero) {
            1 -> if (illuminated) Color(0xFFFF9999) else ColoresIluminados.ROJO_PARPADEO.colorNormal
            2 -> if (illuminated) Color(0xFFA8FFAA) else ColoresIluminados.VERDE_PARPADEO.colorNormal
            3 -> if (illuminated) Color(0xFF5F85FF) else ColoresIluminados.AZUL_PARPADEO.colorNormal
            else -> if (illuminated) Color(0xFFFCFFBE) else ColoresIluminados.AMARILLO_PARPADEO.colorNormal
        }
        return when (numero) {
            1 -> copy(rojo = color)
            2 -> copy(verde = color)
            3 -> copy(azul = color)
            else -> copy(amarillo = color)
        }
    }
}
