package es.rafapuig.mysensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import es.rafapuig.mysensors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var sensorManager: SensorManager

    lateinit var sensors: List<Sensor>

    var currentSensor: Sensor? = null

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


        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)

        fillSpinner(sensors)

        initListeners()
    }


    private fun fillSpinner(sensors: List<Sensor>) {

        val list = sensors.map { sensor -> sensor.name }

        ArrayAdapter(this, android.R.layout.simple_spinner_item, list).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.sensorListSpinner.adapter = adapter
        }
    }

    private fun initListeners() {
        binding.sensorListSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    currentSensor = sensors[position]
                    updateUI()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

            }
    }

    private fun updateUI() {
        with(binding) {
            currentSensor?.let {
                sensorVendor.text = it.vendor ?: ""
                sensorType.text = getSensorTypeName(it.type)
            }
        }
    }

    fun getSensorTypeName(type: Int) = when (type) {
        Sensor.TYPE_GRAVITY -> "GRAVITY"
        else -> "OTHER"
    }

}