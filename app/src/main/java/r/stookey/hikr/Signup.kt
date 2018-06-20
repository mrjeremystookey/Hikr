package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.signup.*
import org.jetbrains.anko.toast

class Signup: AppCompatActivity(), View.OnClickListener{
    var TAG: String = "Signup"

    lateinit var next: Button
    lateinit var creationIntent: Intent

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = firebaseAuth.currentUser
    var profileUpdates: UserProfileChangeRequest.Builder = UserProfileChangeRequest.Builder()

    lateinit var email: String
    lateinit var username: String
    lateinit var password: String
    lateinit var passwordConfirm: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        initialize()
    }

    private fun initialize(){
        next = findViewById(R.id.bNext)
        next.setOnClickListener(this)
        username = etUsername.text.toString()
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        passwordConfirm = etPasswordConfirm.text.toString()
    }

    override fun onStart() {
        super.onStart()
        currentUser = firebaseAuth.currentUser
        updateStatus("onStart(): Logged in as " + currentUser?.displayName.toString())
    }

    override fun onClick(v: View?) {
        updateStatus("creating new user")
        checkFields()
    }

    private fun checkFields(){
        updateStatus("checking password requirements...")
        //checks to make sure the fields are full
        if((!TextUtils.isEmpty(email)) && !TextUtils.isEmpty(password)
                && !TextUtils.isEmpty(passwordConfirm) && !TextUtils.isEmpty(username)) {
            //checks to make sure passwords are equal
            if (etPassword.text.toString() == (etPasswordConfirm.text.toString())) {
                createNewUser()
            } else {
                toast("Passwords do not match")
            }
        } else {
            toast("fill out all fields")
        }
    }

    private fun createNewUser(){
        updateStatus("attemping firebase user account creation")
        creationIntent = Intent(this, Post::class.java)
        firebaseAuth.createUserWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                currentUser = firebaseAuth.currentUser
                updateUser()
            } else {
                updateStatus("createNewUser(): " + it.exception)
                toast("Unable to create user")
            }
        }
        }

    private fun updateUser(){
        profileUpdates.setDisplayName(etUsername.text.toString())
        currentUser?.updateProfile(profileUpdates.build())?.addOnCompleteListener {
            if (it.isSuccessful) {
                creationIntent.putExtra("newUserUsername", currentUser?.displayName)
                creationIntent.putExtra("newUserEmail", currentUser?.email)
                startActivity(creationIntent)
                updateStatus("updateUser(): " + currentUser?.email + " " + currentUser?.displayName)
            } else {
                updateStatus("Unable to update user profile")
            }
        }
    }

    fun updateStatus(status: String){
        Log.d(TAG, status)
    }


    }


