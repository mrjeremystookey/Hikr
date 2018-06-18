package r.stookey.hikr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.post.*
import org.jetbrains.anko.toast

class Post: AppCompatActivity(), View.OnClickListener {

    val TAG: String = "POST"

    private lateinit var username: String
    private lateinit var email: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)
        Log.d(TAG, "Post activity started")
        toast("Post activity started")
        fabAddPost.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        setUser()
    }

    private fun setUser() {
        //TODO if intent comes from Sign Up or Login In
        if (intent.hasExtra("email") && intent.hasExtra("username")) {
            tvUsername.text = intent.getStringExtra("username")
            toast("logged in as " + tvUsername.text)
            Log.d(TAG, "setUser(): Logged In")
        } else if (intent.hasExtra("newUserEmail") && intent.hasExtra("newUserUsername"))
            tvUsername.text = intent.getStringExtra("newUserUsername")
            toast("new user registered, username is " + tvUsername.text)
            Log.d(TAG, "setUser(): New User Registered")

    }

    override fun onClick(v: View?) {
        //TODO create new post for logged in user
    }

//    data class Post(val id: String, val user: String, val location: Double, val time: Int, var message: String){
//
//    }

}