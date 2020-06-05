package com.example.cryptostore

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth: FirebaseAuth //not null type can be initlilized  ising  letinit keyword
    private lateinit var currentUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_login)
        login_btn.setOnClickListener(this)

        mAuth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()
//        currentUser= mAuth.currentUser!!
        Toast.makeText(applicationContext, "Already login", Toast.LENGTH_LONG).show()
        val intent: Intent = Intent(applicationContext, Home::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.login_btn) {
                if (!(TextUtils.isEmpty(email_id.text) || TextUtils.isEmpty(password.text))) {
//                     Toast.makeText(applicationContext,"Email id is"+email_id.text.toString(),Toast.LENGTH_LONG).show()
//                     Toast.makeText(applicationContext,"password is "+password.text.toString(),Toast.LENGTH_LONG).show()
                    signIn(email_id.text.toString(), password.text.toString())
                } else {
                    Toast.makeText(applicationContext, "Field is vacant", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    fun signUp(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Account Created Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent: Intent = Intent(applicationContext, Home::class.java)
                    startActivity(intent)
                    val user = mAuth.currentUser
                } else {
                    if (task.exception.toString() == "com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account.") {
                        signIn(email, password)
                    } else {
                        Log.w("ERROr", "Check this SIGN UP ERROR", task.exception)
                    }

                }
            }
    }

    fun signIn(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT)
                        .show()
                    val intent: Intent = Intent(applicationContext, Home::class.java)
                    startActivity(intent)
                    val user = mAuth.currentUser
                } else {
                    if (task.exception.toString() == "com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted.") {
                        signUp(email, password)
                    } else {
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            }

    }

    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }
}
