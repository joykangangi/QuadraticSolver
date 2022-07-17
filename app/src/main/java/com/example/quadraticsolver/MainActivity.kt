package com.example.quadraticsolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quadraticsolver.ui.theme.QuadraticSolverTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadraticSolverTheme {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp(calculatorViewModel: CalculatorViewModel = viewModel()) {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(
                        text = stringResource(id = R.string.quad_title)
                    )
                }
            }
        ) { paddingValues ->
            CalculateForm(
                modifier = Modifier.padding(paddingValues),
                root1 = calculatorViewModel.root1,
                root2 = calculatorViewModel.root2,
                message = calculatorViewModel.message,
                onCalculate = { a: String, b: String, c: String ->
                    calculatorViewModel.doCalculation(
                        a = a,
                        b = b,
                        c = c
                    )
                }
            )
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        QuadraticSolverTheme {
            MyApp()
        }
    }
}