package com.example.simonahorasidice.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simonahorasidice.R
import com.example.simonahorasidice.model.Colores
import com.example.simonahorasidice.modelview.MyViewModel


/**
 * Interfaz para mostrar el récord máximo del usuario en el juego.
 *
 * @param record Récord máximo del usuario.
 */
@Composable
fun Record(record: Int) {
    Text(
        text = "Record \n     $record",
        fontSize = 52.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFEAEAEA),
        modifier = Modifier.offset(y = 120.dp, x = 20.dp)

    )

}

/**
 * Interfaz con el botón de color.
 *
 * @param viewModel Instancia de MyViewModel.
 * @param listaColores Lista de colores seleccionados por el usuario.
 * @param lista_Random Lista de números aleatorios.
 * @param colorValor Valor del color.
 * @param color Color del botón.
 */
@Composable
fun buttonColor(
    viewModel: MyViewModel,
    activo: Boolean,
    colorValor: Int,
    color: Color,
    modifier: Modifier,
    shape: RoundedCornerShape
) {
    Button(
        enabled = activo,
        onClick = { viewModel.añadirColor(colorValor) },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = shape,
        modifier = modifier
    ) {}
}

/**
 * Interfaz para mostrar las rondas que lleva el usuario.
 *
 * @param numeroRondas Número de rondas que lleva el usuario.
 */
@Composable
fun rondas(numeroRondas: Int) {
    Text(
        text = "Puntos \n     $numeroRondas",
        fontWeight = FontWeight.Bold,
        fontSize = 52.sp,
        modifier = Modifier.offset(y = 120.dp, x = 235.dp),
        color = Color(0xFFEAEAEA),
    )
}

/**
 * Interfaz que muestra el botón de inicio.
 *
 * @param viewModel Instancia de MyViewModel.
 */
@Composable
fun buttonStart(viewModel: MyViewModel, activo: Boolean, modifier: Modifier,) {
    val rosa = Color(0x95D7D7D7)

    Button(
        enabled = activo,
        onClick = { viewModel.setRandom() },
        colors = ButtonDefaults.buttonColors(containerColor = rosa, contentColor = Color.Black),
        modifier = modifier
    ) {
        Text(
            "START",
            color = Color.Black,
            fontSize = 21.sp,
            fontWeight = FontWeight.Bold,
        )
    }

}

/**
 * App principal del juego.
 *
 * @param viewModel Instancia de MyViewModel.
 */
@Composable
fun UI(viewModel: MyViewModel) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state.record) {
        context.getSharedPreferences("datos_app", android.content.Context.MODE_PRIVATE)
            .edit().putInt("recordP", state.record).apply()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Record(state.record)

        buttonColor(viewModel, state.estado.botonesColoresActivos, Colores.ROJO.valorColor, state.rojo, modifier = Modifier
            .offset(y = 350.dp,x = 5.dp)
            .size(180.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Transparent, Color.Red),
                    start = Offset(740f, 740f),
                    end = Offset(10f, 10f)
                ),
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 12.dp,
                    bottomEnd = 115.dp,
                    bottomStart = 12.dp
                )
            ),
            shape = RoundedCornerShape(
                topStart = 30.dp,
                topEnd = 12.dp,
                bottomEnd = 115.dp,
                bottomStart = 12.dp
            )
        )
        buttonColor(viewModel, state.estado.botonesColoresActivos, Colores.VERDE.valorColor, state.verde, modifier = Modifier
            .offset(y = 560.dp,x = 5.dp)
            .size(180.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Transparent, Color.Green),
                    start = Offset(390f, -230f),
                    end = Offset(0f, 200f)
                ),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 115.dp,
                    bottomEnd = 12.dp,
                    bottomStart = 30.dp
                )
            ),
            shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 115.dp,
                bottomEnd = 12.dp,
                bottomStart = 30.dp
            )

        )

        buttonColor(viewModel, state.estado.botonesColoresActivos, Colores.AZUL.valorColor, state.azul, modifier = Modifier
            .offset(y = 350.dp, x = 207.dp)
            .size(180.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Transparent, Color.Blue),
                    start = Offset(-220f, 420f),
                    end = Offset(200f, 0f)
                ),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 30.dp,
                    bottomEnd = 12.dp,
                    bottomStart = 115.dp
                )
            ),
            shape = RoundedCornerShape(
                topStart = 12.dp,
                topEnd = 30.dp,
                bottomEnd = 12.dp,
                bottomStart = 115.dp
            )
        )
        buttonColor(viewModel, state.estado.botonesColoresActivos, Colores.AMARILLO.valorColor, state.amarillo, modifier = Modifier
            .size(180.dp)
            .offset(y = 560.dp, x = 207.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color.Transparent, Color.Yellow),
                    start = Offset(0f, 0f),
                    end = Offset(250f, 250f)
                ),
                shape = RoundedCornerShape(
                    topStart = 115.dp,
                    topEnd = 12.dp,
                    bottomEnd = 30.dp,
                    bottomStart = 12.dp
                )
            ),
            shape = RoundedCornerShape(
                topStart = 115.dp,
                topEnd = 12.dp,
                bottomEnd = 30.dp,
                bottomStart = 12.dp
            )
        )

        rondas(state.rondas)
        buttonStart(viewModel, state.estado.startActivo, modifier = Modifier
            .size(105.dp)
            .offset(y = 491.8.dp, x = 143.5.dp)
        )
    }
}




