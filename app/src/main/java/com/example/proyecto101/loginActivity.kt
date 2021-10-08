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
import kotlinx.android.synthetic.main.list_item.*

class loginActivity : AppCompatActivity() {

	private lateinit var txtUser:EditText
	private lateinit var  txtPassword:EditText
	private lateinit var progressBar: ProgressBar
	private lateinit var auth:FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)
		txtUser=findViewById(R.id.txtUser)
		txtPassword=findViewById(R.id.txtPassword)
		progressBar=findViewById(R.id.progressBar)
		auth= FirebaseAuth.getInstance()

	}

	//funcion de botones
	fun ForgotPassword(view:View){
		startActivity(Intent(this, ForgotPassActivity::class.java))
	}
	fun Login(view:View){
		LoginUser()
	}
	fun Register(view:View){
		startActivity(Intent(this, RegisterActivity::class.java))
	}
	//Funcion que valida que el usuario y la contraseña sean correctos
	private fun LoginUser(){
			//variables en la cual agregar el rol
		val user:String=txtUser.text.toString()
		val password:String=txtPassword.text.toString()
			if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
				progressBar.visibility=View.VISIBLE
				auth.signInWithEmailAndPassword(user,password)
					.addOnCompleteListener ( this){
						task->
						if(task.isSuccessful){
							action()
						}else	{
							Toast.makeText(this,"Error en la autenticacion", Toast.LENGTH_LONG).show()
						}
					}
			}
	}

	private fun action(){
		startActivity(Intent( this,MainActivity::class.java))
	}
}