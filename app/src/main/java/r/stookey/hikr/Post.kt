package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.post.*
import org.jetbrains.anko.toast

class Post: AppCompatActivity() {

    val TAG: String = "POST"

    lateinit var createdUser: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.post)
        Log.d(TAG, "Post activity started")
        toast("Post activity started")
        createdUser = intent.getStringExtra("createdUser")
        Log.d(TAG, createdUser)
        //tvUsername.text = createdUser

    }
}