package com.example.proyecto101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.security.auth.login.LoginException

class RegisterActivity : AppCompatActivity() {
	private	lateinit var txtName: EditText
	private lateinit var txtLastName: EditText
	private lateinit var txtAddress: EditText
	private lateinit var txtCity: EditText
	private lateinit var txtPhone: EditText
	private lateinit var txtEmail: EditText
	private lateinit var txtPassword: EditText
	private lateinit var progressBar: ProgressBar
	private lateinit var dbReference: DatabaseReference
	private lateinit var dataBase: FirebaseDatabase
	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_register)
		txtName=findViewById(R.id.txtName)
		txtLastName=findViewById(R.id.txtLastName)
		txtAddress=findViewById(R.id.txtAddress)
		txtCity=findViewById(R.id.txtCity)
		txtPhone=findViewById(R.id.txtPhone)
		txtEmail=findViewById(R.id.txtEmail)
		txtPassword=findViewById(R.id.txtPassword)
		//instancia de firebase
		progressBar= findViewById(R.id.progressBar)

		dataBase= FirebaseDatabase.getInstance()
		auth= FirebaseAuth.getInstance()

		dbReference=dataBase.reference.child("User")
	}


	fun register(view:View){
		createNewAccount()
	}
	// crea el usuario
	fun createNewAccount(){
		val name:String=txtName.text.toString()
		val lastName:String=txtLastName.text.toString()
		val address:String=txtAddress.text.toString()
		val city:String=txtCity.text.toString()
		val phone:String=txtPhone.text.toString()
		val email:String=txtEmail.text.toString()
		val password:String=txtPassword.toString()
		//ciclo en el cual se solicita datos de registro.
		if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(address)
			&& !TextUtils.isEmpty(city)&& !TextUtils.isEmpty(phone)	&&  !TextUtils.isEmpty(email)
			&& !TextUtils.isEmpty(password)){
			progressBar.visibility=View.VISIBLE
			//solicita el email y la contraseña
			auth.createUserWithEmailAndPassword(email,password)
				.addOnCompleteListener(this) {
					task ->
					if(task.isComplete){
						//Obtener el usuario registrado
						val user:FirebaseUser?=auth.currentUser
						//envia datos de registro Usuario y contraseña a bd
						verifyEmail(user)
						//envia datos a bd al chiel creado user, con llame primaria unica
						val userBD = user?.uid?.let { dbReference.child(it) }
						userBD?.child("name")?.setValue(name)
						userBD?.child("lastName")?.setValue(lastName)
						userBD?.child("address")?.setValue(address)
						userBD?.child("city")?.setValue(city)
						userBD?.child("phone")?.setValue(phone)
						action()
					}
				}
		}
	}
	//envia a ventaja de login
	fun action(){
		startActivity(Intent(this,loginActivity::class.java))
	}
	// enviar email a usuario que se registro correctamente y devuelve mensaje de usuario creado
	fun verifyEmail(user: FirebaseUser?){
		user?.sendEmailVerification()
			?.addOnCompleteListener(this){
					task ->
				if (task.isComplete){
					Toast.makeText(this, "Correo Enviado", Toast.LENGTH_LONG).show()

				}else{
					Toast.makeText(this,"Error al enviar correo",Toast.LENGTH_LONG).show()
				}
			}

	}
}