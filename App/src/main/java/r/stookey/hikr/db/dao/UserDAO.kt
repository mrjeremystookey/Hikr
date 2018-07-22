package r.stookey.hikr.db.dao

import android.arch.persistence.room.*
import r.stookey.hikr.model.Post

@Dao
interface UserDAO {


    //Returns a List of Post objects to the Repo which bundles it in LiveData
    @Query("SELECT * FROM posts where created_by = :userID")
    fun getUserPostsByID(userID: String): List<Post>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateListOfUserPosts(allPosts: List<Post>)


}