package r.stookey.hikr

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.new_post_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class PostFragment(): Fragment(), View.OnClickListener {

    private val TAG = "ProfileFragment"

    private lateinit var messageString: String
    private lateinit var titleString: String
    private lateinit var userID: String
    private lateinit var username: String
    private lateinit var location: String

    val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
    private lateinit var post: Post




    companion object {
        fun newInstance(userID: String, username: String, location: String): PostFragment {
            val args = Bundle()
            args.putString("userID", userID)
            args.putString("username", username)
            args.putString("location", location)
            val fragment = PostFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.new_post_fragment, container, false)
        val button: FloatingActionButton = view.findViewById(R.id.fabAddPost)
        button.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "onClick(): button clicked")
        post.uploadMessage()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        location = arguments!!.getString("location")
        userID = arguments!!.getString("userID")
        username = arguments!!.getString("username")
        Log.d(TAG, userID + username + location)

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
        messageString = etText.text.toString()
        titleString = etTitle.text.toString()
        val currentDate = sdf.format(Date())
        post = Post("1" , userID, titleString, currentDate, messageString, location)
        Log.d(TAG, "textChanged(): " + post.toString())
    }





    private fun uploadText(){

    }

}