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

class PostFragment(): Fragment(), View.OnClickListener {

    private val TAG = "POSTFRAGMENT"

    private lateinit var messageString: String
    private lateinit var titleString: String


    private var userViewModel: UserViewModel = UserViewModel()
    private var postViewModel: PostViewModel = PostViewModel()



    companion object {

        fun newInstance(): PostFragment = PostFragment()
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


    //TODO Show Menu App Bar
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.post_app_bar, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    //Todo Minimize the typed message or pin message to map
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            R.id.pin -> {

            }
            R.id.menu_map -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = ViewModelProviders.of(activity!!).get(userViewModel::class.java)
        postViewModel = ViewModelProviders.of(activity!!).get(postViewModel::class.java)
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
        postViewModel.updateText(messageString)
    }




}