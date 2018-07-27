package r.stookey.hikr.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@ForeignKey(entity = UserEntity::class, parentColumns = ["userID"], childColumns = ["created_by"])
@Entity(tableName = "posts")
data class PostEntity(@PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name = "post_id") var postID: Long?,
                      @ColumnInfo(name = "created_by") var createdBy: String?,
                      @ColumnInfo(name = "date") var date: String?,
                      @ColumnInfo(name = "location") var location: String?,
                      @ColumnInfo(name = "text") var text: String?,
                      @ColumnInfo(name = "title") var title: String?)


