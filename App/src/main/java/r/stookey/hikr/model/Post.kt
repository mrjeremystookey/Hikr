package r.stookey.hikr.model


import android.arch.persistence.room.Ignore
import android.util.Log
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


@IgnoreExtraProperties
class Post {


    @Ignore
    private val TAG: String = "MESSAGE"

    lateinit var createdBy: String
    lateinit var title: String
    lateinit var dateCreated: String
    lateinit var text: String
    lateinit var location: String
    lateinit var id: String

    constructor()

    @Ignore
    constructor(id: String?, createdBy: String, title: String, dateCreated: String, text: String, location: String) {
        this.title = title
        this.text = text
        this.location = location
        this.dateCreated = dateCreated
        this.createdBy = createdBy
    }

    @Exclude
    override fun toString(): String {
        return "Post(createdBy=$createdBy, title=$title, dateCreated='$dateCreated', " +
                "text=$text, location=$location)"
    }

    @Exclude
    private fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("createdBy", createdBy)
        result.put("title", title)
        result.put("dateCreated", dateCreated)
        result.put("text", text)
        result.put("location", location)
        return result
    }


}






