package r.stookey.hikr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import r.stookey.hikr.db.entity.PostEntity
import r.stookey.hikr.di.Injector

class ListViewModel constructor(var userID: String) : ViewModel() {

    private val repo = Injector.get().repo


    //TODO Get All Posts by User ID for displaying in the List Fragment
    fun getAllPostsByUserID(): LiveData<List<PostEntity>> {
        return repo.getAllPostsByUserID(userID)
    }






}