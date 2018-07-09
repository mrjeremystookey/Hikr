package r.stookey.hikr.model

import android.arch.lifecycle.LiveData
import android.content.ContentValues.TAG
import android.util.Log
import android.widget.Toast
import com.google.common.cache.Cache
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Singleton

@Singleton
class User(){

    private val TAG = "USER"


    var uid: String? = String()
    var username: String? = String()

    private lateinit var email: String





    private fun deleteUser(){

    }

    private fun changeEmail(){

    }

    private fun changeUsername(){

    }


}




