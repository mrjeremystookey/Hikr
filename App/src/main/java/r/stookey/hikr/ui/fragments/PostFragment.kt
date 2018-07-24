package r.stookey.hikr.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import kotlinx.android.synthetic.main.new_post_fragment.*
import r.stookey.hikr.viewmodel.PostViewModel
import r.stookey.hikr.viewmodel.UserViewModel
import r.stookey.hikr.R
import r.stookey.hikr.di.DaggerHikrComponent
import r.stookey.hikr.di.HikrComponent
import r.stookey.hikr.di.modules.StorageModule
import r.stookey.hikr.viewmodel.ViewModelFactory

class PostFragment: Fragment(), View.OnClickListener
{

    private val TAG = "POSTFRAGMENT"

    private lateinit var messageString: String
    private lateinit var titleString: String
    private lateinit var mUserID: Any
    private lateinit var mLocation: Any


    private val userViewModel by lazy {
        ViewModelProviders.of(activity!!).get(UserViewModel::class.java)
    }
    private val postViewModel by lazy {
        ViewModelProviders.of(activity!!).get(PostViewModel::class.java)
    }


    //Creates PostFragment object with Location and UID arguments
    companion object {
        fun newInstance() = PostFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.new_post_fragment, container, false)
        val fabAddPost: FloatingActionButton = view.findViewById(R.id.fabAddPost)
        fabAddPost.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        //TODO Display Submenu for adding audio recording, video, and photos
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.post_app_bar, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.pin -> {

            }
            R.id.menu_map -> {
                //TODO Show and hide the PostFragment to display the MapView with messages
            }
        }
        return super.onOptionsItemSelected(item)
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
        postViewModel.addPostTextToModelView(messageString, titleString)
    }




}