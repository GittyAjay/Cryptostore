package com.example.cryptostore.modules

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.list_layout.view.*

class Adapter(val mCtx:Context,val layoutResId:Int,val list:List<module>):ArrayAdapter<module>(mCtx,layoutResId,list) {
    //variables
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
         val layoutInflator:LayoutInflater= LayoutInflater.from(mCtx)
        val view:View=layoutInflator.inflate(layoutResId,null)
        val name=view._name
        val password:TextView=view._password
        val module=list[position]
        name.text=module.username
        password.text=module.password
        Log.e("view",view.toString())
        return view
    }
}