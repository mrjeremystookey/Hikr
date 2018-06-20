package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.signup.*
import org.jetbrains.anko.toast
import studios.codelight.smartloginlibrary.*
import studios.codelight.smartloginlibrary.users.SmartUser
import studios.codelight.smartloginlibrary.util.SmartLoginException

//Login in system
//https://github.com/CodelightStudios/Android-Smart-Login/wiki/Configuring-SmartLoginCallbacks


class Login: AppCompatActivity(), View.OnClickListener, SmartLoginCallbacks {
    val TAG: String = "Login"

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
        setContentView(R.layout.login)
        bLogin.setOnClickListener(this)
    }


    override fun onStart() {
        super.onStart()
        firebaseUser = firebaseAuth?.currentUser
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "onClick(): Attemping login...")
        email = etEmailLogin?.text.toString()
        password = etPasswordLogin?.text.toString()
        smartLogin.login(config)
    }

    override fun onLoginSuccess(user: SmartUser?) {
        if(credentialCheck){
            loginIntent = Intent(this, Post::class.java)
            loginIntent.putExtra("email", user?.email)
            loginIntent.putExtra("username", user!!.username)
            startActivity(loginIntent)
        }
    }

    override fun doCustomLogin(): SmartUser {
        user.username = login()?.displayName
        user.email = login()?.email
        return user
    }

    private fun login(): FirebaseUser? {
        if (email == "" || password == "") {
            toast("Enter an email and a password")
            firebaseUser = null
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseUser = firebaseAuth.currentUser
                    updateStatus("Login successful, firebase user = " + firebaseUser?.displayName.toString())
                    credentialCheck = true
                } else if (!it.isSuccessful) {
                    Log.e(TAG, "onComplete: Failed = " + it.exception?.message)
                    firebaseUser = null
                }
            }
        }
        updateStatus("login(): firebase user = " + firebaseUser.toString())
        return firebaseUser
    }



    override fun doCustomSignup(): SmartUser {
        return SmartUser()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        smartLogin.onActivityResult(requestCode, resultCode, data, config)
    }

    fun updateStatus(status: String){
        Log.d(TAG, status)
    }

    override fun onLoginFailure(e: SmartLoginException?) {
    }


}