package r.stookey.hikr.viewmodel

import android.annotation.TargetApi
import android.arch.lifecycle.ViewModel
import android.location.Location
import r.stookey.hikr.Repo
import r.stookey.hikr.db.entity.PostEntity
import r.stookey.hikr.di.Injector
import r.stookey.hikr.model.Post
import java.time.LocalDateTime
import javax.inject.Inject


//class PostViewModel(@Inject val uid: String, @Inject val repo: Repo): ViewModel(){
class PostViewModel(var userID: String) : ViewModel() {

    private val mRepo: Repo

    init {
        mRepo = Injector.get().repo
    }

    //Post and User Information
    private lateinit var mPostText: String
    private lateinit var mTitleString: String
    private lateinit var mLocation: Location


    /*Called in Main Activity once Location is set*/
    fun setLocation(location: Location) {
        mLocation = location
    }

    /*Called when text in the Title or Post Body is changed*/
    fun addPostTextToModelView(text: String, title: String) {
        mPostText = text
        mTitleString = title
    }

    fun addPost(){
        createPostForRepo()
    }




    //Internal functions
    //TODO Will be called when the createdBy clicks pin message
    /*Creates the Post object to be sent to the Repo class for further processing*/
    private fun createPostForRepo() {
        var mPost = PostEntity(null, userID, getDateOfPostCreation(), getLocationOfPost(), mPostText, mTitleString)
        mRepo.addPostFromViewModel(mPost)
    }


    //Utility Functions
    private fun getLocationOfPost(): String {
        return " '" + mLocation.latitude.toString() + ", " + mLocation.longitude.toString() + "' "
    }

    @TargetApi(26)
    private fun getDateOfPostCreation(): String {
        val dateTime = LocalDateTime.now().toString()
        return dateTime
    }


}