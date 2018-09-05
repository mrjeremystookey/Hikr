package r.stookey.hikr.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import r.stookey.hikr.R
import r.stookey.hikr.UserPref

class ProfileFragment : Fragment(), View.OnClickListener {


    /*private val userViewModel by lazy{
        ViewModelProviders.of(activity!!).get(ListViewModel::class.java)
    }*/


    private val TAG = "ProfileFragment"
    private lateinit var userID: String
    private val user = UserPref()

    companion object {
        fun newInstance(userID: String): ProfileFragment {
            var profileFragment = ProfileFragment()
            var args = Bundle()
            args.putString("userID", userID)
            profileFragment.arguments = args
            return profileFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_page, container, false)
    }

    override fun onClick(v: View?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            userID = it.getString("userID")
        }

        //TODO Logout Functionality
        /*val tvLogout = view!!.findViewById<TextView>(R.id.tvLogOut)
        tvLogout.setOnClickListener {
            user.setLoggedIn(activity!!, false)
            val logoutIntent = Intent(activity!!, Entry::class.java)
            startActivity(logoutIntent)
        }*/


    }


}