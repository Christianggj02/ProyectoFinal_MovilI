@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.notas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.compose.NotasTheme
import com.example.notas.model.Nota
import com.example.notas.model.NotasModelRepository
import com.example.notas.ui.theme.md_theme_light_onPrimary
import com.example.notas.ui.theme.md_theme_light_tertiaryContainer
import com.example.notas.ui.theme.otherTheme
import com.example.proyectofinal_jma.sizeScreen.WindowInfo
import com.example.proyectofinal_jma.sizeScreen.rememberWindowInfo

class NotasSacreen: ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Notas()
                }
            }
        }
    }
}

@Composable
fun Notas() {
    var expandedTitle: Dp by remember{ mutableStateOf(224.dp) }
    var paddingTitle: Dp by remember{ mutableStateOf(70.dp) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                titulo = R.string.allNotas,
                backgrounColor = otherTheme().primary,
                color = md_theme_light_onPrimary,
                modifier = Modifier.sizeIn(maxHeight = 224.dp),
                expandedTitle,
                paddingTitle
            )
        },
    ){
        val notas =NotasModelRepository.notas
        val window = rememberWindowInfo()

        if(window.screenWindthInfo is WindowInfo.WindowType.Compact){
            NotasList(notas,modifier = Modifier.padding(top = 16.dp),contentPadding = it)
        }
        else if(window.screenWindthInfo is WindowInfo.WindowType.Medium){
            NotasList(notas,modifier = Modifier.padding(top = 16.dp),contentPadding = it, 3)
            expandedTitle = 100.dp
            paddingTitle = 0.dp
        }else if(window.screenWindthInfo is WindowInfo.WindowType.Expanded){
            NotasList(notas,modifier = Modifier.padding(top = 16.dp),contentPadding = it, 4)
            expandedTitle = 100.dp
            paddingTitle = 0.dp
        }

    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NotasList(
    notas: List<Nota>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    columns: Int = 2
) {
    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyVerticalGrid(columns = GridCells.Fixed(columns),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = contentPadding,
            modifier = Modifier.padding(8.dp)
        ) {
            itemsIndexed(notas) { index, nota ->
                val backColor = if(index%2==0){
                    MaterialTheme.colorScheme.background
                }else{ md_theme_light_tertiaryContainer}
                NotaListItem(
                    nota = nota,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = Spring.StiffnessVeryLow,
                                    dampingRatio = Spring.DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        ),
                    backColor = md_theme_light_tertiaryContainer
                )
            }
        }
    }
}

@Composable
fun NotaListItem(
    nota: Nota,
    modifier: Modifier = Modifier,
    backColor: Color,
    ) {
    Card(
        onClick = {},
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        //colors = CardDefaults.cardColors(containerColor = backColor),
        border = BorderStroke(width = 2.dp, color = com.example.notas.ui.theme.Notas),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = nota.titulo,
                    fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                    //color = MaterialTheme.colorScheme.onTertiaryContainer,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = nota.nota,
                    modifier = Modifier.padding(16.dp),
                    fontFamily = FontFamily(Font(R.font.caveat_regular, FontWeight.Bold)),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {
                /*Image(
                    painter = painterResource(0),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )*/
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NotasPreview() {
    NotasTheme {
        /*NotaListItem(
            Nota(
                titulo = "Nota 1",
                nota = "Hacer la tarea",
            ),
            backColor = md_theme_light_tertiaryContainer
        )*/
        Notas()
    }
}
