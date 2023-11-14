package com.saritzia.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saritzia.calculator.databinding.ActivityMainBinding
import java.lang.Math.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var result = 0.0
    private var valuesToCalculate = "0"
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
        binding.resultTextView.text = "$result"
        checkButtonTapped()
    }

    private fun updateResultText() {
        binding.resultTextView.text = "$result"
    }

    private fun calculateInstantResult(operation: Operation) {
        result = when(operation){
            Operation.NUMBERSIGN -> changeNumberSign()
            Operation.SIN -> kotlin.math.sin(valuesToCalculate.toDouble())
            Operation.COSIN -> kotlin.math.cos(valuesToCalculate.toDouble())
            Operation.SQRT -> kotlin.math.sqrt(valuesToCalculate.toDouble())
        }
    }

    private fun calculateComplexResult() {
        val fragments = valuesToCalculate.split(" ")
        result = fragments[0].toDouble()
        var contador = 0
        while(contador<fragments.size){
            when {
                fragments[contador].equals("+") -> {
                    contador++
                    result += fragments[contador].toDouble()
                }
                fragments[contador].equals("-") -> {
                    contador++
                    result -= fragments[contador].toDouble()
                }
                fragments[contador].equals("*") -> {
                    contador++
                    result *= fragments[contador].toDouble()
                }
                fragments[contador].equals("/") -> {
                    contador++
                    result /= fragments[contador].toDouble()
                }
                fragments[contador].equals("%") -> {
                    contador++
                    result %= fragments[contador].toDouble()
                }
                fragments[contador].equals("^") -> {
                    contador++
                    result = result.pow(fragments[contador].toDouble())
                }
             }
            contador++
        }
        valuesToCalculate = result.toString()
        updateResultText()
    }

    private fun changeNumberSign():Double {
        if (valuesToCalculate>"0") {
            return (-kotlin.math.abs(valuesToCalculate.toDouble()))
        } else {
           return kotlin.math.abs(valuesToCalculate.toDouble())
        }
    }

    private fun addSymbol(symbol: String) {
        valuesToCalculate = "{$valuesToCalculate} {$symbol} "
        updateResultText()
    }

    private fun createLongNumber(number: Int){
        if(!valuesToCalculate.equals("0")){
            valuesToCalculate = "${this.valuesToCalculate}$number"
        }else {
            valuesToCalculate="$number"
        }
        updateResultText()
    }

    private fun checkButtonTapped() {
        binding.oneButton.setOnClickListener {
            createLongNumber(1)
        }
        binding.twoButton.setOnClickListener {
            createLongNumber(2)
        }
        binding.threeButton.setOnClickListener {
            createLongNumber(3)
        }
        binding.fourButton.setOnClickListener {
            createLongNumber(4)
        }
        binding.fiveButton.setOnClickListener {
            createLongNumber(5)
        }
        binding.sixButton.setOnClickListener {
            createLongNumber(6)
        }
        binding.sevenButton.setOnClickListener {
            createLongNumber(7)
        }
        binding.eightButton.setOnClickListener {
            createLongNumber(8)
        }
        binding.nineButton.setOnClickListener {
            createLongNumber(9)
        }
        binding.zeroButton.setOnClickListener {
            createLongNumber(0)
        }
        binding.acButton.setOnClickListener {
            valuesToCalculate = "0"
            result = 0.0
            updateResultText()
        }
        binding.cosinButton?.setOnClickListener {
            calculateInstantResult(Operation.COSIN)
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.sinButton?.setOnClickListener {
            calculateInstantResult(Operation.COSIN)
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.sqrtButton?.setOnClickListener {
            calculateInstantResult(Operation.SQRT)
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.dotButton.setOnClickListener {
            addSymbol(".")
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.plusButton.setOnClickListener {
            addSymbol("+")
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.minusButton.setOnClickListener {
            addSymbol("-")
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.multiplyButton.setOnClickListener {
            addSymbol("*")
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.quotientButton.setOnClickListener {
            addSymbol("/")
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.moduleButton.setOnClickListener {
            addSymbol("%")
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.powButton?.setOnClickListener {
            addSymbol("^")
            valuesToCalculate = result.toString()
            updateResultText()
        }
        binding.equalButton.setOnClickListener {
            calculateComplexResult()
            updateResultText()
        }
    }
}