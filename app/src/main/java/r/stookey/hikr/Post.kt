package r.stookey.hikr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.post.*
import org.jetbrains.anko.toast
import r.stookey.hikr.R.id.fabAddPost
import r.stookey.hikr.R.id.tvUsername

//setting up the bottom nav bar
//https://medium.com/@hitherejoe/exploring-the-android-design-support-library-bottom-navigation-drawer-548de699e8e0

class Post: AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "POST"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)
        Log.d(TAG, "Post activity started")
        fabAddPost.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        determineUser()
    }

    private fun determineUser() {
        if (intent.hasExtra("email") && intent.hasExtra("username")) {
            tvUsername.text = intent.getStringExtra("username")
            Log.d(TAG, "determineUser(): logged in as " + tvUsername.text)
        } else if (intent.hasExtra("newUserEmail") && intent.hasExtra("newUserUsername")) {
            tvUsername.text = intent.getStringExtra("newUserUsername")
            Log.d(TAG, "determineUser(): new user registered, username is " + tvUsername.text)
        }
    }

    override fun onClick(v: View?) {
        //TODO create new post for logged in user
    }

//    data class Post(val id: String, val user: String, val location: Double, val time: Int, var message: String){
//
//    }

}