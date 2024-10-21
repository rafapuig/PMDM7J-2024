package es.rafapuig.permissiondemo

import android.Manifest.permission.CAMERA
import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.permissiondemo.MainActivity.Companion.TAG


class MiActivityResultCallback : ActivityResultCallback<Boolean> {
    override fun onActivityResult(isGranted: Boolean) {
        if (isGranted) {
            Log.i(TAG, "El permiso de grabar audio ha sido DENEGADO por el usuario")
        } else {
            Log.i(TAG, "El permiso de grabar audio ha sido DENEGADO por el usuario")
        }
    }
}

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "Permission_Demo"
    }

    val RECORD_AUDIO_REQUEST_CODE = 12345
    val CAMERA_REQUEST_CODE = 888
    val CAMERA_AND_RECORD_AUDIO_REQUEST_CODE = 1000


    private val requestRecordAudioPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.i(TAG, "El permiso de grabar audio ha sido DENEGADO por el usuario")
            } else {
                Log.i(TAG, "El permiso de grabar audio ha sido DENEGADO por el usuario")
            }
        }

    private val requestCameraPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.i(TAG, "El permiso de usar la camara ha sido DENEGADO por el usuario")
            } else {
                Log.i(TAG, "El permiso de usar la camara ha sido DENEGADO por el usuario")
            }
        }


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
        //requestPermissions(arrayOf(RECORD_AUDIO), RECORD_AUDIO_REQUEST_CODE)
        //requestRecordAudioPermissionLauncher.launch(RECORD_AUDIO)
        //requestCameraPermissionLauncher.launch(CAMERA)

        requestPermissions(arrayOf(CAMERA),CAMERA_REQUEST_CODE)
        requestPermissions(arrayOf(CAMERA, RECORD_AUDIO), CAMERA_AND_RECORD_AUDIO_REQUEST_CODE)
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

            CAMERA_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PERMISSION_GRANTED) {
                    Log.i(TAG, "El permiso de usar la camara ha sido DENEGADO por el usuario")
                } else {
                    Log.i(TAG, "El permiso de usar la camara ha sido CONCEDIDO por el usuario")
                }
            }

            CAMERA_AND_RECORD_AUDIO_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PERMISSION_GRANTED || grantResults[1] != PERMISSION_GRANTED) {
                  
                }
            }
        }


    }

}