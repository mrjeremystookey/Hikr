package r.stookey.hikr.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "createdBy")
data class UserEntity(
        @PrimaryKey var userID: String,
                      var username: String?,
                      @ColumnInfo(name = "all_posts")
                      var allPosts: String)



