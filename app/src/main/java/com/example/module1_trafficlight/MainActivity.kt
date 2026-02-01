package com.example.module1_trafficlight


// imports que nos ayudara en el trascurso de la app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


// vamos a trabar solo con 3 colores de semaforo, estas son especiales asi para código más limpio
enum class Light {
    Red, Yellow, Green
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TrafficLightScreen()
            }
        }
    }
}


@Composable
fun TrafficLightScreen() {
    // el valor va a cambiar conservando el valor en recomposiciones como estado inicial el rojo
    var currentLight by remember { mutableStateOf(Light.Red) }

    // se ejecutara una vez en la primera recomposición
    LaunchedEffect(Unit) {
        while (true) { // para el Loop infinito

            currentLight = Light.Red
            delay(2000) // 2 segundos

            currentLight = Light.Green
            delay(2000) // 2 segundos

            currentLight = Light.Yellow
            delay(1000) // 1 segundo
        }
    }
}
