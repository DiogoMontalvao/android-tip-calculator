package com.example.tip_calculator_android

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import com.example.tip_calculator_android.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createHint()
        clearText()
        calculateTip()
        binding.constraintLayout.setOnClickListener { hideKeyboard(it) }
    }

    private fun createHint() {
        binding.billValueEdit.hint =
            getString(
                R.string.valor_da_conta, NumberFormat.getCurrencyInstance().format(0.00)
            )
    }

    private fun clearText() {
        binding.apply {
            billValueLayout.setEndIconOnClickListener { billValueEdit.text?.clear() }
        }
    }

    private fun calculateTip() {
        binding.apply {
            calculateButton.setOnClickListener {
                val billValue: Double? = billValueEdit.text?.toString()?.toDoubleOrNull()

                if (billValue == null) {
                    formatTip(0.0)
                    return@setOnClickListener
                }

                val tipPercentage = getPorcentagem()

                var tip = billValue * tipPercentage

                if (roundUpSwitch.isChecked) tip = ceil(tip)

                formatTip(tip)
                hideKeyboard(calculateButton)
            }
        }
    }

    private fun formatTip(value: Double) {
        val formatedValue = NumberFormat.getCurrencyInstance().format(value)

        binding.tipValueText.text = getString(R.string.valor_da_gorjeta, formatedValue)
    }

    private fun getPorcentagem(): Double {
        binding.apply {
            return when (
                percentageOptionsGroup.checkedRadioButtonId) {
                twentyPercentageRadio.id -> 0.20
                fifteenPercentageRadio.id -> 0.15
                else -> 0.10
            }
        }
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}