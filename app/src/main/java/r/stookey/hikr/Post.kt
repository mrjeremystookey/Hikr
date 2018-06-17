package r.stookey.hikr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.post.*
import org.jetbrains.anko.toast

class Post: AppCompatActivity(), View.OnClickListener {

    val TAG: String = "POST"

    private lateinit var user: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)
        Log.d(TAG, "Post activity started")
        toast("Post activity started")
        fabAddPost.setOnClickListener(this)
    }

    private fun setUser(){
        //TODO if intent comes from Sign Up or Login In
        if(intent.)
        user = intent.getStringExtra("user")
        tvUsername.text = user
    }

    override fun onClick(v: View?) {
        setUser()
        toast("setting the user")
    }

//    data class Post(val id: String, val user: String, val location: Double, val time: Int, var message: String){
//
//    }

}