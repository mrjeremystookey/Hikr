package r.stookey.hikr.ui.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login_flow.*
import r.stookey.hikr.viewmodel.UserViewModel
import r.stookey.hikr.R


class Login : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "LOGIN"


    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseUser: FirebaseUser? = firebaseAuth.currentUser

    private lateinit var loginIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_flow)
        bLogin.setOnClickListener(this)
        loginIntent = Intent(this, Main::class.java)

    }

    override fun onStart() {
        super.onStart()
        if (progressBar.visibility == VISIBLE)
            progressBar.visibility = INVISIBLE
        firebaseUser = firebaseAuth.currentUser
    }

    override fun onClick(v: View?) {
        if (etEmailLogin.text.isBlank() || etPasswordLogin.text.isBlank()) {
            tvIncorrectEmailPassword.visibility = VISIBLE
            return
        }
        login()
    }

    private fun login() {
        tvIncorrectEmailPassword.visibility = INVISIBLE
        progressBar.visibility = VISIBLE

        firebaseAuth.signInWithEmailAndPassword(etEmailLogin.text.toString(), etPasswordLogin.text.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                loginIntent.putExtra("userID", firebaseAuth.currentUser!!.uid)
                loginIntent.putExtra("username", firebaseAuth.currentUser!!.displayName)
                startActivity(loginIntent)
            } else {
                tvIncorrectEmailPassword.text = "Email address or password is incorrect"
                tvIncorrectEmailPassword.visibility = VISIBLE
                progressBar.visibility = View.INVISIBLE
                Log.d(TAG, "onComplete: Failed)")
            }
        }


    }


}