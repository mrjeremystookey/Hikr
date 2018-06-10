package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.toast

class Signup: AppCompatActivity(), View.OnClickListener{
    var TAG: String = "Signup"

    lateinit var username: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var passwordConfirm: EditText
    lateinit var next: Button
    lateinit var createdUser: User

    lateinit var ref: DatabaseReference
    lateinit var userId: String

    lateinit var creationIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        username = findViewById(R.id.etUsername)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        passwordConfirm = findViewById(R.id.etPasswordConfirm)

        next = findViewById(R.id.bNext)
        next.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "creating new user...")
        createNewUser()
    }

    fun createNewUser(){
        if(password.text.toString() == (passwordConfirm.text.toString())) {
            ref = FirebaseDatabase.getInstance().getReference("Users")
            userId = ref.push().key
            createdUser = User(userId, username.text.toString(), email.text.toString(), password.text.toString())
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