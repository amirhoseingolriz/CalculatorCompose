package ir.golriz.amirhosein.calculatorcompose

sealed class CalculatorAction {

    data class Number(val number: Int) : CalculatorAction()
    object Delete : CalculatorAction()
    object Clear : CalculatorAction()
    object Decimal : CalculatorAction()
    object Equal : CalculatorAction()
    data class Operation(val operation: CalculatorOperation) : CalculatorAction()

}

sealed class CalculatorOperation(val symbol: String) {

    object Sum : CalculatorOperation("+")
    object Minus : CalculatorOperation("-")
    object Multiply : CalculatorOperation("*")
    object Divide : CalculatorOperation("/")
    object ParenthesesLeft : CalculatorOperation("(")
    object ParenthesesRight : CalculatorOperation(")")

}