package r.stookey.hikr.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.register_flow.*
import org.jetbrains.anko.toast
import r.stookey.hikr.R

class Signup: AppCompatActivity(), View.OnClickListener{
    private var TAG: String = "SIGNUP"

    private lateinit var next: Button
    private lateinit var creationIntent: Intent

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = firebaseAuth.currentUser
    private var profileUpdates: UserProfileChangeRequest.Builder = UserProfileChangeRequest.Builder()

    private lateinit var email: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var passwordConfirm: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_flow)

        next = findViewById(R.id.bNext)
        next.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        currentUser = firebaseAuth.currentUser
        updateStatus("onStart(): Logged in as " + currentUser?.displayName.toString())
    }

    override fun onClick(v: View?) {
        getFields()
    }

    private fun getFields(){
        updateStatus("getting field strings")
        username = etUsername.text.toString()
        email = etEmail.text.toString()
        password = etPassword.text.toString()
        passwordConfirm = etPasswordConfirm.text.toString()
        checkFields()
    }

    private fun checkFields(){
        updateStatus("checking password requirements...")
        //checks to make sure the fields are full
        if(email.isBlank() || username.isBlank() || passwordConfirm.isBlank() || password.isBlank()) {
            //checks to make sure passwords are equal
            toast("fill out all fields")
        } else {
            if(password == passwordConfirm){
                createNewUser()
            }
        }
    }

    private fun createNewUser(){
        updateStatus("attempting firebase user_page_fragment account creation")
        creationIntent = Intent(this, Main::class.java)
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                currentUser = firebaseAuth.currentUser
                updateUser()
            } else {
                updateStatus("createNewUser(): " + it.exception)
                toast("Unable to create user_page_fragment")
            }
        }
        }

    private fun updateUser(){
        updateStatus("updating user_page_fragment info")
        profileUpdates.setDisplayName(etUsername.text.toString())
        currentUser?.updateProfile(profileUpdates.build())?.addOnCompleteListener {
            if (it.isSuccessful) {
                updateStatus("updateUser(): " + currentUser?.email + " " + currentUser?.displayName)
                launchPost()
            } else {
                updateStatus("Unable to update user_page_fragment profile")
            }
        }
    }

    private fun launchPost(){
        updateStatus("launching Post activity as logged in user_page_fragment")
        creationIntent.putExtra("username", currentUser?.displayName)
        creationIntent.putExtra("email", currentUser?.email)
        creationIntent.putExtra("userID", currentUser?.uid)
        creationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(creationIntent)
    }

    private fun updateStatus(status: String){
        Log.d(TAG, status)
    }


    }


