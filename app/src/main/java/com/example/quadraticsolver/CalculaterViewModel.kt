package com.example.quadraticsolver

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt


//manage state and calculations here
class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorState())

        private set //similar to backing property

    fun onAction(action: CalculatorAction) {
        when (action) {
            CalculatorAction.Calculate -> doCalculation()
            CalculatorAction.Reset -> state = CalculatorState() //initial state of the form
           // is CalculatorAction.Number -> enterNumber(action.number)
        }
    }
/*
    private fun enterNumber(number: Float) {
        this.state.numberA = number.toString()
    }

 */

    private fun doCalculation() {
        val aNumber = state.numberA.toFloatOrNull()
        val bNumber = state.numberB.toFloatOrNull()
        val cNumber = state.numberC.toFloatOrNull()

        val root1: Float
        val root2: Float
        val message: String

        if (aNumber != null && bNumber != null && cNumber != null) {
            val d = (bNumber * bNumber) - (4 * aNumber * cNumber) //discriminant

            when {
                d > 0 -> {
                    message = "Roots are real and different"
                    root1 = (-bNumber + sqrt(d)) / (2 * aNumber)
                    root2 = (-bNumber - sqrt(d)) / (2 * aNumber)
                }
                d == 0.0F -> {
                    message = "Roots are real and equal"
                    root1 = (-bNumber + sqrt(d)) / (2 * aNumber)
                    root2 = (-bNumber - sqrt(d)) / (2 * aNumber)
                }
                else -> {
                    message = "Roots are imaginary/complex"
                    root1 = (-bNumber + sqrt(d)) / (2 * aNumber)
                    root2 = (-bNumber - sqrt(d)) / (2 * aNumber)
                }
            }
            state = state.copy(
                numberA = aNumber.toString(),
                numberB = bNumber.toString(),
                numberC = cNumber.toString(),
                rootMessage = message,
                answer1 = root1.toString().format("%.4f"),
                answer2 = root2.toString().format("%.4f"),
                isEnabled = true
            )
        }
    }
}



/*

class CalculatorViewModel : ViewModel() {
    private val _a = 0
    val a get() = _a

    private val _b = 0
    val b get() = _b

    private val _c = 0
    val c get() = _c

    private var _ansText = ""
    val answerText get() = _ansText

    private var _messText = ""
    val messText get() = _messText



    fun validateInput(a: Float, b: Float = 0.0F, c: Float = 0.0F): Boolean {
        return (a != 0.0F)
    }

    fun calculate(a: Int, b: Int, c: Int) {
        val root1: Float
        val root2: Float
        val real: Float
        val imaginary: Float
        val d = (b * b) - (4 * a * c).toFloat()

        when {
            d > 0 -> {
                root1 = (-b + sqrt(d)) / (2 * a)
                root2 = (-b - sqrt(d)) / (2 * a)
                _messText = "Roots are real and different"
                _ansText = String.format("$_messText %.1f, %.1f", root1, root2)
            }
            d == 0.0F -> {
                root1 = (-b / (2 * a)).toFloat()
                root2 = root1
                _messText = "Roots are real and equal"
                _ansText = String.format("$_messText %.1f, %.1f", root1, root2)
            }
            else -> {
                real = -b / (2 * a).toFloat()
                imaginary = sqrt(-d) / (2 * a)
                _messText = "Roots are imaginary"
                _ansText = String.format("$_messText %.1f, %.1f", real , imaginary)
            }
        }
    }
    }*/