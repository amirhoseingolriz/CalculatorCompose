package ir.golriz.amirhosein.calculatorcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ir.golriz.amirhosein.calculatorcompose.ui.theme.CalculatorComposeTheme
import ir.golriz.amirhosein.calculatorcompose.ui.theme.OrangeColor

lateinit var textNumbers: MutableState<String>

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorComposeTheme {
                CalculatorScreen()
            }
        }


    }

}

@Composable
fun CalculatorScreen() {

    val isResult = remember { mutableStateOf(false) }
    textNumbers = remember { mutableStateOf("0") }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {


        //TopDesign
        Column(
            Modifier
                .fillMaxWidth()
                .weight(0.45F),
            verticalArrangement = Arrangement.Bottom
        ) {

            TopDesign(isResult)

        }

        Spacer(modifier = Modifier.weight(0.025F))

        //Line
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.LightGray)
                .padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.weight(0.025F))


        //BottomDesign
        Column(
            Modifier
                .fillMaxWidth()
                .weight(0.5F),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BottomDesign()

        }

    }

}

@Composable
fun TopDesign(isResult: MutableState<Boolean>) {
    if (isResult.value) {
        TextCalculate(textNumbers.value, MaterialTheme.typography.h3, Color.Black)
        TextCalculate("0", MaterialTheme.typography.h6, Color.DarkGray)
    } else {
        TextCalculate(textNumbers.value, MaterialTheme.typography.h5, Color.DarkGray)
        TextCalculate("0", MaterialTheme.typography.h4, Color.Black)
    }

}

@Composable
fun TextCalculate(text: String, textStyle: TextStyle, color: Color) {

    Text(
        text = text,
        style = textStyle,
        color = color,
        maxLines = 1,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
    )

}

@Composable
fun BottomDesign() {

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        OperatorDesign(icon = R.drawable.ic_backspace, size = 40.dp, detailsIcon = "Remove") {
            if (textNumbers.value.isNotEmpty()) {
                textNumbers.value =
                    textNumbers.value.substring(0, textNumbers.value.lastIndex)
            }
        }

        OperatorDesign(
            icon = R.drawable.ic_left_parentheses,
            size = 28.dp,
            detailsIcon = "ParenthesesLeft"
        ) {
            textNumbers.value += "("
        }

        OperatorDesign(
            icon = R.drawable.ic_right_parentheses,
            size = 28.dp,
            detailsIcon = "ParenthesesRight"
        ) {
            textNumbers.value += ")"
        }

        OperatorDesign(icon = R.drawable.ic_divide, size = 40.dp, detailsIcon = "Divide") {
            if (
                textNumbers.value.last() == '*' ||
                textNumbers.value.last() == '+' ||
                textNumbers.value.last() == '-'
            ) {
                textNumbers.value =
                    textNumbers.value.substring(0, textNumbers.value.lastIndex)
                textNumbers.value += "/"
            } else if (textNumbers.value.last() != '/') {
                textNumbers.value += "/"
            }
        }

    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        NumberDesign(number = "7") {
            textNumbers.value += "7"
        }
        NumberDesign(number = "8") {
            textNumbers.value += "8"
        }
        NumberDesign(number = "9") {
            textNumbers.value += "9"
        }
        OperatorDesign(
            icon = R.drawable.ic_multiply,
            size = 30.dp,
            detailsIcon = "Multiplication"
        ) {
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
        }

    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        NumberDesign(number = "4") {
            textNumbers.value += "4"
        }
        NumberDesign(number = "5") {
            textNumbers.value += "5"
        }
        NumberDesign(number = "6") {
            textNumbers.value += "6"
        }
        OperatorDesign(icon = R.drawable.ic_minus, size = 36.dp, detailsIcon = "Minus") {
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
        }

    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        NumberDesign(number = "1") {
            textNumbers.value += "1"
        }
        NumberDesign(number = "2") {
            textNumbers.value += "2"
        }
        NumberDesign(number = "3") {
            textNumbers.value += "3"
        }
        OperatorDesign(icon = R.drawable.ic_sum, size = 40.dp, detailsIcon = "Sum") {

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
        }

    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        OperatorDesign(icon = R.drawable.ic_clear, size = 30.dp, detailsIcon = "Clear") {
            textNumbers.value = "0"
        }

        NumberDesign(number = "0") {
            textNumbers.value += "0"
        }

        NumberDesign(number = ".") {

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
        }

        EqualIcon {

        }

    }

}

@Composable
fun OperatorDesign(icon: Int, size: Dp, detailsIcon: String, OnOperatorClicked: () -> Unit) {

    IconButton(
        onClick = OnOperatorClicked,
        modifier = Modifier
            .size(64.dp)
    ) {

        Icon(
            painter = painterResource(id = icon),
            contentDescription = detailsIcon,
            tint = OrangeColor,
            modifier = Modifier.size(size)
        )

    }

}

@Composable
fun NumberDesign(number: String, OnNumberClicked: () -> Unit) {

    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable {
                if (textNumbers.value.length == 1 && textNumbers.value == "0") {
                    textNumbers.value = ""
                }
                OnNumberClicked.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = number,
            style = MaterialTheme.typography.h4,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }


}

@Composable
fun EqualIcon(OnResultClicked: () -> Unit) {

    IconButton(
        onClick = OnResultClicked,
        modifier = Modifier
            .size(64.dp)
            .background(OrangeColor, CircleShape)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_equal),
            contentDescription = "Equal",
            tint = Color.White,
            modifier = Modifier.size(40.dp)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CalculatorComposeTheme {
        CalculatorScreen()
    }
}

