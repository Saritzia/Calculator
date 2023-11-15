package com.saritzia.calculator

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.saritzia.calculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var result= "0"
    private var number = "0"
    private var operationSymbol : String? = null
    private enum class Operation {
        NUMBERSIGN,
        SIN,
        COSIN,
        SQRT
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        result = savedInstanceState?.getString("result") ?: "0"
        number = savedInstanceState?.getString("number") ?: "0"
        operationSymbol = savedInstanceState?.getString("operation") ?: null

        binding.resultTextView.text = number
        binding.totalTextView.text = result
        checkButtonTapped()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result",result)
        outState.putString("number",number)
        outState.putString("operation",operationSymbol)
    }
    private fun calculateInstantResult(operation: Operation) {
        if(operationSymbol==null) {
            result = when (operation) {
                Operation.NUMBERSIGN -> changeNumberSign().toString()
                Operation.SIN -> kotlin.math.sin(number.toFloat()).toString()
                Operation.COSIN -> kotlin.math.cos(number.toFloat()).toString()
                Operation.SQRT -> kotlin.math.sqrt(number.toFloat()).toString()
            }
        }else {
            result = when (operation) {
                Operation.NUMBERSIGN -> changeNumberSign().toString()
                Operation.SIN -> kotlin.math.sin(result.toFloat()).toString()
                Operation.COSIN -> kotlin.math.cos(result.toFloat()).toString()
                Operation.SQRT -> kotlin.math.sqrt(result.toFloat()).toString()
            }
        }
        number = "0"
        operationSymbol = "="
    }

    private fun updateResultText() {
        binding.resultTextView.text = number
        binding.totalTextView.text = result
    }

    private fun calculateComplexResult() {
        if(operationSymbol!=null) {
            when {
                operationSymbol.equals("+") -> {
                    result = (result.toFloat() + number.toFloat()).toString()
                }

                operationSymbol.equals("-") -> {
                    result = (result.toFloat() - number.toFloat()).toString()
                }

                operationSymbol.equals("*") -> {
                    result = (result.toFloat() * number.toFloat()).toString()
                }

                operationSymbol.equals("/") -> {
                    result = (result.toFloat() / number.toFloat()).toString()
                }

                operationSymbol.equals("%") -> {
                    result = (result.toFloat() % number.toFloat()).toString()
                }

                operationSymbol.equals("^") -> {
                    result = ((result.toFloat()).pow((number.toFloat()))).toString()
                }
                operationSymbol.equals("=") -> number = "0"
            }
        }else {
            result = number
        }
        updateResultText()
    }

    private fun changeNumberSign() {
        if (operationSymbol == null){
            result = number
        }
        result = if (result.toFloat()>0) {
            ("-$result")
        } else {
            result.substring(1)
        }
        operationSymbol = "="
        binding.resultTextView.text = result
    }
    private fun createLongNumber(buttonNumber: String){
        number = if(number != "0") {
            "$number$buttonNumber"
        }else {
            buttonNumber
        }
        updateResultText()
    }
    private fun checkButtonTapped() {
        binding.oneButton.setOnClickListener {
            createLongNumber("1")
        }
        binding.twoButton.setOnClickListener {
            createLongNumber("2")
        }
        binding.threeButton.setOnClickListener {
            createLongNumber("3")
        }
        binding.fourButton.setOnClickListener {
            createLongNumber("4")
        }
        binding.fiveButton.setOnClickListener {
            createLongNumber("5")
        }
        binding.sixButton.setOnClickListener {
            createLongNumber("6")
        }
        binding.sevenButton.setOnClickListener {
            createLongNumber("7")
        }
        binding.eightButton.setOnClickListener {
            createLongNumber("8")
        }
        binding.nineButton.setOnClickListener {
            createLongNumber("9")
        }
        binding.zeroButton.setOnClickListener {
            createLongNumber("0")
        }
        binding.acButton.setOnClickListener {
            number = "0"
            result = "0"
            operationSymbol = null
            updateResultText()
        }
        binding.cosinButton?.setOnClickListener {
            calculateInstantResult(Operation.COSIN)
            updateResultText()
        }
        binding.sinButton?.setOnClickListener {
            calculateInstantResult(Operation.COSIN)
            updateResultText()
        }
        binding.sqrtButton?.setOnClickListener {
            calculateInstantResult(Operation.SQRT)
            updateResultText()
        }
        binding.dotButton.setOnClickListener {
            updateResultText()
        }
        binding.plusButton.setOnClickListener {
            calculateComplexResult()
            number = "0"
            operationSymbol = "+"
        }
        binding.minusButton.setOnClickListener {
            calculateComplexResult()
            number = "0"
            operationSymbol = "-"
        }
        binding.multiplyButton.setOnClickListener {
            calculateComplexResult()
            number = "0"
            operationSymbol = "*"
        }
        binding.quotientButton.setOnClickListener {
            calculateComplexResult()
            number = "0"
            operationSymbol = "/"
        }
        binding.moduleButton.setOnClickListener {
            calculateComplexResult()
            number = "0"
            operationSymbol = "%"
        }
        binding.powButton?.setOnClickListener {
            calculateComplexResult()
            number = "0"
            operationSymbol = "^"
        }
        binding.equalButton.setOnClickListener {
            calculateComplexResult()
            operationSymbol = "="
            binding.resultTextView.text = result
        }
        binding.possitiveOrNegativeButton.setOnClickListener {
            changeNumberSign()
        }
        binding.dotButton.setOnClickListener {
            createLongNumber(".")
        }
    }
}