package r.stookey.hikr.ui.activities


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.entry_point.*
import r.stookey.hikr.R


class Entry : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.entry_point)
        bSignup.setOnClickListener(this)
        bLogin.setOnClickListener(this)
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