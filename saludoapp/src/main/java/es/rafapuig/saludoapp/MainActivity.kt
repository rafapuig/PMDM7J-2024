package es.rafapuig.saludoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object {
        val NOMBRE = "es.rafapuig.saludoapp.MainActivity.NOMBRE"
    }

    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        button = Button(this)

        button.text = "Haz click"

        setContentView(button)

        button.setOnClickListener { cambiarActividad() }
    }

    fun cambiarActividad() {

        val intent = Intent(this, SaludoActivity::class.java)
        intent.putExtra(NOMBRE, "Rafa Puig")

        //startActivity(intent)
        startActivityForResult.launch(intent)
    }


    val startActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result -> processResult(result) }


    fun processResult(result: ActivityResult) {
       if(result.resultCode == RESULT_OK) {

           val resultado = result.data?.getStringExtra("RESULTADO")

           button.text = resultado
       }
    }


}