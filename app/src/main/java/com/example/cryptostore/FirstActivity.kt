package com.example.cryptostore

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptostore.Login
import kotlinx.android.synthetic.main.first_activity_layout.*

class FirstActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(R.layout.first_activity_layout)
        login_btn.setOnClickListener(this)
        signup_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.login_btn) {
//                Toast.makeText(this,"Login button is clicked",Toast.LENGTH_LONG).show()
                val intent: Intent = Intent(applicationContext, Login::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Signup button is clicked", Toast.LENGTH_LONG).show()
            }

        }
    }
}
