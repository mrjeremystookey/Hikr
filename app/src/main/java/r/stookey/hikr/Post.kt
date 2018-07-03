package r.stookey.hikr


import android.util.Log
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.firestore.FirebaseFirestore

@IgnoreExtraProperties
class Post {

    private val TAG: String = "MESSAGE"

    var id: String? = null
    var user:String? = null
    var title: String? = null
    var dateCreated: String? = null
    var text: String? = null
    var location: String? = null

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()


    constructor()

    constructor(id: String, user: String ,title: String, dateCreated: String ,text: String, location: String?){
        this.id = id
        this.title = title
        this.text = text
        this.location = location
        this.dateCreated = dateCreated
        this.user = user
    }

    @Exclude
    override fun toString(): String {
        return "Post(id=$id, user=$user, title=$title, dateCreated='$dateCreated', " +
                "text=$text, location=$location)"
    }

    @Exclude
    private fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>()
        result.put("id", id!!)
        result.put("user", user!!)
        result.put("title", title!!)
        result.put("dateCreated", dateCreated!!)
        result.put("text", text!!)
        result.put("location", location!!)
        return result
    }


    fun uploadMessage(){
        //TODO Handle uploading the message to Firebase
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

    private fun deleteMessage(){
        //TODO Remove message from Firebase
    }



}






