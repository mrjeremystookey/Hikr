package r.stookey.hikr.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.location.Location
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import r.stookey.hikr.Repo
import r.stookey.hikr.di.DaggerHikrComponent
import r.stookey.hikr.di.modules.StorageModule
import r.stookey.hikr.model.Post
import javax.inject.Inject

class UserViewModel(@Inject val repo: Repo, @Inject val firebaseAuth: FirebaseAuth): ViewModel(){


    private lateinit var userID: String
    private lateinit var mLocation: Location



    //Best Way to pass parameters to a View Model?
        fun setUserID(uid: String){
            userID = uid
        }

        fun getUserID(): String{
            return userID
        }

        fun setLocation(location: Location){
            mLocation = location
        }

        //TODO Get All Posts by User ID for displaying in the List Fragment
        /*Called when the All Posts fragment is opened or when a now post is added to the DB*/
        fun getAllPostsByUserID(): LiveData<List<Post>>{
            return repo.getAllPostsByUserID(userID)
        }



        //Should probably be moved out of this class.
        fun login(email: String, password:String): MutableLiveData<FirebaseUser>{
            //Attempt Login Here instead of Login Class
            val firebaseUserMutable: MutableLiveData<FirebaseUser> = MutableLiveData()
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