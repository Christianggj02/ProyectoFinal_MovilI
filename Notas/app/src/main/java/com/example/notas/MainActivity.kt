package com.example.notas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.NotasTheme
import com.example.notas.ui.theme.PrincipalTheme
import com.example.notas.ui.theme.seed
import com.example.notas.ui.theme.tareas.TareasTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrincipalTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavHost()
                }
            }
        }
    }
}

@Composable
fun MyAppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "inicio"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("inicio") {
            Inicio(
                onNavigateToCrearNota = { navController.navigate("crearNota") },
                onNavigateToNotas = {navController.navigate("Notas")},
                onNavigateToTareas = {navController.navigate("Tareas")}
            )
        }
        composable("crearNota") {
            CrearNota(navController = navController)
        }
        composable("Notas") {
            NotasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Notas()
                }
            }
        }
        composable("Tareas") {
            TareasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tareas()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inicio(
    onNavigateToCrearNota: () -> Unit,
    onNavigateToNotas: () -> Unit,
    onNavigateToTareas: () -> Unit
    ) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                titulo = R.string.app_name,
                backgrounColor = MaterialTheme.colorScheme.background,
                color = seed,
                modifier = Modifier.sizeIn(maxHeight = 224.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToCrearNota,
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = seed,
                shape = CircleShape,
                modifier = Modifier.border(width = 8.dp, color = seed, shape = CircleShape),
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(1.dp)
                ) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "Add",
                    )
            }
        }
    ){
        Principal(
            onNavigateToNotas,
            onNavigateToTareas,
            modifier = Modifier.padding(top = 280.dp),
            contentPadding = it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    @StringRes titulo: Int,
    backgrounColor: Color,
    color: Color,
    modifier: Modifier = Modifier,
    expanded: Dp = 224.dp,
    titlePadding: Dp = 70.dp
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(titulo),
                fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                color = color,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(top = titlePadding)
            )
        },
        modifier = modifier.height(expanded)
            /*.fillMaxHeight(expanded)*/,
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = backgrounColor,
            titleContentColor = color,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PrincipalTheme {
        MyAppNavHost()
    }
}