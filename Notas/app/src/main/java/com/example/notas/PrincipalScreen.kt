@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.notas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.notas.ui.theme.Notas
import com.example.notas.ui.theme.container_Notas
import com.example.notas.ui.theme.md_theme_light_tertiary
import com.example.notas.ui.theme.md_theme_light_tertiaryContainer

enum class InicioScreen() {
    Start,
    Flavor,
    Pickup,
    Summary
}

@Composable
fun Principal(
    onNavigateToNotas: () -> Unit,
    onNavigateToTareas: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
)
{
    val navController = rememberNavController()
    Column(modifier = modifier) {
        NotasCard(onNavigateToNotas = onNavigateToNotas)
        Spacer(modifier = Modifier.padding(16.dp))
        TareasCard(onNavigateToTareas = onNavigateToTareas)
    }
}

@Composable
fun NotasCard(modifier: Modifier = Modifier, onNavigateToNotas: () -> Unit){
    Card(
        onClick = onNavigateToNotas,
        modifier = Modifier.padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = container_Notas),
        border = BorderStroke(width = 5.dp, color = Notas),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = 150.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = "Notas",
                    tint = Notas)
                Text(
                    text = "Notas",
                    color = Notas,
                    fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                    fontSize = 24.sp,
                    )
            }
        }
    }
}

@Composable
fun TareasCard(modifier: Modifier = Modifier, onNavigateToTareas: () -> Unit){
    Card(
        onClick = onNavigateToTareas,
        modifier = Modifier.padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = md_theme_light_tertiaryContainer),
        border = BorderStroke(width = 5.dp, color = md_theme_light_tertiary),

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = 150.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Icon(
                    Icons.Rounded.Notifications,
                    contentDescription = "Notas",
                    tint = md_theme_light_tertiary)
                Text(
                    text = "Tareas",
                    color = md_theme_light_tertiary,
                    fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                    fontSize = 24.sp,
                )
            }
        }
    }
}


