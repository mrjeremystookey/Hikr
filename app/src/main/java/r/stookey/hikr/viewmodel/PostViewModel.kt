package r.stookey.hikr.viewmodel

import android.annotation.TargetApi
import android.arch.lifecycle.ViewModel
import android.location.Location
import r.stookey.hikr.Repo
import r.stookey.hikr.model.Post
import java.time.LocalDateTime
import javax.inject.Inject



//class PostViewModel(@Inject val uid: String, @Inject val repo: Repo): ViewModel(){
class PostViewModel(@Inject var repo: Repo): ViewModel(){


    //Post and User Information
    private lateinit var mUserID: String
    private lateinit var mPostText: String
    private lateinit var mTitleString: String


    lateinit var locationLatLngFormatted: String
    private lateinit var mLocation: Location


    //Public functions
    /*Called in Main Activity*/
    fun setUserID(uid: String){
        mUserID = uid
    }

    /*Called in Main Activity once Location is set*/
    fun setLocation(location:Location){
        mLocation = location
    }

    /*Called when text in the Title or Post Body is changed*/
    fun addPostTextToModelView(text: String, title: String){
        mPostText = text
        mTitleString = title
    }




    //Internal functions
    //TODO Will be called when the createdBy clicks pin message
    /*Creates the Post object to be sent to the Repo class for further processing*/
    private fun createPostForRepo(){
        var mPost = Post(null ,mUserID, mTitleString, getDateOfPostCreation(), mPostText, getLocationOfPost())
        repo.addPostFromViewModel(mPost)
    }




    //Utility Functions
    private fun getLocationOfPost(): String{
        return  " '" + mLocation.latitude.toString() + ", " + mLocation.longitude.toString()+"' "
    }

    @TargetApi(26)
    private fun getDateOfPostCreation(): String{
        val dateTime = LocalDateTime.now().toString()
        return dateTime
    }









}