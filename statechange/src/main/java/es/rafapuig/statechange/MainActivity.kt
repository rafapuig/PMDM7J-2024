package es.rafapuig.statechange

import android.content.res.Configuration
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.statechange.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = "StateChangeX"

    lateinit var binding: ActivityMainBinding

    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        Log.i(TAG,"Llamada a onCreate")

        binding = ActivityMainBinding.inflate(layoutInflater)

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG,"Llamada a onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"Llamada a onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"Llamada a onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"Llamada a onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"Llamada a onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG,"Llamada a onRestart")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(TAG,"Llamada a onSaveInstanceState")

        val userText = binding.editText.text
        outState.putCharSequence("savedText", userText)

        contador++;
        outState.putInt("contador", contador)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.i(TAG,"Llamada a onRestoreInstanceState")

        contador = savedInstanceState.getInt("contador")

        Log.i(TAG, "Veces restaurado = $contador")

        val userText = savedInstanceState.getCharSequence("savedText")
        binding.editText.setText(userText)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.i(TAG,"Llamada a onConfigurationChanged")
    }

}