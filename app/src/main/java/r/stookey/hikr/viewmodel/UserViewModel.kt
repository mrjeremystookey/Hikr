package r.stookey.hikr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import r.stookey.hikr.model.Post
import r.stookey.hikr.model.User

class UserViewModel: ViewModel(){


    //Email may not be needed, can retrieve and sync messages with the USERID
    var allPosts: MutableLiveData<List<Post>> = MutableLiveData()
    private lateinit var userID: String
    private lateinit var mUser: LiveData<User>
    private lateinit var mLocation: String

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        fun setUserID(uid: String){
            userID = uid
        }

        fun setLocation(location: String){
            mLocation = location
        }



        fun login(email: String, password:String): MutableLiveData<FirebaseUser>{
            //Attempt Login Here instead of Login Class
            var firebaseUserMutable: MutableLiveData<FirebaseUser> = MutableLiveData()
                //Attempt Login Here instead of Login Class
                //Return boolean and startActivity on next page
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        firebaseUserMutable.value = firebaseAuth.currentUser
                        return@addOnCompleteListener
                    }

                }
            if(firebaseUserMutable.value == null){
                firebaseUserMutable.value = firebaseAuth.currentUser
            }
            return firebaseUserMutable
        }
















}