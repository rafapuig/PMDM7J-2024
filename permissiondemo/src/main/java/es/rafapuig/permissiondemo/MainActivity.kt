package es.rafapuig.permissiondemo

import android.Manifest.permission.RECORD_AUDIO
import android.content.DialogInterface
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val TAG = "Permission_Demo"

    val RECORD_AUDIO_REQUEST_CODE = 12345

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupPermissions()
    }

    private fun setupPermissions() {

        val permission = checkSelfPermission(RECORD_AUDIO)

        if (permission != PERMISSION_GRANTED) {
            Log.i(TAG, "Permiso para grabar audio no está concedido")

            if (shouldShowRequestPermissionRationale(RECORD_AUDIO)) {
                showRationaleUI()
            } else {
                makeRequest()
            }

        } else {
            Log.i(TAG, "Permiso para grabar audio está concedido")
        }

    }

    private fun showRationaleUI() {

        AlertDialog.Builder(this)

            .setTitle(getString(R.string.record_dialog_title))

            .setMessage(getString(R.string.record_message))

            .setPositiveButton(R.string.record_ok) { dialog, which -> makeRequest() }

            .create()

            .show()
    }


    private fun makeRequest() {
        requestPermissions(arrayOf(RECORD_AUDIO), RECORD_AUDIO_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)


        when (requestCode) {
            RECORD_AUDIO_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PERMISSION_GRANTED) {
                    Log.i(TAG, "El permiso de grabar audio ha sido DENEGADO por el usuario")
                } else {
                    Log.i(TAG, "El permiso de grabar audio ha sido CONCEDIDO por el usuario")
                }

            }
        }


    }

}