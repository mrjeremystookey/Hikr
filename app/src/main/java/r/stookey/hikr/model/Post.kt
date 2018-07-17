package r.stookey.hikr.model


import android.util.Log
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


@IgnoreExtraProperties
class Post {

    private val TAG: String = "MESSAGE"

    lateinit var createdBy:String
    lateinit var title: String
    lateinit var dateCreated: String
    lateinit var text: String
    lateinit var location: String
    lateinit var id: String

    private val filename = "temp_post"

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    constructor()

    constructor(id: String?, createdBy: String, title: String, dateCreated: String, text: String, location: String){
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



    fun uploadPost(){
        val messages = toMap()
        db.collection("Messages")
                .document(title!!)
                .set(messages)
                .addOnSuccessListener {
                    Log.d(TAG, "uploadMessage(): message upload successful")
                }
                .addOnFailureListener {
                    Log.d(TAG, "uploadMessage(): message upload failure")
                }
    }

    fun cachePost(){
        //TODO Save messages to RoomDatabase as they are typed
        val tempPost = createTempFile(filename,null, null)

    }

    fun deletePost(){

    }

    fun editPost(){

    }


    fun pinPost(){

    }

    private fun getLocation(){

    }



}






