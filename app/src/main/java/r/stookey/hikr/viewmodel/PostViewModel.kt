package r.stookey.hikr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import r.stookey.hikr.Repo
import r.stookey.hikr.model.Post

class PostViewModel: ViewModel(){

    private val TAG = "POSTVIEWMODEL"
    private lateinit var userID: String
    private lateinit var mPostText: String
    lateinit var location: String
    private lateinit var  mAllPosts: LiveData<Post>
    private var repo: Repo = Repo()

    fun setUserID(uid: String){
        userID = uid
    }

    fun updateText(text: String){
        mPostText = text
    }

    //TODO Get All Posts by User ID for displaying in the List Fragment
    //TODO Called when the user clicks on the All Messages Fragment
    fun getAllPostsByUserID(): LiveData<Post> {
        return repo.getAllPostsByUserID(userID)
    }


    //TODO Will get the final location right as user clicks pin message in the App Bar
    private fun getLocationOfPost(){

    }







}