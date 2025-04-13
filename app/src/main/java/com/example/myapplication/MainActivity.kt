package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var tvResult: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstNumber = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.caculator)

        tvResult = findViewById(R.id.tvResult)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btnDot
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                val value = (it as Button).text.toString()
                currentInput += value
                tvResult.text = currentInput
            }
        }

    val operators = mapOf(
        R.id.btnPlus to "+",
        R.id.btnMinus to "-",
        R.id.btnMultiply to "*",
        R.id.btnDivide to "/"
    )
    operators.forEach { (id, op) ->
        findViewById<Button>(id).setOnClickListener {
            if (currentInput.isNotEmpty()) {
                firstNumber = currentInput
                currentInput = ""
                operator = op
                tvResult.text = ""
            }
        }
    }
        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            if (firstNumber.isNotEmpty() && currentInput.isNotEmpty()) {
                val num1 = firstNumber.toDouble()
                val num2 = currentInput.toDouble()
                val result = when (operator) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "*" -> num1 * num2
                    "/" -> if (num2 != 0.0) num1 / num2 else "Error"
                    else -> "Error"
                }
                tvResult.text = result.toString()
                currentInput = result.toString()
                firstNumber = ""
                operator = ""
            }
        }

        findViewById<Button>(R.id.btnC).setOnClickListener {
            currentInput = ""
            firstNumber = ""
            operator = ""
            tvResult.text = "0"
        }
    }
    }
    
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}