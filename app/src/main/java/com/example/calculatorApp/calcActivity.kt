package com.example.calculatorApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.calculatorApp.databinding.ActivityCalcBinding

class calcActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalcBinding
    private var currentInput = ""
    private var result = 0.0
    private var displayedresult=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalcBinding.inflate(layoutInflater)
        setContentView(binding.root)
            //displayHelloMessage
        val greetingIntent = intent
        val uName = greetingIntent.getStringExtra("userName")
        Toast.makeText(this, "Hello,$uName", Toast.LENGTH_SHORT).show()

        setNumberButtonClickListeners()
        setOperatorButtonClickListeners()

    }
    private fun onNumberButtonClick(value: String) {
        currentInput += value
        updateDisplay()
    }
    private fun onOperatorButtonClick(operator: String) {
        currentInput += " $operator "
        updateDisplay()
    }
    private fun onClearButtonClick() {
        currentInput = ""
        displayedresult=""
        result = 0.0

        updateDisplay()
        updateResult()
    }
    private fun onEqualsButtonClick() {
        try {
            result = calculate()
            displayedresult = result.toString()
            updateResult()
        } catch (e: ArithmeticException) {
            currentInput = "Error"
            updateDisplay()
        }
    }
    private fun setNumberButtonClickListeners() {
        val numberButtons = listOf(
            binding.zeroBtn, binding.oneBtn, binding.twoBtn, binding.threeBtn,
            binding.fourBtn, binding.fiveBtn, binding.sixBtn, binding.sevenBtn,
            binding.eightBtn, binding.nineBtn, binding.decimalBtn
        )
        for (button in numberButtons) {
            button.setOnClickListener {
                onNumberButtonClick((it as Button).text.toString())
            }
        }
    }

    private fun setOperatorButtonClickListeners() {
        val operatorButtons = listOf(
            binding.addBtn, binding.subtractBtn,
            binding.multiplyBtn, binding.divideBtn,binding.modulusBtn
        )

        for (button in operatorButtons) {
            button.setOnClickListener {
                onOperatorButtonClick((it as Button).text.toString())
            }
        }

        binding.equalBtn.setOnClickListener { onEqualsButtonClick() }
        binding.clear.setOnClickListener { onClearButtonClick() }
    }


    private fun calculate(): Double {
        // Split the input by spaces to separate numbers and operators
        val char = currentInput.split(" ")
        var total = char[0].toDouble()
        try{
        for (i in 1 until char.size step 2) {
            val operator = char[i]
            val operand = char[i + 1].toDouble()

            when (operator) {
                "+" -> total += operand
                "-" -> total -= operand
                "×" -> total *= operand
                "÷" -> total /= operand
                "%" -> total %= operand
            }
        }

        return total
        } catch (e: Exception) {
            Log.e("calcActivity", "Error during evaluation: ${e.message}")
            throw ArithmeticException("Invalid calculation")
        }
    }

    private fun Double.isWholeNumber(): Boolean {
        return this % 1 == 0.0
    }

    private fun updateDisplay() {
        binding.expressionTextView.text = currentInput
    }

    private fun updateResult() {
        try {
            if (displayedresult.endsWith(".0")) {
                displayedresult = displayedresult.replace(oldValue = ".0", newValue = "")
            }
        } catch (e: Exception) {

        }
        binding.resultTextView.text = displayedresult
    }
}