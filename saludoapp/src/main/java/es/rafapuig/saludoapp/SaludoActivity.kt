package es.rafapuig.saludoapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SaludoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombre = intent.getStringExtra(MainActivity.NOMBRE)

        val textView = TextView(this)
        textView.textSize = 64f
        textView.text= "Holaaa!!!!! $nombre"

        setContentView(textView)

    }
}