package com.example.quadraticsolver

/**
 * What actions users can perform on a screen, i.e. events
 */

sealed interface CalculatorAction {
    //send number to the view Model
    //data class Number(val number: Float): CalculatorAction
    object Reset: CalculatorAction
    object Calculate: CalculatorAction
}