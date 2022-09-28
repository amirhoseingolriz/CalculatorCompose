package ir.golriz.amirhosein.calculatorcompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculatorViewModel : ViewModel() {

    val textNumbersFirst = mutableStateOf("0")
    val textNumberSecond = mutableStateOf("0")
    val isShowResult = mutableStateOf(false)


    fun onAction(action: CalculatorAction) {


        /*
        Divide
         if (
                textNumbers.value.last() == '*' ||
                textNumbers.value.last() == '+' ||
                textNumbers.value.last() == '-'
            ) {
                textNumbers.value =
                    textNumbers.value.substring(0, textNumbers.value.lastIndex)
                textNumbers.value += ""
            } else if (textNumbers.value.last() != '÷') {
                textNumbers.value += "×"
            }


            Multiply
            if (
                textNumbers.value.last() == '+' ||
                textNumbers.value.last() == '/' ||
                textNumbers.value.last() == '-'
            ) {
                textNumbers.value =
                    textNumbers.value.substring(0, textNumbers.value.lastIndex)
                textNumbers.value += "*"
            } else if (textNumbers.value.last() != '*') {
                textNumbers.value += "*"
            }


            Minus
            if (
                textNumbers.value.last() == '*' ||
                textNumbers.value.last() == '/' ||
                textNumbers.value.last() == '+'
            ) {
                textNumbers.value =
                    textNumbers.value.substring(0, textNumbers.value.lastIndex)
                textNumbers.value += "-"
            } else if (textNumbers.value.last() != '-') {
                textNumbers.value += "-"
            }

            Sum
                if (
                textNumbers.value.last() == '*' ||
                textNumbers.value.last() == '/' ||
                textNumbers.value.last() == '-'
            ) {
                textNumbers.value =
                    textNumbers.value.substring(0, textNumbers.value.lastIndex)
                textNumbers.value += "+"
            } else if (textNumbers.value.last() != '+') {
                textNumbers.value += "+"
            }



            Dot

            if (textNumbers.value.isNotEmpty()) {
                if (
                    !textNumbers.value.contains(".") &&
                    textNumbers.value.last() != '+' &&
                    textNumbers.value.last() != '-' &&
                    textNumbers.value.last() != '*' &&
                    textNumbers.value.last() != '/' &&
                    textNumbers.value.last() != '(' &&
                    textNumbers.value.last() != ')'
                ) {
                    Log.e("Adas", textNumbers.value.last().toString())
                    if (textNumbers.value == "0") {
                        textNumbers.value += "0."
                    } else {
                        textNumbers.value += "."
                    }

                }
            } else {
                textNumbers.value += "0."
            }
         */

    }

}