package com.example.quadraticsolver

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt


//manage state and calculations here
class CalculatorViewModel: ViewModel() {
    var state by mutableStateOf(CalculatorFormState(formValid = false))
        private set //similar to backing property
    private var _root1: String = ""
    val root1 get() = _root1

    private var _root2: String = ""
    val root2 get() = _root2

    private var _message: String = ""
    val message get() = _message


    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.NumberA -> {
                state = state.copy(
                    numberA = state.numberA.copy(
                        text = action.aNumber.toString()
                    )
                )
            }
            is CalculatorAction.NumberB -> {
                state = state.copy(
                    numberB = state.numberB.copy(
                        text = action.bNumber.toString()
                    )
                )
            }
            is CalculatorAction.NumberC -> {
                state = state.copy(
                    numberC = state.numberC.copy(
                        text = action.cNumber.toString()
                    )
                )
            }
            CalculatorAction.Calculate -> doCalculation(
                state.numberA.text.toFloat(),
                state.numberB.text.toFloat(),
                state.numberC.text.toFloat()
            )
            CalculatorAction.Reset -> {
                state.numberA.text = ""
                state.numberB.text = ""
                state.numberC.text = ""
            } //initial state of the form
        }
    }

   private fun doCalculation(aNumber: Float, bNumber: Float, cNumber: Float) {
        val d = (bNumber * bNumber) - (4 * aNumber * cNumber) //discriminant

        when {
            d > 0 -> {
                _message = "Roots are real and different"
                _root1 = ((-bNumber + sqrt(d)) / (2 * aNumber)).toString()
                _root2 = ((-bNumber - sqrt(d)) / (2 * aNumber)).toString()
            }
            d == 0.0F -> {
                _message = "Roots are real and equal"
                _root1 = ((-bNumber + sqrt(d)) / (2 * aNumber)).toString()
                _root2 = ((-bNumber - sqrt(d)) / (2 * aNumber)).toString()
            }
            else -> {
                _message = "Roots are imaginary/complex"
                _root1 = ((-bNumber + sqrt(d)) / (2 * aNumber)).toString()
                _root2 = ((-bNumber - sqrt(d)) / (2 * aNumber)).toString()
            }
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