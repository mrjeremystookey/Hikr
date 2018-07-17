package r.stookey.hikr.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import r.stookey.hikr.model.Post


@Entity(tableName = "createdBy")
data class UserEntity(@PrimaryKey var userID: String?,
                      var username: String?,
                      @ColumnInfo(name = "all_posts")var allPosts: List<Post>?){



    constructor():this(null,null,null)

}


//Will hold the list of user's posts as a List<Post>