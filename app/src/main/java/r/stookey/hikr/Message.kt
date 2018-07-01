package r.stookey.hikr

import android.graphics.Bitmap
import android.location.Location
import android.provider.MediaStore
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Message {

    var id: String? = null
    var user:String? = null
    var title: String? = null
    var text: String? = null
    var photo: Bitmap? = null
    var video: MediaStore.Video? = null
    var location: String? = null


    constructor(){
    }

    constructor(id: String, user: String ,title: String, text: String,
                photo: Bitmap, video: MediaStore.Video, location: String){
        this.id = id
        this.title = title
        this.text = text
        this.photo = photo
        this.video = video
        this.location = location

    }

}






