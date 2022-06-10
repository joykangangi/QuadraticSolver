package com.example.quadraticsolver

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import kotlin.math.sqrt


//manage state and calculations here
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
}