package com.lopez.semana04

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lopez.semana04.ui.theme.Semana04Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Semana04Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        // ✅ Fondo con imagen
                        Image(
                            painter = painterResource(id = R.drawable.fondo), // pon tu imagen en res/drawable
                            contentDescription = "Fondo",
                            contentScale = ContentScale.Crop, // para que ocupe toda la pantalla
                            modifier = Modifier.fillMaxSize()
                        )

                        // ✅ Contenido encima del fondo
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            verticalArrangement = Arrangement.spacedBy(40.dp)
                        ) {
                            ContadorScreen()
                            BloqueoContador()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ContadorScreen() {
    var ephemeralCount by remember { mutableStateOf(0) }
    var persistentCount by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(" ✏\uFE0FComparando estados", style = MaterialTheme.typography.titleLarge)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Ephemeral (remember): $ephemeralCount")
            Button(onClick = { ephemeralCount++ }) {
                Text("+1")
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Persistente (rememberSaveable): $persistentCount")
            Button(onClick = { persistentCount++ }) {
                Text("+1")
            }
        }

        Text("\uD83D\uDC64 Rota la pantalla y observa qué contador se reinicia.")
    }
}

@Composable
fun BloqueoContador() {
    var contador by rememberSaveable { mutableStateOf(0) }
    var desbloqueado by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botón para bloquear/desbloquear
        Button(onClick = { desbloqueado = !desbloqueado }) {
            Text(if (desbloqueado) "Bloquear" else "Desbloquear")
        }
        Image(
            painter = painterResource(id = R.drawable.mujer),
            contentDescription = "imagen",
            modifier = Modifier.size(100.dp)

        )

        if (desbloqueado) {
            // Si está desbloqueado, mostrar contador y botón
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Contador: $contador")
                Button(onClick = { contador++ }) {
                    Text("+1")
                }
            }
        } else {
            // Si está bloqueado, mostrar mensaje de error
            Text("\uD83D\uDD13 Contador bloqueado", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAll() {
    Semana04Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            ContadorScreen()
            BloqueoContador()
        }
    }
}
