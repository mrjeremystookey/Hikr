package r.stookey.hikr

import android.arch.lifecycle.LiveData
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import r.stookey.hikr.db.HikrDatabase
import r.stookey.hikr.db.dao.PostDAO
import r.stookey.hikr.db.dao.UserDAO
import r.stookey.hikr.db.entity.PostEntity
import r.stookey.hikr.db.entity.UserEntity
import r.stookey.hikr.model.Post
import javax.inject.Inject

/*class Repo(@Inject var userDAO: UserDAO, @Inject var postDAO: PostDAO, @Inject var firestoreDB: FirebaseFirestore){*/
class Repo() {

    private val TAG = "REPO"


    lateinit var mListLiveData: LiveData<List<Post>>
    lateinit var mList: List<Post>
    lateinit var mUID: String
    lateinit var mPost:Post

    @Inject lateinit var userDAO: UserDAO
    @Inject lateinit var postDAO: PostDAO
    @Inject lateinit var firestoreDB: FirebaseFirestore



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
        if(userDAO.getUserPostsByID(mUID) != null){
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
        var collectionReference = firestoreDB.collection("Messages")
        var query: Query = collectionReference.whereEqualTo("created_by", mUID)

        //fun level property for holding the values returned from
        //var tmpPost = Post()
        query.get().addOnSuccessListener {
            if(!it.isEmpty){
                //Gets List of Post Objects returned by Firestore
                var list = it.toObjects(Post::class.java)
                syncAllPostsToRoom(list)
                //#1
                mListLiveData.value == list

                //#2
                /*it.forEach {
                    tmpPost.createdBy = it["dateCreated"].toString()
                    tmpPost.id = it["id"].toString()
                    tmpPost.dateCreated = it["location"].toString()
                    tmpPost.title = it["title"].toString()
                    tmpPost.text = it["text"].toString()
                    tmpPost.createdBy = it["createdBy"].toString()
                }*/
            } else{
                Log.d(TAG, "Unable to create tmpPost, no data found in FireStore")
            }
        }
    }

    /*Caches the list of the User's to the Room Database
    * Is called once the list of Posts is obtained from the Firestore Database
    * */
    private fun syncAllPostsToRoom(list: List<Post>){
        //TODO Cache List of Posts to the Room Database
        var userEntity = UserEntity()
        userEntity.allPosts = list
    }





    /*
    gets Post object from the PostViewModel
    */
    fun addPostFromViewModel(post:Post){
        mPost = post
        addPostToRoomDatabase()
    }


    private fun addPostToRoomDatabase(){
        var postEntity = PostEntity()
        //Will probably need to be in its own thread
        postEntity.createdBy = mPost.createdBy
        postEntity.date = mPost.dateCreated
        postEntity.location = mPost.location
        postEntity.text = mPost.text
        postEntity.title = mPost.title
        //TODO Should be completed in another thread
        postDAO.addPostToRoom(postEntity)
        //TODO if Room data successfully syncs, add data to FireStore
        syncPostToFireStore(postEntity)
    }


    /*Called once the Post is synced to the Room Database
    uploads the Post to the Firestore Database*/
    private fun syncPostToFireStore(postEntity: PostEntity){
        firestoreDB.collection("Messages")
                //Sets Document Title to the PostID
                .document(postEntity.postID.toString())
                .set(postEntity)
                //Once Post is synced to the FireStore
                .addOnCompleteListener{

                }.addOnCompleteListener {

                }
    }

}