package r.stookey.hikr.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dagger.Module
import dagger.Provides
import r.stookey.hikr.Repo
import r.stookey.hikr.db.HikrDatabase
import r.stookey.hikr.db.dao.PostDAO
import r.stookey.hikr.db.dao.UserDAO
import javax.inject.Singleton


/*Used for injecting the Database instance and DAOS into the Repo class*/
@Module
class StorageModule(context: Context) {

    private var mContext = context


    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return mContext
    }

    @Singleton
    @Provides
    fun providesRepo(): Repo {
        return Repo()
    }


    //Room DI
    @Singleton
    @Provides
    fun provideRoomDatabase(): HikrDatabase {
        return Room.databaseBuilder(mContext, HikrDatabase::class.java, "hikr_db")
                .allowMainThreadQueries()
                .build()
    }

    @Singleton
    @Provides
    fun providesPostDao(db: HikrDatabase): PostDAO {
        return db.getPostDao()
    }

    @Singleton
    @Provides
    fun providesUserDao(db: HikrDatabase): UserDAO {
        return db.getUserDao()
    }


    //Firebase DI
    @Singleton
    @Provides
    fun providesFireStore(): FirebaseFirestore {
        var settings = FirebaseFirestoreSettings.Builder().setTimestampsInSnapshotsEnabled(true).build()
        var firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = settings
        return FirebaseFirestore.getInstance()

    }

    @Singleton
    @Provides
    fun providesFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


}