package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login_flow.*
import studios.codelight.smartloginlibrary.*
import studios.codelight.smartloginlibrary.users.SmartUser
import studios.codelight.smartloginlibrary.util.SmartLoginException

//Login in system
//https://github.com/CodelightStudios/Android-Smart-Login/wiki/Configuring-SmartLoginCallbacks


class Login: AppCompatActivity(), View.OnClickListener, SmartLoginCallbacks {
    private val TAG: String = "LOGIN"

    private val smartLogin: SmartLogin = SmartLoginFactory.build(LoginType.CustomLogin)
    private val config: SmartLoginConfig = SmartLoginConfig(this, this)
    private var user: SmartUser = SmartUser()

    private lateinit var loginIntent: Intent

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseUser: FirebaseUser? = firebaseAuth.currentUser

    private lateinit var password: String
    private lateinit var email: String
    private var credentialCheck: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_flow)
        bLogin.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        firebaseUser = firebaseAuth.currentUser
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "onClick(): Attemping login_flow...")
        email = etEmailLogin?.text.toString()
        password = etPasswordLogin?.text.toString()
        smartLogin.login(config)
    }

    override fun doCustomLogin(): SmartUser {
        if(password.isBlank() || email.isBlank()){
            tvIncorrectEmailPassword.text = "enter an email address and password"
            tvIncorrectEmailPassword.visibility = VISIBLE
        } else{
            user.username = login()?.displayName
            user.email = login()?.email
            user.userId = login()?.uid
        }
        return user
    }

    private fun login(): FirebaseUser? {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseUser = firebaseAuth.currentUser
                updateStatus("Login successful, firebase user_page_fragment = " + firebaseUser?.displayName.toString())
                credentialCheck = true
            } else if (!it.isSuccessful) {
                tvIncorrectEmailPassword.text = "incorrect email and/or password"
                tvIncorrectEmailPassword.visibility = VISIBLE
                Log.e(TAG, "onComplete: Failed = " + it.exception?.message)
                firebaseUser = null
            }
        }
        updateStatus("login_flow(): firebase user_page_fragment = " + firebaseUser.toString())
        return firebaseUser
    }

    override fun onLoginSuccess(user: SmartUser?) {
        if (firebaseUser == null) {
            Log.d(TAG, "onLoginSuccess(): something went wrong")
        } else {
            if (credentialCheck) {
                loginIntent = Intent(this, Main::class.java)
                loginIntent.putExtra("email", user!!.email)
                loginIntent.putExtra("username", user.username)
                loginIntent.putExtra("userID", user.userId)
                startActivity(loginIntent)
            }
        }
    }



    private fun updateStatus(status: String){
        Log.d(TAG, status)
    }

    override fun doCustomSignup(): SmartUser {
        return SmartUser()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        smartLogin.onActivityResult(requestCode, resultCode, data, config)
    }

    override fun onLoginFailure(e: SmartLoginException?) {
    }

}