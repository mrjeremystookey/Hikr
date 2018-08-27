package r.stookey.hikr.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import r.stookey.hikr.db.entity.PostEntity

@Dao
interface UserDAO {


    //Returns a List of Post objects to the Repo which bundles it in LiveData
    @Query("SELECT * FROM posts where created_by = :userID")
    fun getUserPostsByID(userID: String): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateListOfUserPosts(allPosts: List<PostEntity>)


}