package r.stookey.hikr.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_login.*
import r.stookey.hikr.R
import r.stookey.hikr.UserPref
import r.stookey.hikr.di.Injector


class Login : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "LOGIN"
    private val firebaseAuth = Injector.get().firebaseAuth
    private lateinit var loginIntent: Intent
    private val prefsFilename = "com.stookey.hikr.prefs"
    private var sharedPreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bLogin.setOnClickListener(this)
        loginIntent = Intent(this, Main::class.java)
        sharedPreferences = this.getSharedPreferences(prefsFilename, 0)

    }

    override fun onStart() {
        super.onStart()
        if (progressBar.visibility == VISIBLE)
            progressBar.visibility = INVISIBLE
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
                var user = firebaseAuth.currentUser!!
                loginIntent.putExtra("userID", user.uid)
                loginIntent.putExtra("username", user.displayName)
                var userPref = UserPref()
                userPref.setLoggedIn(this, true)
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