package r.stookey.hikr.ui.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_entry_point.*
import r.stookey.hikr.R
import r.stookey.hikr.UserPref


class Entry : AppCompatActivity(), View.OnClickListener {

    private val TAG = "Entry"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_point)
        bSignup.setOnClickListener(this)
        bLogin.setOnClickListener(this)
        var user = UserPref()

        if (user.isLoggedIn(this)) {
            val mainIntent = Intent(this, Main::class.java)
            startActivity(mainIntent)
        }

    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.bLogin -> {
                val loginIntent = Intent(this, Login::class.java)
                startActivity(loginIntent)
            }
            R.id.bSignup -> {
                val signUpIntent = Intent(this, Signup::class.java)
                startActivity(signUpIntent)
            }
        }
    }
}