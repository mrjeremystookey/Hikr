package r.stookey.hikr.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import r.stookey.hikr.model.Post
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import r.stookey.hikr.db.entity.PostEntity


@Dao
interface PostDAO {


    @Insert
    @OnConflictStrategy
    fun addPostToRoom(post: PostEntity)


}