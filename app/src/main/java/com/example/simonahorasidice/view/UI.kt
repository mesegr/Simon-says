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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
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
    listaColores: MutableList<Int>,
    lista_Random: MutableList<Int>,
    colorValor: Int,
    color: Color,
    modifier: Modifier,
    shape: RoundedCornerShape
) {
    var _activo by remember { mutableStateOf(viewModel.estadosLiveData.value!!.botonesColoresActivos) }

    viewModel.estadosLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadosLiveData.value!!.botonesColoresActivos
    }

    Button(
        enabled = _activo,
        onClick = {
            viewModel.añadirColor(colorValor, listaColores, lista_Random)
        },
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
fun buttonStart(viewModel: MyViewModel,modifier: Modifier,) {
    var _activo by remember { mutableStateOf(viewModel.estadosLiveData.value!!.startActivo) }

    viewModel.estadosLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadosLiveData.value!!.startActivo
    }

    val rosa = Color(0x95D7D7D7)

    Button(
        enabled = _activo,
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
    val record by viewModel.recordLiveData.observeAsState(viewModel.getRecord())
    val rondas by viewModel.rondasLiveData.observeAsState(viewModel.getRondas())

    var lista_colores = remember { mutableStateListOf<Int>() }

    val colorRojo by viewModel.rojoLiveData.observeAsState(viewModel.getRed())
    val colorVerde by viewModel.verdeLiveData.observeAsState(viewModel.getGreen())
    val colorAzul by viewModel.azulLiveData.observeAsState(viewModel.getBlue())
    val colorAmarillo by viewModel.amarilloLiveData.observeAsState(viewModel.getYellow())

    Box(modifier = Modifier.fillMaxSize()) {
        val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Record(record)

        buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.ROJO.valorColor, colorRojo,    modifier = Modifier
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
        buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.VERDE.valorColor, colorVerde, modifier = Modifier
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

        buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.AZUL.valorColor, colorAzul, modifier = Modifier
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
        buttonColor(viewModel, lista_colores, viewModel.getRandom(), Colores.AMARILLO.valorColor, colorAmarillo, modifier = Modifier
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

        rondas(rondas)
        buttonStart(viewModel, modifier = Modifier
            .size(105.dp)
            .offset(y = 491.8.dp, x = 143.5.dp)
        )
    }
}




