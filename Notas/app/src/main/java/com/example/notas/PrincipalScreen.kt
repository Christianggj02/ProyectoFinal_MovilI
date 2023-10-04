@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.notas

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontFamily.Resolver
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.notas.ui.theme.Notas
import com.example.notas.ui.theme.container_Notas
import com.example.notas.ui.theme.md_theme_light_tertiary
import com.example.notas.ui.theme.md_theme_light_tertiaryContainer

@Composable
fun Principal(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
)
{
    Column(modifier = Modifier
        .padding(vertical = 250.dp)
        .fillMaxSize()
    ) {
        NotasCard("Notas", Notas,container_Notas)
        Spacer(modifier = Modifier.padding(16.dp))
        TareasCard()
        Spacer(modifier = Modifier.padding(16.dp))
        TareasCard()
        NotasCard("Notas", Notas,container_Notas)
    }
}

@Composable
fun NotasCard(title: String, color: Color, backColor: Color,modifier: Modifier = Modifier){
    Card(
        onClick = {},
        modifier = Modifier.padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = backColor),
        border = BorderStroke(width = 5.dp, color = color),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .sizeIn(minHeight = 100.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
            ) {
                Icon(
                    Icons.Rounded.Edit,
                    contentDescription = "Notas",
                    tint = color)
                Text(
                    text = title,
                    color = color,
                    fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                    fontSize = 24.sp,
                    )
            }
        }
    }
}

@Composable
fun TareasCard(modifier: Modifier = Modifier){
    Card(
        onClick = {},
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
                    Icons.Rounded.Edit,
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


