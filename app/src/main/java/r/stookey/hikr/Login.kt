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
import org.jetbrains.anko.custom.onUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread


class Login: AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "LOGIN"



    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    private lateinit var user: User

    private lateinit var loginIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_flow)
        bLogin.setOnClickListener(this)
        loginIntent= Intent(this, Main::class.java)

    }

    override fun onStart() {
        super.onStart()
        firebaseUser = firebaseAuth.currentUser
    }

    override fun onClick(v: View?) {
        if(etEmailLogin.text.isBlank() || etPasswordLogin.text.isBlank()){
            tvIncorrectEmailPassword.visibility = VISIBLE
            return
        }
        Log.d(TAG, "onClick(): Attemping login_flow...")
        login()
    }

    private fun login() {

        doAsync {
            firebaseAuth.signInWithEmailAndPassword(etEmailLogin.text.toString(), etPasswordLogin.text.toString()).addOnCompleteListener {
            if (it.isSuccessful) {
                firebaseUser = firebaseAuth.currentUser
                user= User(firebaseUser!!.uid, firebaseUser!!.email, firebaseUser!!.displayName)
                loginIntent.putExtra("email", user!!.email)
                loginIntent.putExtra("username", user.username)
                loginIntent.putExtra("userID", user.uid)
                startActivity(loginIntent)
            } else if (!it.isSuccessful) {
                tvIncorrectEmailPassword.visibility = VISIBLE
                Log.e(TAG, "onComplete: Failed = " + it.exception?.message)
                firebaseUser = null
            }
        }
            runOnUiThread {
                progressBar.visibility = View.VISIBLE
            }
        }
        updateStatus("login_flow(): firebase user_page_fragment = " + firebaseUser.toString())
    }

    private fun updateStatus(status: String){
        Log.d(TAG, status)
    }




}