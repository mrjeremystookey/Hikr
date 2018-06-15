package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.signup.*
import org.jetbrains.anko.toast

class Signup: AppCompatActivity(), View.OnClickListener{
    var TAG: String = "Signup"

    lateinit var next: Button
    lateinit var createdUser: User

    lateinit var ref: DatabaseReference
    lateinit var userId: String

    lateinit var creationIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        next = findViewById(R.id.bNext)
        next.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        Log.d(TAG, "creating new user...")
        createNewUser()
    }

    fun createNewUser(){
        if(etPassword.text.toString() == (etPasswordConfirm.text.toString())) {
            ref = FirebaseDatabase.getInstance().getReference("Users")
            userId = ref.push().key
            createdUser = User(userId, etUsername.text.toString(), etEmail.text.toString(), etPassword.text.toString())
            ref.child(userId).setValue(createdUser).addOnCompleteListener {
                Log.d(TAG, "user creation successful")
                toast("user creation successful")
                creationIntent = Intent(this, Post::class.java)
                //How to add User extra to an Intent?
                creationIntent.putExtra("createdUser", createdUser.toString())
                startActivity(creationIntent)
            }
        }
    }

}