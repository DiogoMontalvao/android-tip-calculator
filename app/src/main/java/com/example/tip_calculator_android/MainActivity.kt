package com.example.tip_calculator_android

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.ComponentActivity
import kotlin.math.*
import com.example.tip_calculator_android.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.layoutInputValorDaConta.setEndIconOnClickListener { limparTexto() }

        binding.inputValorDaConta.hint =
            getString(R.string.valor_da_conta, NumberFormat.getCurrencyInstance().format(0.00))

        binding.buttonCalcular.setOnClickListener { calcularGorjeta() }

        binding.constraint.setOnClickListener { recolherTeclado(binding.constraint) }
    }

    private fun limparTexto() {
        binding.inputValorDaConta.text?.clear()
    }

    private fun calcularGorjeta() {
        val stringCampoDeTexto = binding.inputValorDaConta.text.toString()
        val valor = stringCampoDeTexto.toDoubleOrNull()

        if (valor == null) {
            formatarGorjeta(0.0)
            return
        }

        val porcentagemGorjeta = when (binding.groupOpcoesGorjeta.checkedRadioButtonId) {
            R.id.radio_vinte_porcento -> 0.20
            R.id.radio_quinze_porcento -> 0.15
            else -> 0.10
        }

        var gorjeta = valor * porcentagemGorjeta

        if (binding.switchArredondarParaCima.isChecked) gorjeta = ceil(gorjeta)

        formatarGorjeta(gorjeta)
    }

    private fun formatarGorjeta(value: Double) {
        val gorjetaFormatada = NumberFormat.getCurrencyInstance().format(value)
        binding.textValorGorjeta.text = getString(R.string.valor_da_gorjeta, gorjetaFormatada)
    }

    private fun recolherTeclado(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}