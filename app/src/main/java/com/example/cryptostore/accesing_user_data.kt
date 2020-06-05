package com.example.cryptostore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import com.example.cryptostore.modules.Adapter
import com.example.cryptostore.modules.module
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.accesing_user_data.*

class accesing_user_data : AppCompatActivity() {

    //variables
    lateinit var list:MutableList<module>
    lateinit var listV:ListView
    private lateinit var categories:String
    private var getItem: module? = null
    private var  ref:DatabaseReference=FirebaseDatabase.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.accesing_user_data)
        categories = intent.getStringExtra("categories")

        list= mutableListOf()
        listV=findViewById<ListView>(R.id.list_v)
        Log.e("ref",ref.toString())

        //referencing to firebase child element
        ref=ref.child("users").child(getUid().toString()).child(categories)

        ref.addValueEventListener(object :ValueEventListener{

            override fun onCancelled(p0: DatabaseError) {
                Log.e("ref",p0.details)
            }
            override fun onDataChange(p0: DataSnapshot) {
               if(p0.exists())
               {
                   for (i in p0.children)
                   {
                       Log.e("i",""+i)
                       getItem= i.getValue(module::class.java)
                       if (getItem != null) {
                           list.add(getItem!!)
                       }
                   }
                   val adapter= Adapter(
                       applicationContext,
                       R.layout.list_layout,
                       list
                   )
                   listV.adapter=adapter
               }
            }

        })

          //back button operation
           back_btn.setOnClickListener{
               finish()
           }
    }
    private fun getUid():String
    {
        return(FirebaseAuth.getInstance().currentUser!!.uid)
    }
}
