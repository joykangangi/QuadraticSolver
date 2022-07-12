package com.example.quadraticsolver

data class CalculatorFormState(
    val numberA: InputState = InputState(),
    val numberB: InputState = InputState(),
    val numberC: InputState = InputState(),
    val formValid: Boolean
)

/**InputState
manages all the state related to an input field
 */
data class InputState(
    var text: String = "",
    val isValid: Boolean = true,
    val type: Float = Float.MIN_VALUE,
    val errorMessage: String = ""
)