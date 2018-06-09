package r.stookey.hikr

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText

class Signup: AppCompatActivity(), View.OnClickListener{
    var TAG: String = "Signup"

    lateinit var username: EditText
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var passwordConfirm: EditText
    lateinit var next: Button
    lateinit var user: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        username = findViewById(R.id.etUsername)
        email = findViewById(R.id.etEmail)
        password = findViewById(R.id.etPassword)
        passwordConfirm = findViewById(R.id.etPasswordConfirm)

        next = findViewById(R.id.bNext)
        next.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d(TAG, username.text.toString())

        if(password.text.toString() == (passwordConfirm.text.toString())) {
            user = User(username.text.toString(), email.text.toString(), password.text.toString())
            Log.d(TAG, user.toString())
        }




    }
}