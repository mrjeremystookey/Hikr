package r.stookey.hikr.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import r.stookey.hikr.db.entity.PostEntity


@Dao
interface PostDAO {


    @Insert
    @OnConflictStrategy
    fun addPostToRoom(post: PostEntity)


}