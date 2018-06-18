package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.signup.*
import org.jetbrains.anko.toast

class Signup: AppCompatActivity(), View.OnClickListener{
    var TAG: String = "Signup"

    lateinit var next: Button

    lateinit var creationIntent: Intent

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = firebaseAuth.currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        next = findViewById(R.id.bNext)
        next.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        currentUser = firebaseAuth.currentUser
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "creating new user...")
        createNewUser()
    }


    fun createNewUser(){
        Log.d(TAG, "attemping firebase user account creation")
        if(etPassword.text.toString() == (etPasswordConfirm.text.toString())) {
            creationIntent = Intent(this, Post::class.java)
            firebaseAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString()).addOnCompleteListener {
                if (it.isSuccessful) {
                    currentUser = firebaseAuth.currentUser
                    var profileUpdates: UserProfileChangeRequest.Builder = UserProfileChangeRequest.Builder().setDisplayName(etUsername.text.toString())
                    Log.d(TAG, "createNewUser(): " + etUsername.text.toString())
                    currentUser?.updateProfile(profileUpdates.build())?.addOnCompleteListener {
                        if(it.isSuccessful){
                            Log.d(TAG, "User profile updated with username")
                            creationIntent.putExtra("newUserUsername", currentUser?.displayName)
                            //Email is added to Post Intent
                            creationIntent.putExtra("newUserEmail", currentUser?.email)

                            Log.d(TAG, "createNewUser(): " + currentUser?.email + " "+ currentUser?.displayName)
                            startActivity(creationIntent)
                        }
                        else {
                            Log.d(TAG, "Unable to update user profile")
                        }
                    }
                } else {
                    Log.d(TAG, "createNewUser(): " + it.exception)
                    toast("Unable to create user" + it.exception)
                }
        }
            }
        else{
            toast("Make sure passwords match")
        }

        }
    }


