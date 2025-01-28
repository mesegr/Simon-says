package com.example.simonahorasidice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.simonahorasidice.modelview.MyViewModel
import com.example.simonahorasidice.view.*

/**
 * Clase MainActivity donde se ejecuta la aplicación.
 */
class MainActivity : ComponentActivity() {
    /**
     * Llamado cuando la actividad está comenzando. Aquí es donde debe ir la mayor parte de la inicialización.
     *
     * @param savedInstanceState Si la actividad se está re-inicializando después de haber sido previamente cerrada, entonces este Bundle contiene los datos que más recientemente proporcionó en onSaveInstanceState(Bundle).
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Crear un objeto de la clase MyViewModel y pasarlo a la función composable UI
        val viewModel = MyViewModel()
        enableEdgeToEdge()
        setContent {
            UI(viewModel)
        }
    }
}










