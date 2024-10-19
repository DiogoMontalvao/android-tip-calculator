package com.example.tip_calculator_android

import android.content.Context
import android.os.Bundle
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

        criaHint()

        limpaTexto()

        calculaGorjeta()

        recolherTeclado()
    }

    private fun criaHint() {
        binding.valorContaInput.hint =
            getString(
                R.string.valor_da_conta, NumberFormat.getCurrencyInstance().format(0.00)
            )
    }

    private fun limpaTexto() {
        binding.valorContaInputLayout.setEndIconOnClickListener {
            binding.valorContaInput.text?.clear()
        }
    }

    private fun calculaGorjeta() {
        binding.calcularButton.setOnClickListener {
            val inputValorConta: Double? =
                binding.valorContaInput.text?.toString()?.toDoubleOrNull()

            if (inputValorConta == null) {
                formataValorGorjeta(0.0)
                return@setOnClickListener
            }

            val porcentagemGorjeta = getPorcentagem()

            var gorjeta = inputValorConta * porcentagemGorjeta

            if (binding.arredondarCimaSwitch.isChecked) gorjeta = ceil(gorjeta)

            formataValorGorjeta(gorjeta)
        }
    }

    private fun formataValorGorjeta(valor: Double) {
        val valorFormatado = NumberFormat.getCurrencyInstance().format(valor)

        binding.valorGorjetaText.text = getString(R.string.valor_da_gorjeta, valorFormatado)
    }

    private fun getPorcentagem() =
        when (binding.opcoesGorjetaRadioGroup.checkedRadioButtonId) {
            binding.vintePorcentoRadio.id -> 0.20
            binding.quinzePorcentoRadio.id -> 0.15
            else -> 0.10
        }

    private fun recolherTeclado() {
        binding.constraint.setOnClickListener {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            inputMethodManager.hideSoftInputFromWindow(binding.constraint.windowToken, 0)
        }
    }
}