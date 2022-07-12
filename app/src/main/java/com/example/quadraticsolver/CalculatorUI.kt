package com.example.quadraticsolver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit,// hoisted to main Activity, call the viewmodel w/action
    calculatorViewModel: CalculatorViewModel

) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        InputForm(modifier, calculatorViewModel, onAction)
        DisplayAns(calculatorViewModel = calculatorViewModel, modifier = modifier )

    }
}

@Composable
fun InputForm(
    modifier: Modifier,
    calculatorViewModel: CalculatorViewModel,
    onAction: (CalculatorAction) -> Unit
) {
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
            val calculatorFormState = calculatorViewModel.state
            val numA = remember {
                mutableStateOf(calculatorFormState.numberA.text)
            }
            val numB = remember {
                mutableStateOf(calculatorFormState.numberB.text)
            }
            val numC = remember {
                mutableStateOf(calculatorFormState.numberC.text)
            }


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
                value = numA.value,
                onValueChange = { numA.value = it },
                label = { Text("Value of a") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            if (numA.value.all { it.code.toFloat() == 0.0F })
                Text(text = "Value of a can't be 0 to be quadratic", color = Color.Red)

            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = numB.value,
                onValueChange = { numB.value = it },
                label = { Text("Value of b") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = numC.value,
                onValueChange = { numC.value = it },
                label = { Text("Value of c") },
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
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                    },
                    enabled = numA.value.isNotEmpty() || numB.value.isNotEmpty() || numC.value.isNotEmpty()
                ) {
                    Text(text = "CALCULATE")
                }
                Button(
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                    },
                    enabled = numA.value.isNotEmpty() || numB.value.isNotEmpty() || numC.value.isNotEmpty()
                ) {
                    Text(text = "RESET")
                }
            }
        }
    }
}

//Display results
@Composable
fun DisplayAns(calculatorViewModel: CalculatorViewModel, modifier: Modifier) {
    Spacer(modifier = Modifier.height(4.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Ans: ${calculatorViewModel.message}")
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = calculatorViewModel.root1,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            fontSize = 80.sp
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = calculatorViewModel.root2,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            fontSize = 80.sp
        )
    }
}
