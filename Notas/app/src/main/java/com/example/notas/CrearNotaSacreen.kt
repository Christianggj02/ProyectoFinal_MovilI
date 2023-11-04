@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.notas

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notas.ui.theme.PrincipalTheme
import com.example.notas.ui.theme.md_theme_light_onPrimary
import com.example.notas.viewModels.NotaState
import com.example.notas.viewModels.NotasViewModel
import com.example.notas.viewModels.NotasViewModelProvider
import com.example.proyectofinal_jma.sizeScreen.WindowInfo
import com.example.proyectofinal_jma.sizeScreen.rememberWindowInfo
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@Composable
fun CrearNota(
    navController: NavHostController = rememberNavController(),
    vm: NotasViewModel = viewModel(factory = NotasViewModelProvider.factory)
) {
    var expandedTitle: Dp by remember{ mutableStateOf(224.dp) }
    var paddingTitulo: Dp by remember{ mutableStateOf(70.dp) }
    var paddingTitle: Dp by remember{ mutableStateOf(155.dp) }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                titulo = R.string.CrearNota,
                backgrounColor = MaterialTheme.colorScheme.primary,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.sizeIn(maxHeight = 224.dp),
                expandedTitle,
                paddingTitulo
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingTitle),
                value = vm.uiState.titulo,
                label = {
                    Text(
                        text = stringResource(id = R.string.tituloNota),
                        fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                        fontSize = 24.sp,
                    )
                },
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.caveat_bold)),
                    fontSize = 24.sp
                ),
                singleLine = true,
                onValueChange = { vm.updateUiState(vm.uiState.copy(titulo = it)) }
            )
        },
        bottomBar = {
            Row (horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            vm.saveNote()
                            navController.navigate("inicio")
                        }
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.guardarNota),
                        fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    ){
        val window = rememberWindowInfo()

        if(window.screenWindthInfo is WindowInfo.WindowType.Medium){
            expandedTitle = 100.dp
            paddingTitulo = 5.dp
            paddingTitle = 24.dp
        }else if(window.screenWindthInfo is WindowInfo.WindowType.Expanded){
            expandedTitle = 100.dp
            paddingTitulo = 5.dp
            paddingTitle = 24.dp
        }

        nota(
            modifier = Modifier.padding(top = expandedTitle),
            contentPadding = it,
            notaS = vm.uiState,
            updateUiState = vm::updateUiState
        )
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun nota(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    notaS: NotaState,
    updateUiState: (NotaState) -> Unit
){
    Column(modifier = modifier
        .verticalScroll(state = ScrollState(0)),
    ){
        Card(modifier = Modifier
            .padding(horizontal = 16.dp)
        ) {

        }

        Card(modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = notaS.nota,
                label = {
                    Text(
                        text = stringResource(id = R.string.nota),
                        fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                        fontSize = 24.sp,
                    )
                },
                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.caveat_regular)), fontSize = 24.sp),
                onValueChange = {updateUiState(notaS.copy(nota = it))}
            )
            Row(){
                AssistChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Añadir Imagen", fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),)},
                    leadingIcon = {
                        Icon(Icons.Rounded.Add, contentDescription = "addImage")
                    },
                    modifier = Modifier.padding(start = 16.dp)
                )
                AssistChip(
                    onClick = { /*TODO*/ },
                    label = { Text(text = "Añadir Audio",fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),)},
                    leadingIcon = {
                        Icon(Icons.Rounded.PlayArrow, contentDescription = "addAudio")
                    },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Card (modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .padding(bottom = 100.dp)
        ) {
            Column(
                modifier = Modifier
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioNoBouncy,
                            stiffness = Spring.StiffnessMedium
                        )
                    )
            ){
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Tarea",
                        fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                        fontSize = 24.sp,
                        modifier = Modifier
                            .fillMaxWidth(.6f)
                            .padding(start = 16.dp)
                    )
                    Switch(
                        modifier = Modifier
                            .fillMaxWidth(),
                        checked = notaS.isTask,
                        onCheckedChange = {updateUiState(notaS.copy(isTask = it))}
                    )
                }
                if (notaS.isTask){
                    Tarea(notaS = notaS, updateUiState = updateUiState)
                }
            }
        }
    }
}

@Composable
fun Tarea(
    notaS: NotaState,
    updateUiState: (NotaState) -> Unit
){

    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    // Initializing a Calendar
    val mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mHour = mCalendar[Calendar.HOUR_OF_DAY]
    val mMinute = mCalendar[Calendar.MINUTE]

    mCalendar.time = Date()

    // Declaring a string value to
    // store date in string format
    //val mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            //mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
            updateUiState(notaS.copy(fecha = "$mDayOfMonth/${mMonth+1}/$mYear"))
        }, mYear, mMonth, mDay
    )

    // Value for storing time as a string
    //val mTime = remember { mutableStateOf("") }

    // Creating a TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            //mTime.value = "$mHour:$mMinute"
            updateUiState(notaS.copy(hora = "$mHour:$mMinute"))
        }, mHour, mMinute, false
    )

    Row {
        AssistChip(
            onClick = { mDatePickerDialog.show() },
            label = { Text(text = "Definir fecha: ${notaS.fecha}", fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),)},
            leadingIcon = {
                Icon(Icons.Rounded.DateRange, contentDescription = "addDate")
            },
            modifier = Modifier.padding(start = 16.dp)
        )
        AssistChip(
            onClick = { mTimePickerDialog.show() },
            label = { Text(text = "Definir hora: ${notaS.hora}", fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),)},
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Composable
fun TitleBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.CrearNota),
                fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                color = md_theme_light_onPrimary,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.padding(top = 70.dp)
            )
        },
        modifier = modifier
            .fillMaxHeight(),
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),

    )
}

@Preview(showBackground = true)
@Composable
fun CrearNotaPreview() {
    PrincipalTheme {
        CrearNota()
    }
}
