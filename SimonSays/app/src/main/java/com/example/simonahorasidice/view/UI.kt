package com.example.simonahorasidice.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
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
        text = "Récord\n$record",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFEAEAEA),
    )

}

/**
 * Interfaz con el botón de color.
 *
 * @param viewModel Instancia de MyViewModel.
 * @param colorValor Valor del color.
 */
@Composable
fun buttonColor(
    viewModel: MyViewModel,
    activo: Boolean,
    colorValor: Int,
    modifier: Modifier,
    shape: RoundedCornerShape
) {
    Button(
        enabled = activo,
        onClick = { viewModel.añadirColor(colorValor) },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        ),
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
        text = "Puntos\n$numeroRondas",
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
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
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
        modifier = modifier
    ) {
        BoxWithConstraints {
            val textSize = minOf(21f, maxWidth.value / 3.4f, maxHeight.value * 0.55f)
                .coerceAtLeast(8f)
                .sp
            Text(
                "START",
                color = Color.Black,
                fontSize = textSize,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                softWrap = false,
            )
        }
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

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val topOffset = (maxHeight * 0.20f).coerceIn(72.dp, 160.dp)
        val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = topOffset, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Record(state.record)
                rondas(state.rondas)
            }

            BoxWithConstraints(
                modifier = Modifier.fillMaxWidth().weight(1f),
                contentAlignment = Alignment.Center
            ) {
                val boardSide = minOf(maxWidth, maxHeight)
                val gap = 12.dp
                val outerCorner = 36.dp
                val centerCorner = 110.dp
                val edgeCorner = 12.dp
                val buttonSidePx = with(LocalDensity.current) { ((boardSide - gap) / 2).toPx() }
                val redShape = RoundedCornerShape(
                    topStart = outerCorner, topEnd = edgeCorner,
                    bottomEnd = centerCorner, bottomStart = edgeCorner
                )
                val blueShape = RoundedCornerShape(
                    topStart = edgeCorner, topEnd = outerCorner,
                    bottomEnd = edgeCorner, bottomStart = centerCorner
                )
                val greenShape = RoundedCornerShape(
                    topStart = edgeCorner, topEnd = centerCorner,
                    bottomEnd = edgeCorner, bottomStart = outerCorner
                )
                val yellowShape = RoundedCornerShape(
                    topStart = centerCorner, topEnd = edgeCorner,
                    bottomEnd = outerCorner, bottomStart = edgeCorner
                )

                Box(modifier = Modifier.size(boardSide)) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(gap)
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(gap)
                        ) {
                            buttonColor(
                                viewModel, state.estado.botonesColoresActivos,
                                Colores.ROJO.valorColor,
                                modifier = Modifier.weight(1f).fillMaxHeight()
                                    .background(
                                        Brush.linearGradient(
                                            listOf(state.rojo, Color.Transparent),
                                            start = Offset.Zero,
                                            end = Offset(buttonSidePx, buttonSidePx)
                                        ), redShape
                                    ), shape = redShape
                            )
                            buttonColor(
                                viewModel, state.estado.botonesColoresActivos,
                                Colores.AZUL.valorColor,
                                modifier = Modifier.weight(1f).fillMaxHeight()
                                    .background(
                                        Brush.linearGradient(
                                            listOf(state.azul, Color.Transparent),
                                            start = Offset(buttonSidePx, 0f),
                                            end = Offset(0f, buttonSidePx)
                                        ), blueShape
                                    ), shape = blueShape
                            )
                        }
                        Row(
                            modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.spacedBy(gap)
                        ) {
                            buttonColor(
                                viewModel, state.estado.botonesColoresActivos,
                                Colores.VERDE.valorColor,
                                modifier = Modifier.weight(1f).fillMaxHeight()
                                    .background(
                                        Brush.linearGradient(
                                            listOf(state.verde, Color.Transparent),
                                            start = Offset(0f, buttonSidePx),
                                            end = Offset(buttonSidePx, 0f)
                                        ), greenShape
                                    ), shape = greenShape
                            )
                            buttonColor(
                                viewModel, state.estado.botonesColoresActivos,
                                Colores.AMARILLO.valorColor,
                                modifier = Modifier.weight(1f).fillMaxHeight()
                                    .background(
                                        Brush.linearGradient(
                                            listOf(state.amarillo, Color.Transparent),
                                            start = Offset(buttonSidePx, buttonSidePx),
                                            end = Offset.Zero
                                        ), yellowShape
                                    ), shape = yellowShape
                            )
                        }
                    }

                    buttonStart(
                        viewModel,
                        state.estado.startActivo,
                        modifier = Modifier.align(Alignment.Center).size(minOf(112.dp, boardSide * 0.28f))
                    )
                }
            }
        }
    }
}




