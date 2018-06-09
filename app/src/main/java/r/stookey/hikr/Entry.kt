package r.stookey.hikr


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import org.jetbrains.anko.toast




class Entry: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry)


        val btnSignUp: Button = findViewById(R.id.bSignup)
        btnSignUp.setOnClickListener(this)
        val btnLogin: Button = findViewById(R.id.bLogin)
        btnLogin.setOnClickListener(this)

    }

    override fun onClick(v: View?){
        when (v?.id) {
            R.id.bLogin -> {
                toast("Login started")
                val loginIntent = Intent(this, Login::class.java)
                startActivity(loginIntent)

            }
            R.id.bSignup -> {
                toast("Signup started")
                val signUpIntent = Intent(this, Signup::class.java)
                startActivity(signUpIntent)
            }
        }
    }
}