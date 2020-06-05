package com.example.cryptostore

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptostore.R.drawable.*
import com.example.cryptostore.modules.Save
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_user_input.view.*


class Home : AppCompatActivity(), View.OnClickListener {

     //variables
    private lateinit var getName: String
    private lateinit var getPassword: String
    private lateinit var writeUser: Save
    private var database: DatabaseReference=FirebaseDatabase.getInstance().reference //always write in single line

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
           setContentView(R.layout.activity_home)

          //ui upper part click event
           atm_btn.setOnClickListener(this)
           email_btn.setOnClickListener(this)
           socialAcc_btn.setOnClickListener(this)

           //ui lower part click event
           see_atm_btn.setOnClickListener(this)
           see_email_btn.setOnClickListener(this)
           see_socialAcc_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //getting instance
        val intent:Intent=Intent(applicationContext,accesing_user_data::class.java)
        //image resources
        val imgResource= arrayOf<Int>(atm_acc, email_acc, social_acc)
        if (v != null) when (v.id) {
            R.id.atm_btn -> savePopupMenu("ATM",imgResource[0],"Bank Name","Account number")
            R.id.email_btn -> {
                savePopupMenu("EMAIL",imgResource[1],"Email id","Password")
                Toast.makeText(applicationContext,"email btn is clicked",Toast.LENGTH_LONG).show()

            }
            R.id.socialAcc_btn -> {
                savePopupMenu("SOCIAL",imgResource[2],"User Name","Password")
                Toast.makeText(applicationContext,"email social acc is clicked",Toast.LENGTH_LONG).show()
            }
            //see buttons operations
            R.id.see_atm_btn->{ intent.putExtra("categories","ATM")
                                     startActivity(intent)}
            R.id.see_email_btn->{ intent.putExtra("categories","EMAIL")
                startActivity(intent)}
            R.id.see_socialAcc_btn->{ intent.putExtra("categories","SOCIAL")
                startActivity(intent)}
        }

    }
    private fun savePopupMenu(type:String, img:Int, _hint:String, password:String)
    {
        //inflate the dialog from custom view
        val mDialogViews =
            LayoutInflater.from(this).inflate(R.layout.activity_user_input, null)

        //hiding editext
        mDialogViews.user_password.visibility=View.GONE
        //setting background
        mDialogViews.cat_img.setBackgroundResource(img)

        //changing editText hint
        mDialogViews.user_input.hint=_hint

        //changing password hint
        mDialogViews.user_password.hint=password


        //alert dialog builder
        val mBuilder= AlertDialog.Builder(this)
            .setView(mDialogViews)
        // .setTitle("Login page popup")
        //        //show message
        val malertDialog=mBuilder.show()
        //login button click of custom layout
        mDialogViews.user_button.setOnClickListener{
//            malertDialog.dismiss()
            //get Text from EditText
            getName=mDialogViews.user_input.text.toString()
            //set the input text
            //making Invisible  editext
            mDialogViews.user_input.visibility=View.GONE
            //making visible  editext
            mDialogViews.user_password.visibility=View.VISIBLE

            getPassword=mDialogViews.user_password.text.toString()

            //set the input text
            if(!(TextUtils.isEmpty(mDialogViews.user_input.text)||TextUtils.isEmpty(mDialogViews.user_password.text)))
            {
                writeNewUserData(type,getUid(),getName,getPassword)
                malertDialog.dismiss()
            }

        }
        //cancel button
        mDialogViews.cancel_btn.setOnClickListener{
            malertDialog.dismiss()
        }

    }

    private fun writeNewUserData(type: String, userId: String, name: String, password: String?) {
        writeUser = Save(name, password)

         database.child("users").child(userId).child(type).push().setValue(writeUser)
    }
    private fun getUid():String
    {
        return(FirebaseAuth.getInstance().currentUser!!.uid)
    }



}
