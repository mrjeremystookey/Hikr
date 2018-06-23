package r.stookey.hikr

import android.app.Fragment
import android.app.FragmentManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.post_fragment.*
import org.jetbrains.anko.toast

class PostFragment: Fragment() {

    private val TAG = "PostFragment"

    private lateinit var messageText: String

    companion object {
        fun newInstance() = PostFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.post_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
    }


    //When Parent activity is completed
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated(): parent activity fully loaded" )
    }



    //TODO a Watcher for the EditText to automatically save what the user has written
    fun getText(): String{
        messageText = "the button has been clicked"
        Log.d(TAG, "getText(): " + messageText)
        return messageText
    }

    //TODO When Action Button is pressed, three options for Video, Audio, and Photo appear as subitems


    //TODO Upload the string from the edit text to firebase DB using the UID as the primary key




}