package com.example.proyecto101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.proyecto101.databinding.ActivityRegistroDeMaterialesBinding
import kotlinx.android.synthetic.main.activity_registro_de_materiales.*

class registroDeMateriales : AppCompatActivity() {
	//binding crea actividad para menu desplegable
	private lateinit var binding: ActivityRegistroDeMaterialesBinding

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

	}

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