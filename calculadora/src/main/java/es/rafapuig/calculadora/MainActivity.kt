package es.rafapuig.calculadora

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.calculadora.databinding.ActivityMainBinding

enum class Estado { INSERT_OP1, INSERT_OP2 }


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    var estado = Estado.INSERT_OP1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        reset()

        binding.key1.setOnClickListener { updateOperando(1) }
        binding.key2.setOnClickListener { updateOperando(2) }
        binding.key3.setOnClickListener { updateOperando(3) }
        binding.key4.setOnClickListener { updateOperando(4) }
        binding.key5.setOnClickListener { updateOperando(5) }
        binding.key6.setOnClickListener { updateOperando(6) }
        binding.key7.setOnClickListener { updateOperando(7) }
        binding.key8.setOnClickListener { updateOperando(8) }
        binding.key9.setOnClickListener { updateOperando(9) }
        binding.key0.setOnClickListener { updateOperando(0) }

        binding.keyAC.setOnClickListener { reset() }
    }

    private fun updateOperando(digito: Int) {

        var operando = when (estado) {
            Estado.INSERT_OP1 -> operando1
            Estado.INSERT_OP2 -> operando2
        }
        
        operando= añadirDigito(operando, digito)

        when(estado) {
            Estado.INSERT_OP1 -> operando1 = operando
            Estado.INSERT_OP2 -> operando2 = operando
        }

        updateDisplay()
    }

    private fun reset() {
        operando1 = 0
        operando2 = 0
        estado = Estado.INSERT_OP1
        updateDisplay()
    }

    fun updateDisplay() {
        binding.display.text = when(estado) {
            Estado.INSERT_OP1 ->  operando1
            Estado.INSERT_OP2 -> operando2
        }.toString()
    }

    var operando1: Int = 0
    var operando2: Int = 0

    fun añadirDigito(numero: Int, digito: Int) = numero * 10 + digito

}