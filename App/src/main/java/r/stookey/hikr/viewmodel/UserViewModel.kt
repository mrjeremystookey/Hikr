package r.stookey.hikr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import r.stookey.hikr.Repo
import r.stookey.hikr.db.entity.PostEntity
import r.stookey.hikr.di.Injector
import r.stookey.hikr.model.Post
import javax.inject.Inject

class UserViewModel constructor(var userID: String) : ViewModel() {

    val repo: Repo
    init {
        repo = Injector.get().repo
    }

    private lateinit var mLocation: Location


    fun setLocation(location: Location) {
        mLocation = location
    }

    //TODO Get All Posts by User ID for displaying in the List Fragment
    /*Called when the All Posts fragment is opened or when a now post is added to the DB*/
    fun getAllPostsByUserID(): LiveData<List<PostEntity>> {
        return repo.getAllPostsByUserID(userID)
    }






}