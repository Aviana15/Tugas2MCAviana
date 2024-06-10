package com.example.tugas2mcaviana

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas2mcaviana.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            val email : String = binding.edtUsername.text.toString().trim()
            val password : String = binding.edtPassword.text.toString().trim()
            val confirmPassword : String = binding.edtRetypePassword.text.toString().trim()

            if (email.isEmpty()){
                binding.edtUsername.error = "Input Email"
                binding.edtUsername.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtUsername.error = "Invalid email"
                binding.edtUsername.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6){
                binding.edtPassword.error = "password must be more than 6 characters"
                binding.edtPassword.requestFocus()
                return@setOnClickListener
            }

            if (password != confirmPassword){
                binding.edtRetypePassword.error = "password must be match"
                binding.edtRetypePassword.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }



        binding.Login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }

    private  fun registerUser(email: String, password: String){

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this, LoginActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else{
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null){
            Intent(this, LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }
}
