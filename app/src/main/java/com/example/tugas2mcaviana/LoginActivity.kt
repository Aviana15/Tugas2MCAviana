package com.example.tugas2mcaviana

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tugas2mcaviana.databinding.ActivityForgetPasswordBinding
import com.example.tugas2mcaviana.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email : String = binding.edtUsername.text.toString().trim()
            val password : String = binding.edtPassword.text.toString().trim()

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
            loginUser(email, password)
        }

        binding.linkKeRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.linkForgetPassword.setOnClickListener{
            Intent(this, ActivityForgetPasswordBinding::class.java).also {
                startActivity(it)
            }
        }

    }

    private fun loginUser(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else{
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (firebaseAuth.currentUser != null) {
            Intent(this, MainActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }
    }

}