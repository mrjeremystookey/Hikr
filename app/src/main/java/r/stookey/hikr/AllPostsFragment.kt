package r.stookey.hikr

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class AllPostsFragment: Fragment(), View.OnClickListener{

    private val TAG = "AllPostsFragment"


    companion object {
        fun newInstance() = AllPostsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }

    override fun onClick(v: View?) {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}