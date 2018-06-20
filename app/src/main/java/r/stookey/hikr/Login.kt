package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*
import org.jetbrains.anko.toast
import studios.codelight.smartloginlibrary.*
import studios.codelight.smartloginlibrary.users.SmartUser
import studios.codelight.smartloginlibrary.util.SmartLoginException

//Login in system
//https://github.com/CodelightStudios/Android-Smart-Login/wiki/Configuring-SmartLoginCallbacks


class Login: AppCompatActivity(), View.OnClickListener, SmartLoginCallbacks {
    val TAG: String = "Login"

    val smartLogin: SmartLogin = SmartLoginFactory.build(LoginType.CustomLogin)
    val config: SmartLoginConfig = SmartLoginConfig(this, this)
    var user: SmartUser = SmartUser()

    lateinit var loginIntent: Intent

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseUser: FirebaseUser? = firebaseAuth.currentUser


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        bLogin.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        firebaseUser = firebaseAuth.currentUser
    }

    override fun onClick(v: View?) {
        smartLogin.login(config)
    }

    override fun doCustomSignup(): SmartUser {
        return SmartUser()
    }

    override fun onLoginSuccess(user: SmartUser?) {
        loginIntent = Intent(this, Post::class.java)
        loginIntent.putExtra("email", user?.email)
        loginIntent.putExtra("username", user!!.username)
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(loginIntent)
    }

    override fun onLoginFailure(e: SmartLoginException?) {
        toast("Login failed. Password or Email is incorrect")
    }

    override fun doCustomLogin(): SmartUser {
        user.username = login()!!.displayName
        user.email = login()!!.email
        updateStatus(user.username.toString() + " " + user.email.toString())
        return user
    }

    private fun login(): FirebaseUser? {
        firebaseAuth.signInWithEmailAndPassword(etEmail.text.toString(), etPassword.text.toString()).addOnCompleteListener {
            if(it.isSuccessful){
                updateStatus("Login successful, logged in as " + firebaseUser!!.displayName.toString())
                firebaseUser = firebaseAuth.currentUser
            } else{
                updateStatus("Unable to login, firebase sign in failed")
                toast("Unable to login, check email and password")
            }
        }
        updateStatus(firebaseUser.toString())
        return firebaseUser
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        smartLogin.onActivityResult(requestCode, resultCode, data, config)
    }

    fun updateStatus(status: String){
        Log.d(TAG, status)
    }

}