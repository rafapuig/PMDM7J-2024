package es.rafapuig.implicitintent

import android.Manifest.permission.CALL_PHONE
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.implicitintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val callPermissionRequestLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) makePhoneCall()
        }

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

        initListeners()
    }

    private fun initListeners() {
        binding.takePhotoButton.setOnClickListener { onTakingPhoto() }
        binding.callButton.setOnClickListener { onMakingACall() }
    }

    private fun onMakingACall() {
        if (checkSelfPermission(CALL_PHONE) != PERMISSION_GRANTED) {
            if(shouldShowRequestPermissionRationale(CALL_PHONE)) {
                showUIRationale()
            } else {
                requestCallPermission()
            }
        } else {
            makePhoneCall()
        }
    }

    private fun showUIRationale() {
        AlertDialog.Builder(this)
            .setTitle("Solicitud de permiso de llamadas")
            .setMessage("Es necesario que permitas hacer llamadas a la aplicacion")
            .setPositiveButton("OK"
            ) { dialog, which -> requestCallPermission() }
            .create()
            .show()
    }

    private fun requestCallPermission() {
        callPermissionRequestLauncher.launch(CALL_PHONE)
    }

    private fun makePhoneCall() {
        val phoneNumber = binding.editTextPhone.text.toString()
        callPhone(phoneNumber)
    }

    private fun callPhone(phoneNumber: String) {
        val phoneCall = Intent()
        phoneCall.action = Intent.ACTION_CALL
        phoneCall.data = Uri.parse("tel:$phoneNumber")
        startActivity(phoneCall)
    }


    private fun onTakingPhoto() {
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        startActivity(intent)
    }

}