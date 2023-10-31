@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.notas.crearNotaScreen

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.AssistChip
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notas.R
import com.example.notas.ui.theme.PrincipalTheme
import com.example.notas.ui.theme.md_theme_light_onPrimary
import java.util.Calendar
import java.util.Date

@Composable
fun CrearNota(vm: NotasViewModel = viewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TitleBar(modifier = Modifier.sizeIn(maxHeight = 224.dp))
        },
    ){
        nota(
            modifier = Modifier.padding(top = 240.dp),
            contentPadding = it,
            nota = vm.nota,
            onNotaChange = {vm.onNotaChange(it)},
            task = vm.isTask,
            onIsTaskChange = {vm.onIsTaskCange(it)}
        )
    }
}

@Composable
fun nota(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    nota: String,
    onNotaChange: (String) -> Unit,
    task: Boolean,
    onIsTaskChange: (Boolean) -> Unit,
){
    Column(modifier = modifier
        .verticalScroll(state = ScrollState(0)),
    ){
        Card(modifier = Modifier
            .padding(horizontal = 16.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = nota ,
                label = {Text(
                    text = stringResource(id = R.string.nota),
                    fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),
                    fontSize = 24.sp,
                    /*modifier = Modifier
                        .fillMaxWidth(.6f)
                        .padding(start = 16.dp)*/
                )},
                textStyle = TextStyle(fontFamily = FontFamily(Font(R.font.caveat_bold)), fontSize = 24.sp),
                onValueChange = {onNotaChange(it)}
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
                        checked = task,
                        onCheckedChange = { onIsTaskChange(it)}
                    )
                }
                if (task){
                    Tarea()
                }
            }
        }
    }
}

@Composable
fun Tarea(){

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
    val mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        }, mYear, mMonth, mDay
    )

    // Value for storing time as a string
    val mTime = remember { mutableStateOf("") }

    // Creating a TimePicker dialog
    val mTimePickerDialog = TimePickerDialog(
        mContext,
        {_, mHour : Int, mMinute: Int ->
            mTime.value = "$mHour:$mMinute"
        }, mHour, mMinute, false
    )

    Row {
        AssistChip(
            onClick = { mDatePickerDialog.show() },
            label = { Text(text = "Definir fecha: ${mDate.value}", fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),)},
            leadingIcon = {
                Icon(Icons.Rounded.DateRange, contentDescription = "addDate")
            },
            modifier = Modifier.padding(start = 16.dp)
        )
        AssistChip(
            onClick = { mTimePickerDialog.show() },
            label = { Text(text = "Definir hora: ${mTime.value}", fontFamily = FontFamily(Font(R.font.caveat_bold, FontWeight.Bold)),)},
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun CrearNotaPreview() {
    PrincipalTheme {
        CrearNota(NotasViewModel())
    }
}
