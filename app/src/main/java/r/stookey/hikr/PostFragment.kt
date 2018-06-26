package r.stookey.hikr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.new_post_fragment.*

class PostFragment(): Fragment(), View.OnClickListener {

    private val TAG = "ProfileFragment"

    private lateinit var messageString: String
    private lateinit var titleString: String
    private lateinit var userID: String
    private lateinit var username: String

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
        //TODO Update message object as the title and text fields change
    }


    private fun uploadText(){

    }
}