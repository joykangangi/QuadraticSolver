package com.example.quadraticsolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewModelScope
import com.example.quadraticsolver.ui.theme.QuadraticSolverTheme

class MainActivity : ComponentActivity() {
    private val viewmodel: CalculatorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadraticSolverTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                 MyApp(viewmodel)
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: CalculatorViewModel) {

    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text = stringResource(id = R.string.quad_title)
                )
            }
        }
    ) { paddingValues -> 
        CalculateForm(Modifier.padding(paddingValues), viewModel = viewModel)
        DisplayResults(message = viewModel.messText, answer = viewModel.answerText )
    }
}

@Composable
fun CalculateForm(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    var a by remember { mutableStateOf(viewModel.a) }
    var b by remember { mutableStateOf(viewModel.b) }
    var c by remember { mutableStateOf(viewModel.c) }

/*
    val validA = remember(a){
        ( a.isNotEmpty() || a.isDigitsOnly()) || a.all { it.digitToInt() != 0 }
    }
    */
    Surface(
        modifier
            .fillMaxWidth()
            .padding(4.dp),
        shape = RoundedCornerShape(CornerSize(3.dp)),
        border = BorderStroke(3.dp, MaterialTheme.colors.primary)
    ) {
        Column(
            modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.intro_text),
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(id = R.string.form),
                style = MaterialTheme.typography.h5,
                color = Color.Red
            )
            Text(
                text = stringResource(id = R.string.enter_text),
                fontSize = 22.sp
            )
            Spacer(modifier = modifier.height(8.dp))
            OutlinedTextField(
                value = a.toString(),
                onValueChange = { a = it.toInt() },
                isError = viewModel.validateInput(a = a.toFloat()),
                label = { Text(text = "Value of a") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = b.toString(),
                onValueChange = { b = it.toInt() },
                label = { Text(text = "Value of b") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = c.toString(),
                onValueChange = { c = it.toInt() },
                label = { Text(text = "Value of c") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = { viewModel.calculate(a,b,c) },
                    enabled = viewModel.validateInput(a = a.toFloat(), b = b.toFloat(), c = c.toFloat())
                ) {
                    Text(text = "CALCULATE")
                }
                Button(onClick = {
                    a = "".toInt()
                    b = "".toInt()
                    c = "".toInt()
                    //onResetClicked()
                }) {
                    Text(text = "RESET")
                }
            }
        }
    }
}

@Composable
fun DisplayResults(message: String, answer: String) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = message)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = answer )
    }
    
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuadraticSolverTheme {
        MyApp(CalculatorViewModel())
    }
}