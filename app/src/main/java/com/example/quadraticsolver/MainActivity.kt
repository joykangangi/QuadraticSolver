package com.example.quadraticsolver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.quadraticsolver.ui.theme.QuadraticSolverTheme

class MainActivity : ComponentActivity() {
    private val viewModel: CalculatorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuadraticSolverTheme {
                MyApp()
            }
        }
    }

    @Composable
    fun MyApp() {

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
                calculatorViewModel = viewModel,
                onAction = viewModel::onAction
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