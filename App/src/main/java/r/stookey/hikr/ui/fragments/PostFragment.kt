package r.stookey.hikr.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.pawegio.kandroid.d
import com.pawegio.kandroid.textWatcher
import com.pawegio.kandroid.toast
import kotlinx.android.synthetic.main.fragment_new_post.*
import r.stookey.hikr.R
import r.stookey.hikr.viewmodel.PostViewModel
import r.stookey.hikr.viewmodel.ViewModelFactory

class PostFragment: Fragment(), View.OnClickListener, android.support.v7.widget.Toolbar.OnMenuItemClickListener
{

    private val TAG = "PostFragment"

    private lateinit var mUserID: String
    private lateinit var mLocation: String
    private lateinit var viewModel: PostViewModel
    private lateinit var toolbar: Toolbar


    companion object {
        fun newInstance(userID: String/*, location: String*/) = PostFragment().apply {
            arguments = Bundle().apply {
                putString("userID", userID)
//                putString("location", location)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_new_post, container, false)

        arguments?.getString("userID")?.let {
            mUserID = it
        }
        arguments?.getString("location")?.let {
            mLocation = it
        }

        var fabAddPost = view.findViewById<FloatingActionButton>(R.id.fabAddPost)
        fabAddPost.setOnClickListener(this)
        setHasOptionsMenu(true)

        toolbar = activity!!.findViewById(R.id.toolbar)
        toolbar.title = "Create Post"
        toolbar.setTitleTextColor(resources.getColor(R.color.textColor))
        toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
        toolbar.inflateMenu(R.menu.post_app_bar)
        toolbar.setOnMenuItemClickListener(this)

        val factory = ViewModelFactory(mUserID)
        viewModel = ViewModelProviders.of(activity!!, factory).get(PostViewModel::class.java)

        //Testing
        d { viewModel.userID }

        return view
    }


    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        toolbar.title = "Create Post"
    }

    private fun changeToolbar(toolbar: Toolbar) {

    }

    fun setLocation(location: String) {
        mLocation = location
        viewModel.setLocation(location)
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.pin -> {
                d { "pin clicked" }
                if (etBody.text.isBlank() || etTitle.text.isBlank()) {
                    toast("Must have title and body")
                } else {
                    viewModel.addPost()
                }
            }
            R.id.map -> {
                d { "map clicked" }

            }
        }
        return true
    }

    override fun onClick(v: View?) {
        //TODO Display Submenu for adding audio recording, video, and photos
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        d { "onActivityCreated(): parent activity loaded" }
        etBody.textWatcher {
            afterTextChanged {
                viewModel.updateBody(it.toString())
            }
        }
        etTitle.textWatcher {
            afterTextChanged {
                viewModel.updateTitle(it.toString())
            }
        }

    }


}