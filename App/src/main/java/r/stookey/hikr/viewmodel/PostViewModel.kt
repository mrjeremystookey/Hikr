package r.stookey.hikr.viewmodel

import android.annotation.TargetApi
import android.arch.lifecycle.ViewModel
import r.stookey.hikr.Repo
import r.stookey.hikr.db.entity.PostEntity
import r.stookey.hikr.di.Injector
import java.time.LocalDateTime


//class PostViewModel(@Inject val uid: String, @Inject val repo: Repo): ViewModel(){
class PostViewModel constructor(var userID: String) : ViewModel() {

    private val mRepo: Repo

    init {
        mRepo = Injector.get().repo
    }

    //Post and User Information
    private lateinit var mPostText: String
    private lateinit var mTitleString: String
    private lateinit var mLocation: String


    /*Called in Main Activity once Location is set*/
    fun setLocation(location: String) {
        mLocation = location
    }

    /*Called when text in the Title or Post Body is changed*/
    fun updateTitle(title: String) {
        mTitleString = title
    }

    fun updateBody(body: String) {
        mPostText = body
    }

    fun addPost(){
        createPostForRepo()
    }




    //Internal functions
    //TODO Will be called when the createdBy clicks pin message
    /*Creates the Post object to be sent to the Repo class for further processing*/
    private fun createPostForRepo() {
        var mPost = PostEntity(null, userID, getDateOfPostCreation(), mLocation, mPostText, mTitleString)
        mRepo.addPostFromViewModel(mPost)
    }


    //Utility Functions


    @TargetApi(26)
    private fun getDateOfPostCreation(): String {
        val dateTime = LocalDateTime.now().toString()
        return dateTime
    }


}