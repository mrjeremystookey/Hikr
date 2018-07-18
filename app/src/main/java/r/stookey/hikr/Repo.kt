package r.stookey.hikr

import android.arch.lifecycle.LiveData
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import r.stookey.hikr.db.dao.PostDAO
import r.stookey.hikr.db.dao.UserDAO
import r.stookey.hikr.model.Post
import javax.inject.Inject

class Repo(@Inject var userDAO: UserDAO,
           @Inject var postDAO: PostDAO,
           @Inject var firestoreDB: FirebaseFirestore) {

    private val TAG = "REPO"

    private lateinit var mListLiveData: LiveData<List<Post>>
    private lateinit var mList: List<Post>
    private lateinit var mUID: String
    private lateinit var mPost: Post


    /*Public funtion to grab List of Post objects from either local cache, Room or network storage, Firebase*/
    fun getAllPostsByUserID(uid: String): LiveData<List<Post>>{
        mUID = uid
        if(checkRoomForCache()){
            return mListLiveData
        }
        else {
            //Todo make function wait before returning due to network request
            getAllPostsByUserIDFromFireStore()
            return mListLiveData
        }

    }

    /*Checks Room Database for local cache of List of Posts
      if true sets LiveData value and returns
    */
    private fun checkRoomForCache(): Boolean{
        var cacheCheck = false
        if(!userDAO.getUserPostsByID(mUID).isEmpty()){
            cacheCheck = true
            mList = userDAO.getUserPostsByID(mUID)
            //Set LiveData value to value retrieved from the DAO (Room Database)
            mListLiveData.value == mList
        }
        return cacheCheck
    }

    /*
    Sets the mListLiveData to the List of Post objects returned from FireStore
    */
    private fun getAllPostsByUserIDFromFireStore() {
        val collectionReference = firestoreDB.collection("Messages")
        val query: Query = collectionReference.whereEqualTo("created_by", mUID)

        //fun level property for holding the values returned from
        //var tmpPost = Post()
        query.get().addOnSuccessListener {
            if(!it.isEmpty){
                //Gets List of Post Objects returned by Firestore
                val list = it.toObjects(Post::class.java)
                cacheAllPostsToRoom(list)
                mListLiveData.value == list
            } else{
                Log.d(TAG, "Unable to create tmpPost, no data found in FireStore")
            }
        }
    }

    /*Caches the list of the User's to the Room Database
    * Is called once the list of Posts is obtained from the Firestore Database
    * */
    private fun cacheAllPostsToRoom(list: List<Post>){
        userDAO.updateListOfUserPosts(list)
    }





    /*
    gets Post object from the PostViewModel
    */
    fun addPostFromViewModel(post:Post){
        mPost = post
        addPostToRoomDatabase(mPost)
    }


    private fun addPostToRoomDatabase(mPost: Post){
        //TODO Should be completed in another thread
        postDAO.addPostToRoom(mPost)
        //TODO if Room data successfully syncs, add data to FireStore
        //The PostID will be generated by the Room database which will then be written into the Firestore Database
        syncPostToFireStore(mPost)
    }


    /*Called once the Post is synced to the Room Database
    uploads the Post to the Firestore Database*/
    private fun syncPostToFireStore(post: Post){
        firestoreDB.collection("Messages")
                //Sets Document Title to the PostID
                .document(post.id)
                .set(post)
                //Once Post is synced to the FireStore
                .addOnCompleteListener{

                }.addOnCompleteListener {

                }
    }

}