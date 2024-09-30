package es.rafapuig.sumarapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import es.rafapuig.sumarapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initListeners()
    }



    private fun initListeners() {
        binding.btnSumar.setOnClickListener { v -> calcularSuma(v) }
    }


    private fun calcularSuma(view: View) {

        try {
            val sum1 = binding.etSumando1.text.toString().toInt()

            val sum2 = binding.etSumando2.text.toString().toInt()

            val suma = sum1 + sum2

            binding.tvResultado.text = suma.toString()

        } catch (ex: NumberFormatException) {
            Toast
                .makeText(this, "Números no válidos", Toast.LENGTH_LONG)
                .show()
        }

    }
}

