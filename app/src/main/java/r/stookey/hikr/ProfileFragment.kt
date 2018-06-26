package r.stookey.hikr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_page_fragment.*

class ProfileFragment: Fragment(), View.OnClickListener {

    private val TAG = "ProfileFragment"

    private lateinit var userID: String
    private lateinit var email: String

    companion object {
        fun newInstance(userID: String, email: String) =
                ProfileFragment().apply{
                    arguments = Bundle().apply{
                        putString("userID", userID)
                        putString("email", email)
                    }
                }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_page_fragment, container, false)
    }

    override fun onClick(v: View?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            userID = it.getString("userID")
            email = it.getString("email")
        }
        Log.d(TAG, userID + " " + email)
        tvUsername?.text = email
    }



}