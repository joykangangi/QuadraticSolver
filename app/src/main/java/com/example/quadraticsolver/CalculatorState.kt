package com.example.quadraticsolver

data class CalculatorState(
    var numberA: String = "",
    var numberB: String = "",
    var numberC: String = "",
    var rootMessage: String = "",
    var answer1: String="",
    var answer2: String="",
    var isEnabled: Boolean = false
)

/*
sealed class CalculatorMessage(val message: String) {
    object Same: CalculatorMessage("The roots are real and equal")
    object Different: CalculatorMessage("The roots are real and different")
    object Complex: CalculatorMessage("The roots are imaginary")
}

 */