package r.stookey.hikr.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import r.stookey.hikr.Repo
import r.stookey.hikr.db.HikrDatabase
import r.stookey.hikr.db.dao.PostDAO
import r.stookey.hikr.db.dao.UserDAO
import javax.inject.Singleton


/*Used for injecting the Database instance and DAOS into the Repo class*/
@Module
class StorageModule(app: Application) {

    private var roomDatabase: HikrDatabase
    private var firestoreDB: FirebaseFirestore
    private lateinit var userDAO: UserDAO
    private lateinit var postDAO: PostDAO


    init {
        roomDatabase = HikrDatabase.getInstance(app.applicationContext)
        firestoreDB = FirebaseFirestore.getInstance()
    }


    @Singleton
    @Provides
    fun providesRepo(): Repo {
        return Repo()
    }


    //Room DI
    @Singleton
    @Provides
    fun providesDatabase(): HikrDatabase {
        return roomDatabase
    }


    @Singleton
    @Provides
    fun providesPostDao(db: HikrDatabase): PostDAO {
        postDAO = db.getPostDao()
        return postDAO
    }

    @Singleton
    @Provides
    fun providesUserDao(db: HikrDatabase): UserDAO {
        userDAO = db.getUserDao()
        return userDAO
    }


    //Firebase DI
    @Singleton
    @Provides
    fun providesFireStore(): FirebaseFirestore {
        return firestoreDB
    }

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}