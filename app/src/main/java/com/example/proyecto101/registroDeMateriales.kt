package com.example.proyecto101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.proyecto101.databinding.ActivityRegistroDeMaterialesBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registro_de_materiales.*
import java.time.Month
import java.time.Year

class registroDeMateriales : AppCompatActivity() {
	//binding crea actividad para menu desplegable
	private lateinit var binding: ActivityRegistroDeMaterialesBinding
	//binding para enviar informacion a DataBase
	private lateinit var database : DatabaseReference
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityRegistroDeMaterialesBinding.inflate(layoutInflater)
		setContentView(binding.root)

		botones ()
		menuDesplegable()
	}
	//Funcion que llama los botones
	fun botones (){

		startWindows.setOnClickListener {
			val intent:Intent = Intent(this, MainActivity::class.java)
			startActivity(intent)
			finish()

		}

		registerMaterials.setOnClickListener {

			gatherMaterial()

		}

		editTextDate.setOnClickListener{ showDatePickerDialog() }

	}
	//Funcion para fechas
	private fun showDatePickerDialog() {
		val datePicker = DataPicker { day, month, year -> onDateSelected(day, month, year)}
		datePicker.show(supportFragmentManager, "datePicker")

	}
	fun onDateSelected(day:Int, month:Int, year:Int){
		editTextDate.setText("$year/$month/$day")
	}

	// funcion para guardar informacion en Firebase realTimeDatabase
	fun gatherMaterial(){
		val name = binding.PersonName.text.toString()
		val personalEmail = binding.EmailAddress.text.toString()
		val numberPhone = binding.TextPhone.text.toString()
		val address = binding.TextAddress.text.toString()
		val date=binding.editTextDate.text.toString()
		val materials = binding.autoCompleteTextView.text.toString()

		database = FirebaseDatabase.getInstance().getReference("GatherMaterials")
		val	gatherMaterialFireBase = GatherMaterialFireBase (name,personalEmail,numberPhone,address,date,materials)
		database.child(personalEmail).setValue(gatherMaterialFireBase).addOnSuccessListener {

			binding.PersonName.text.clear()
			binding.EmailAddress.text.clear()
			binding.TextPhone.text.clear()
			binding.TextAddress.text.clear()
			binding.editTextDate.text.clear()
			binding.autoCompleteTextView.text.clear()

			Toast.makeText(this, "Guardado Correcto", Toast.LENGTH_SHORT).show()

		}.addOnFailureListener{
			Toast.makeText(this, "Fallo", Toast.LENGTH_LONG).show()
		}
	}
	// funcion de menu desplegable
	fun menuDesplegable(){
		val materialsGather = resources.getStringArray(R.array.materials_gather)
		val adapter = ArrayAdapter(
			this,
			R.layout.list_item,
			materialsGather
		)
		with(binding.autoCompleteTextView){
			setAdapter(adapter)
		}
	}
}