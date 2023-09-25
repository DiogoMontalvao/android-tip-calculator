package com.example.tip_calculator_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlin.math.*
import com.example.tip_calculator_android.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonCalcular.setOnClickListener { calcularGorjeta() }
    }

    fun calcularGorjeta() {
        val stringCampoDeTexto = binding.inputValorDaConta.text.toString()
        val valor = stringCampoDeTexto.toDouble()

        val opcaoSelecionada = binding.groupOpcoesGorjeta.checkedRadioButtonId
        val porcentagemGorjeta = when (opcaoSelecionada){
            R.id.radio_vinte_porcento -> 0.20
            R.id.radio_quinze_porcento -> 0.15
            else -> 0.10
        }

        var gorjeta = valor * porcentagemGorjeta
        val arredondarParaCima = binding.switchArredondarParaCima.isChecked
        if (arredondarParaCima) gorjeta = ceil(gorjeta)

        val gorjetaFormatada = NumberFormat.getCurrencyInstance().format(gorjeta)
        binding.textValorGorjeta.text = getString(R.string.valor_da_gorjeta, gorjetaFormatada)

    }
}