package r.stookey.hikr.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user_page_fragment.*
import r.stookey.hikr.viewmodel.UserViewModel
import r.stookey.hikr.R

class ProfileFragment: Fragment(), View.OnClickListener {


    /*private val userViewModel by lazy{
        ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
    }*/


    private val TAG = "ProfileFragment"

    private lateinit var userID: String
    private lateinit var email: String

    companion object {

        fun newInstance(): ProfileFragment = ProfileFragment()

//        fun newInstance(userID: String, email: String) =
//                ProfileFragment().apply{
//                    arguments = Bundle().apply{
//                        putString("userID", userID)
//                        putString("email", email)
//                    }
//                }
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

        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)


        Log.d(TAG, userID + " " + email)
        tvUsername?.text = email



    }



}