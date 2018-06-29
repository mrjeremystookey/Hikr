package r.stookey.hikr

import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.new_post_fragment.*
import permissions.dispatcher.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.jar.Manifest

class PostFragment(): Fragment(), View.OnClickListener {

    private val TAG = "ProfileFragment"

    private lateinit var messageString: String
    private lateinit var titleString: String
    private lateinit var userID: String
    private lateinit var username: String
    private lateinit var location: Location

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    private lateinit var message: Message

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest




    companion object {
        fun newInstance(userID: String, username: String): PostFragment {
            val args = Bundle()
            args.putString("userID", userID)
            args.putString("username", username)
            val fragment = PostFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.new_post_fragment, container, false)
    }

    override fun onClick(v: View?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO Permissions check for Course and Fine location

        userID = arguments!!.getString("userID")
        username = arguments!!.getString("username")
        Log.d(TAG, userID + username)

    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated(): parent activity fully loaded" )
        etText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                textChanged()

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        etTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                textChanged()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
    }

    private fun textChanged(){
        //TODO a Watcher for the EditText to automatically save what the user_page_fragment has written
        messageString = etText.text.toString()
        titleString = etTitle.text.toString()
        val currentDate = sdf.format(Date())
//        message = Message(titleString, userID, currentDate, )
        //TODO Update message object as the title and text fields change
    }





    private fun uploadText(){

    }

}