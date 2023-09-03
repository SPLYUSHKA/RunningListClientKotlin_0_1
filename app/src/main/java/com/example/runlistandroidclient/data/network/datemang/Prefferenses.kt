package com.example.runlistandroidclient.data.network.datemang

import android.content.Context
import android.content.SharedPreferences
import com.example.runlistandroidclient.data.network.datemang.Const.ConstPreff.PREFERENCE_NAME

class PrefRepository(private val context: Context) {

    private val pref: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()
    
    private fun String.put(useremail: String, userpassword  : String){
            editor.putString(this,useremail)
            editor.putString(this,userpassword)
            editor.commit()
    }
    private fun String.put(token: String){
        editor.putString(this,token)
        editor.commit()
    }
    private  fun String.getUser() = pref.getString(this,"")
    private  fun String.getToken() =pref.getString(this,"")

}