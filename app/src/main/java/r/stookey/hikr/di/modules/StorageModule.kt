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
class StorageModule(){

    private lateinit var roomDatabase: HikrDatabase
    private lateinit var firestoreDB: FirebaseFirestore
    private lateinit var userDAO: UserDAO
    private lateinit var postDAO: PostDAO



    //Creates the Room Database Instance Singleton
    fun storageModule(app: Application): StorageModule{
/*
        roomDatabase = HikrDatabase.getInstance(app.applicationContext)
*/
        roomDatabase = Room.databaseBuilder(app.applicationContext, HikrDatabase::class.java, "hikr_db").build()
        return StorageModule()
    }


    @Singleton
    @Provides
    fun providesDatabase(): HikrDatabase{
        return roomDatabase
    }



    @Singleton
    @Provides
    fun providesPostDao(db: HikrDatabase): PostDAO{
        postDAO = db.getPostDao()
        return postDAO
    }


    @Singleton
    @Provides
    fun providesUserDao(db: HikrDatabase): UserDAO{
        userDAO = db.getUserDao()
        return userDAO
    }

    @Singleton
    @Provides
    fun providesFireStore(): FirebaseFirestore{
        firestoreDB = FirebaseFirestore.getInstance()
        return firestoreDB
    }

    @Singleton
    @Provides
    fun providesRepo(): Repo {
        return Repo()
    }

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}