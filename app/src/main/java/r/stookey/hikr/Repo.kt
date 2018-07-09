package r.stookey.hikr

import android.arch.lifecycle.LiveData
import r.stookey.hikr.model.Post

class Repo(){

    private lateinit var mListLiveData: LiveData<Post>

    fun getAllPostsByUserID(id: String): LiveData<Post>{
        //TODO Get All Posts from either Local Database or Firebase
        //Access the DAO?
        val allPosts: LiveData<Post>
        return allPosts
    }
}