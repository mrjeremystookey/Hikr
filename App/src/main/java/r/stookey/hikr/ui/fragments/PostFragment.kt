package r.stookey.hikr.ui.fragments

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_new_post.*
import r.stookey.hikr.R

class PostFragment: Fragment(), View.OnClickListener, android.support.v7.widget.Toolbar.OnMenuItemClickListener
{

    private val TAG = "POSTFRAGMENT"

    private var messageString: String = ""
    private var titleString: String = ""
    private lateinit var mUserID: String


    companion object {
        fun newInstance(userID: String) = PostFragment().apply {
            arguments = Bundle().apply {
                putString("userID", userID)
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
        var fabAddPost = view.findViewById<FloatingActionButton>(R.id.fabAddPost)
        fabAddPost.setOnClickListener(this)
        setHasOptionsMenu(true)



        return view
    }


    /**
     * This method will be invoked when a menu item is clicked if the item itself did
     * not already handle the event.
     *
     * @param item [MenuItem] that was clicked
     * @return `true` if the event was handled, `false` otherwise.
     */
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.pin){
            //TODO Inflate Map Pop up icon with Post Info at current location
            Log.d(TAG, "pin clicked")
            if(messageString.isBlank() || titleString.isBlank()){
                val text = "Must have title and post"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(activity, text, duration)
                toast.show()
            }else{
/*
                postViewModel.addPost()
*/
            }
            return true
        }else{
            //TODO Hide Current post to show map
            Log.d(TAG, "map clicked")
            return true
        }
    }

    override fun onClick(v: View?) {
        //TODO Display Submenu for adding audio recording, video, and photos
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
/*
        postViewModel.addPostTextToModelView(messageString, titleString)
*/
    }
}