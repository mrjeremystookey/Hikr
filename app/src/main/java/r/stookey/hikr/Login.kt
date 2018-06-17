package r.stookey.hikr

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.login.*
import org.jetbrains.anko.toast
import studios.codelight.smartloginlibrary.*
import studios.codelight.smartloginlibrary.users.SmartUser
import studios.codelight.smartloginlibrary.util.SmartLoginException

class Login: AppCompatActivity(), View.OnClickListener, SmartLoginCallbacks {
    val TAG: String = "Login"

    val smartLogin: SmartLogin = SmartLoginFactory.build(LoginType.CustomLogin)
    val config: SmartLoginConfig = SmartLoginConfig(this, this)
    var user: SmartUser = SmartUser()

    lateinit var loginIntent: Intent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        bLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        smartLogin.login(config)
    }

    override fun doCustomSignup(): SmartUser {
        return SmartUser()
    }

    override fun onLoginSuccess(user: SmartUser?) {
        loginIntent = Intent(this, Post::class.java)
        loginIntent.putExtra("user", user.toString())
        Log.d(TAG, "onLoginSuccess: "+ user.toString())
        startActivity(loginIntent)

    }

    override fun onLoginFailure(e: SmartLoginException?) {
        toast("Login failed. Password or Email is incorrect")
    }

    override fun doCustomLogin(): SmartUser {
        //Todo check firebase database for user information and accept or deny login
        //where email equals etEmail if password equals etPassword
        //set user email to etEmail
        user.email = etEmail.text.toString()
        user.username = etPassword.text.toString()
        return user
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        smartLogin.onActivityResult(requestCode, resultCode, data, config)

    }
}