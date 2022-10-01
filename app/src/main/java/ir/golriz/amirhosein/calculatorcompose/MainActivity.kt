package ir.golriz.amirhosein.calculatorcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import ir.golriz.amirhosein.calculatorcompose.ui.theme.CalculatorComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        setContent {
            CalculatorComposeTheme {
                //ChangeStatusBar Color
                val systemUiController = rememberSystemUiController()
                systemUiController.setStatusBarColor(color = MaterialTheme.colors.primary)

                CalculatorScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel) {

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {


        //Live calculation
        viewModel.liveExpress()


        //TopDesign
        Column(
            Modifier
                .fillMaxWidth()
                .weight(0.45F),
            verticalArrangement = Arrangement.Bottom
        ) {

            TopDesign(
                isResult = viewModel.isShowResult,
                textNumbersFirst = viewModel.textNumbersFirst,
                textNumberSecond = viewModel.textNumberSecond
            )

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

            BottomDesign(viewModel::onAction)

        }

    }

}

@Composable
fun TopDesign(
    isResult: MutableState<Boolean>,
    textNumbersFirst: MutableState<String>,
    textNumberSecond: MutableState<String>
) {

    //If show result === true (click button '=')
    if (isResult.value) {
        //FirstText
        TextCalculate(
            textNumbersFirst.value,
            MaterialTheme.typography.h5,
            MaterialTheme.colors.onSurface
        )
        //SecondText
        TextCalculate(
            textNumberSecond.value,
            MaterialTheme.typography.h4,
            MaterialTheme.colors.onPrimary
        )
    }
    //If show result === true
    else {
        //FirstText
        TextCalculate(
            textNumbersFirst.value,
            MaterialTheme.typography.h4,
            MaterialTheme.colors.onPrimary
        )
        //SecondText
        TextCalculate(
            textNumberSecond.value,
            MaterialTheme.typography.h5,
            MaterialTheme.colors.onSurface
        )
    }


}

@Composable
fun TextCalculate(text: String, textStyle: TextStyle, color: Color) {

    val scroll = rememberScrollState(0)

    LaunchedEffect(scroll.maxValue) {
        scroll.animateScrollTo(scroll.maxValue)
    }

    Text(
        text = text,
        style = textStyle,
        color = color,
        maxLines = 1,
        textAlign = TextAlign.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp)
            .horizontalScroll(scroll)
    )

}

@Composable
fun BottomDesign(onAction: (CalculatorAction) -> Unit) {

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {


        CalculatorButton(
            textButton = "⌫",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Delete)
        }

        CalculatorButton(
            textButton = "(",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Operation(CalculatorOperation.ParenthesesLeft))
        }

        CalculatorButton(
            textButton = ")",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Operation(CalculatorOperation.ParenthesesRight))
        }

        CalculatorButton(
            textButton = "÷",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
        }

    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CalculatorButton(
            textButton = "7",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(7))
        }
        CalculatorButton(
            textButton = "8",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(8))
        }
        CalculatorButton(
            textButton = "9",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(9))
        }
        CalculatorButton(
            textButton = "×",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
        }

    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CalculatorButton(
            textButton = "4",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(4))
        }
        CalculatorButton(
            textButton = "5",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(5))
        }
        CalculatorButton(
            textButton = "6",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(6))
        }
        CalculatorButton(
            textButton = "-",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Operation(CalculatorOperation.Minus))
        }

    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CalculatorButton(
            textButton = "1",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(1))
        }
        CalculatorButton(
            textButton = "2",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(2))
        }
        CalculatorButton(
            textButton = "3",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(3))
        }
        CalculatorButton(
            textButton = "+",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Operation(CalculatorOperation.Sum))
        }

    }

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CalculatorButton(
            textButton = "C",
            color = MaterialTheme.colors.secondary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Clear)
        }
        CalculatorButton(
            textButton = "0",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Number(0))
        }

        CalculatorButton(
            textButton = ".",
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier
        ) {
            onAction(CalculatorAction.Decimal)
        }

        CalculatorButton(
            textButton = "=",
            color = MaterialTheme.colors.onSecondary,
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .padding(bottom = 4.dp)
        ) {
            onAction(CalculatorAction.Equal)
        }

    }

}


@Composable
private fun CalculatorButton(
    textButton: String,
    color: Color,
    modifier: Modifier,
    OnButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { OnButtonClicked.invoke() }
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = textButton,
            color = color,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center
        )
    }
}

