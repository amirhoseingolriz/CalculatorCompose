package ir.golriz.amirhosein.calculatorcompose

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import java.math.RoundingMode

class CalculatorViewModel : ViewModel() {

    val textNumbersFirst = mutableStateOf("0")
    val textNumberSecond = mutableStateOf("0")
    val isShowResult = mutableStateOf(false)

    fun liveExpress() {
        try {
            expressFun()
        } catch (error: Exception) {
            Log.e("ErrorLiveExpress", error.toString())
        }
    }

    fun onAction(action: CalculatorAction) {

        //Actions
        when (action) {

            is CalculatorAction.Number -> numberClicked(action.number)
            is CalculatorAction.Operation -> operationClicked(action.operation)

            is CalculatorAction.Decimal -> decimalClicked()
            is CalculatorAction.Delete -> deleteCalculate()
            is CalculatorAction.Clear -> clearCalculate()

            is CalculatorAction.Equal -> equalCalculate()

        }

    }

    //Click Number
    private fun numberClicked(number: Int) {

        if (isShowResult.value) {
            textNumbersFirst.value = ""
            textNumberSecond.value = "0"
            isShowResult.value = false
        }

        if (textNumbersFirst.value != "0") {
            textNumbersFirst.value += number.toString()
        } else {
            textNumbersFirst.value = number.toString()
        }

    }

    //Click Operation
    private fun operationClicked(operation: CalculatorOperation) {

        if (isShowResult.value) {
            if (textNumberSecond.value != ERROR_TEXT) {
                textNumbersFirst.value = textNumberSecond.value
            } else {
                textNumbersFirst.value = "0"
            }
            textNumberSecond.value = "0"
            isShowResult.value = false
        }
        //Check Duplicate Operation
        if ((textNumbersFirst.value.last() == '+' ||
                    textNumbersFirst.value.last() == '-' ||
                    textNumbersFirst.value.last() == '*' ||
                    textNumbersFirst.value.last() == '/') &&
            operation != CalculatorOperation.ParenthesesLeft
        ) {
            //Replace Operation
            textNumbersFirst.value = textNumbersFirst.value.dropLast(1) + operation.symbol
        } else {
            //Add Operation
            textNumbersFirst.value += operation.symbol
        }

    }

    //Click Decimal(.)
    private fun decimalClicked() {

        if (isShowResult.value) {
            isShowResult.value = false
            textNumbersFirst.value = "0"
            textNumberSecond.value = "0"
        }

        //Check only added once
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

        if (textNumberSecond.value != ERROR_TEXT) {
            //Delete last character
            textNumbersFirst.value = textNumbersFirst.value.dropLast(1)
        } else {
            textNumbersFirst.value = "0"
            textNumberSecond.value = "0"
        }

    }

    //Delete all Text
    private fun clearCalculate() {

        textNumbersFirst.value = "0"
        textNumberSecond.value = "0"
        isShowResult.value = false

    }

    //Click Equal=>Result
    private fun equalCalculate() {

        isShowResult.value = true

        try {
            expressFun()
        } catch (error: Exception) {
            textNumberSecond.value = ERROR_TEXT
        }

    }


    private fun expressFun() {
        val express = ExpressionBuilder(textNumbersFirst.value).build()
        val result = express.evaluate()

        if (result == result.toLong().toDouble()) {
            textNumberSecond.value = result.toLong().toString()
        } else {
            if (result.toBigDecimal().setScale(4, RoundingMode.DOWN).toLong() == 0L) {
                textNumberSecond.value = result.toString()
            } else {
                textNumberSecond.value =
                    result.toBigDecimal().setScale(4, RoundingMode.DOWN).toString()
            }

        }
    }

}