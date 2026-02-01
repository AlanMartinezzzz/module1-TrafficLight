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


    // pare centrar
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TrafficLight(
            activeLight = currentLight
        )
    }
}


@Composable
fun TrafficLight(activeLight: Light) {

    // Contenedor del semáforo
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Luz Roja
        LightCircle(
            color = Color.Red,
            inOn = activeLight == Light.Red
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Luz Amarilla
        LightCircle(
            color = Color.Yellow,
            inOn = activeLight == Light.Yellow
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Luz Verde
        LightCircle(
            color = Color.Green,
            inOn = activeLight == Light.Green
        )
    }
}


@Composable
fun LightCircle(color: Color, inOn: Boolean) {

    // -----------------------------------------
    // Círculo con estado activo e inactivo
    // activo brillante, inactivo tipo gris
    // -----------------------------------------
    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(
                if (inOn) color
                else color.copy(alpha = 0.2f)
            )
    )
}
