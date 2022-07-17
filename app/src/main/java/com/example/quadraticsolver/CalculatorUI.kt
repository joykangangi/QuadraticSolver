package com.example.quadraticsolver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CalculateForm(
    modifier: Modifier,
    root1: String,
    root2: String,
    message: String,
    onCalculate: (String, String, String) -> Unit
) {
    val scrollState = rememberScrollState()
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Surface(
            modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(CornerSize(3.dp)),
            border = BorderStroke(3.dp, MaterialTheme.colors.primary)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var numA by remember { mutableStateOf("") }
                var numB by remember { mutableStateOf("") }
                var numC by remember { mutableStateOf("") }
                var displayAnswer by remember {
                    mutableStateOf(false)
                }

                val onAChanged = { a: String -> numA = a }
                val onBChanged = { b: String -> numB = b }
                val onCChanged = { c: String -> numC = c }

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

                InputForm(
                    numA = numA,
                    onAChanged = onAChanged,
                    numB = numB,
                    onBChanged = onBChanged,
                    numC = numC,
                    onCChanged = onCChanged
                )

                if (displayAnswer) {
                    DisplayAns(
                        message = message,
                        rootOne = root1,
                        rootTwo = root2
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { onCalculate(numA, numB, numC)
                                  displayAnswer = !displayAnswer},
                        enabled = numA.isNotEmpty() || numB.isNotEmpty() || numC.isNotEmpty()
                    ) {
                        Text(text = "CALCULATE")
                    }

                    Button(
                        //initial state of the form
                        onClick = {
                            numA = ""
                            numB = ""
                            numC = ""
                            displayAnswer = !displayAnswer
                        },
                        enabled = numA.isNotEmpty() || numB.isNotEmpty() || numC.isNotEmpty()
                    ) {
                        Text(text = "RESET")
                    }
                }
            }
        }
    }
}
@Composable
fun InputForm(
    modifier: Modifier = Modifier,
    numA: String,
    onAChanged: (String) -> Unit,
    numB: String,
    onBChanged: (String) -> Unit,
    numC: String,
    onCChanged: (String) -> Unit
) {

    OutlinedTextField(
        value = numA,
        onValueChange = { onAChanged(it) },
        label = { Text("Value of a") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true
    )
    if (numA.all { it.digitToInt().toFloat() == 0.0F })
        Text(text = "Value of a can't be 0 to be quadratic", color = Color.Red)

    Spacer(modifier = modifier.height(10.dp))
    OutlinedTextField(
        value = numB,
        onValueChange = { onBChanged(it) },
        label = { Text("Value of b") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        singleLine = true
    )
    Spacer(modifier = modifier.height(10.dp))
    OutlinedTextField(
        value = numC,
        onValueChange = { onCChanged(it) },
        label = { Text("Value of c") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        singleLine = true
    )
}

//Display results
@Composable
fun DisplayAns(modifier: Modifier = Modifier, message: String, rootOne: String, rootTwo: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Nature of roots: $message", modifier
            .fillMaxWidth()
            .padding(4.dp))
        Text(
            text = "Root One: $rootOne",
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Root Two: $rootTwo",
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp),
            fontWeight = FontWeight.Bold
        )
    }
}
