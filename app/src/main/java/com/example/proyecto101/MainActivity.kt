package com.example.proyecto101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		Botones()

	}
	//Funcion que llama los botones
	fun Botones(){
		RegisterMaterial.setOnClickListener {
			val intent:Intent = Intent(this, registroDeMateriales::class.java)
				startActivity(intent)
			finish()
		}

		AgendaRecolector.setOnClickListener{
			val intent:Intent = Intent(this, Gatherer::class.java)
			startActivity(intent)

		}
	}
}