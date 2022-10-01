package ir.golriz.amirhosein.calculatorcompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.RoundingMode

class CalculatorViewModel : ViewModel() {

    val textNumbersFirst = mutableStateOf("0")
    val textNumberSecond = mutableStateOf("0")
    val isShowResult = mutableStateOf(false)
    val errorExpress = mutableStateOf(Pair(false, ""))


    fun onAction(action: CalculatorAction) {


        when (action) {

            is CalculatorAction.Number -> numberClicked(action.number)
            is CalculatorAction.Operation -> operationClicked(action.operation)

            is CalculatorAction.Decimal -> decimalClicked()
            is CalculatorAction.Delete -> deleteCalculate()
            is CalculatorAction.Clear -> clearCalculate()

            is CalculatorAction.Equal -> equalCalculate()

        }

    }

    private fun numberClicked(number: Int) {

        if (isShowResult.value) {
            isShowResult.value = false
            textNumbersFirst.value = ""
            textNumberSecond.value = "0"
        }

        if (textNumbersFirst.value != "0") {
            textNumbersFirst.value += number.toString()
        } else {
            textNumbersFirst.value = number.toString()
        }

    }

    private fun operationClicked(operation: CalculatorOperation) {

        if (isShowResult.value) {
            isShowResult.value = false
            if (!errorExpress.value.first) {
                textNumbersFirst.value = textNumberSecond.value
            } else {
                textNumbersFirst.value = "0"
            }
            textNumberSecond.value = "0"
        }
        if ((textNumbersFirst.value.last() == '+' ||
                    textNumbersFirst.value.last() == '-' ||
                    textNumbersFirst.value.last() == '*' ||
                    textNumbersFirst.value.last() == '/') &&
            operation != CalculatorOperation.ParenthesesLeft
        ) {
            textNumbersFirst.value = textNumbersFirst.value.dropLast(1) + operation.symbol
        } else {
            textNumbersFirst.value += operation.symbol
        }

    }

    private fun decimalClicked() {

        if (isShowResult.value) {
            isShowResult.value = false
            textNumbersFirst.value = "0"
            textNumberSecond.value = "0"
        }

        if (textNumbersFirst.value != "" || textNumbersFirst.value != "0") {
            if (!textNumbersFirst.value.contains(".") &&
                textNumbersFirst.value.last() != '+' &&
                textNumbersFirst.value.last() != '-' &&
                textNumbersFirst.value.last() != '*' &&
                textNumbersFirst.value.last() != '/' &&
                textNumbersFirst.value.last() != '(' &&
                textNumbersFirst.value.last() != ')'
            ) {
                textNumbersFirst.value += "."
            }
        } else {
            textNumbersFirst.value = "0."
        }

    }

    private fun deleteCalculate() {

        textNumbersFirst.value = textNumbersFirst.value.dropLast(1)

    }

    private fun clearCalculate() {

        textNumbersFirst.value = "0"
        textNumberSecond.value = "0"
        isShowResult.value = false

    }

    private fun equalCalculate() {

        isShowResult.value = true

        try {
            errorExpress.value = Pair(false, "")
            val express = ExpressionBuilder(textNumbersFirst.value).build()
            val result = express.evaluate()

            if (result == result.toLong().toDouble()) {
                textNumberSecond.value = result.toLong().toString()
            } else {
                textNumberSecond.value =
                    result.toBigDecimal().setScale(4, RoundingMode.DOWN).toString()
            }

        } catch (error: Exception) {
            errorExpress.value = Pair(true, error.toString())
        }

    }


}