package r.stookey.hikr.viewmodel

import android.annotation.TargetApi
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import android.view.View
import r.stookey.hikr.Repo
import r.stookey.hikr.model.Post
import java.time.LocalDateTime
import javax.inject.Inject



/*class PostViewModel(@Inject uid: String, @Inject repo: Repo): ViewModel(){*/
class PostViewModel: ViewModel(){


    private lateinit var mUserID: String
    private lateinit var mPostText: String
    private lateinit var mTitleString: String

    @Inject lateinit var repo: Repo


    lateinit var locationLatLngFormatted: String
    private lateinit var mLocation: Location


    //public functions
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
    private fun createPostForRepo(){
        var mPost = Post(null ,mUserID, mTitleString, getDate(), mPostText, getLocationOfPost())
        repo.addPostFromViewModel(mPost)
    }

    private fun getLocationOfPost(): String{
        var lat = mLocation.latitude
        var lng = mLocation.longitude
        locationLatLngFormatted = " '" + lat.toString() + ", " + lng.toString()+"' "
        return locationLatLngFormatted
    }

    @TargetApi(26)
    private fun getDate(): String{
        val dateTime = LocalDateTime.now().toString()
        return dateTime
    }









}