package com.saritzia.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saritzia.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var result: String
    private var firstNumber = 0
    private var secondNumber = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        result = getString(R.string.zero)
        binding.resultTextView.text = result
    }

    private fun updateResultText() {
        binding.resultTextView.text = "$result"
    }

    private fun checkButtonTapped() {
        //TO do
    }
}