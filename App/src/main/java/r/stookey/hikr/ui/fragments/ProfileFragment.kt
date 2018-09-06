package r.stookey.hikr.ui.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import r.stookey.hikr.R
import r.stookey.hikr.UserPref

class ProfileFragment : Fragment(), View.OnClickListener, Toolbar.OnMenuItemClickListener {


    /*private val userViewModel by lazy{
        ViewModelProviders.of(activity!!).get(ListViewModel::class.java)
    }*/


    private val TAG = "ProfileFragment"
    private lateinit var userID: String
    private val user = UserPref()
    private lateinit var toolbar: Toolbar

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
        val view = inflater!!.inflate(R.layout.fragment_user_page, container, false)
        arguments?.getString("userID")?.let {
            userID = it
        }
        setHasOptionsMenu(true)

        toolbar = activity!!.findViewById(R.id.toolbar)
        toolbar.title = "User"
        toolbar.setTitleTextColor(resources.getColor(R.color.textColor))
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        toolbar.inflateMenu(R.menu.user_app_bar)
        toolbar.setOnMenuItemClickListener(this)

        return view
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        toolbar.title = "User"
        super.onResume()
    }

    override fun onClick(v: View?) {

    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //TODO Logout Functionality
        /*val tvLogout = view!!.findViewById<TextView>(R.id.tvLogOut)
        tvLogout.setOnClickListener {
            user.setLoggedIn(activity!!, false)
            val logoutIntent = Intent(activity!!, Entry::class.java)
            startActivity(logoutIntent)
        }*/


    }


}