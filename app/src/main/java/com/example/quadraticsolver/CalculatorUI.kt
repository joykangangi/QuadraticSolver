package com.example.quadraticsolver

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
    onAction: (CalculatorAction) -> Unit,
    viewModel: CalculatorViewModel
    // hoisted to main Activity, call the viewmodel w/action
)
{
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
                value = viewModel.state.numberA,
                onValueChange = { viewModel.state.numberA = it } ,
                isError = (viewModel.state.numberA.all { it.digitToInt() == 0 }) ,
                label = { Text(text = "Value of a") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            if (viewModel.state.numberA.all { it.digitToInt() == 0 })
                Text(
                    text = "a cannot be 0 to be a quadratic equation",
                    color = Color.Red
                )
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = viewModel.state.numberB,
                onValueChange = { viewModel.state.numberB = it },
                label = { Text(text = "Value of b") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )
            Spacer(modifier = modifier.height(10.dp))
            OutlinedTextField(
                value = viewModel.state.numberC,
                onValueChange = { viewModel.state.numberC = it },
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
                    onClick = {
                        onAction(CalculatorAction.Calculate)
                              },
                    enabled = viewModel.state.isEnabled
                ) {
                    Text(text = "CALCULATE")
                }
                Button(
                    onClick = {
                        onAction(CalculatorAction.Reset)
                    },
                    enabled = viewModel.state.isEnabled) {
                    Text(text = "RESET")
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(4.dp))
    //Display results
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(text = viewModel.state.rootMessage)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = viewModel.state.answer1,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            fontSize = 80.sp )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = viewModel.state.answer2,
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            fontSize = 80.sp )
    }
}


