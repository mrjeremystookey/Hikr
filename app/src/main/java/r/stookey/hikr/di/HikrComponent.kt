package r.stookey.hikr.di


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Component
import r.stookey.hikr.Repo
import r.stookey.hikr.db.HikrDatabase
import r.stookey.hikr.db.dao.PostDAO
import r.stookey.hikr.db.dao.UserDAO
import r.stookey.hikr.di.modules.AppModule
import r.stookey.hikr.di.modules.ContextModule
import r.stookey.hikr.di.modules.StorageModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ContextModule::class, AppModule::class, StorageModule::class))
interface HikrComponent{

    var userDAO: UserDAO
    var postDAO: PostDAO
    var roomDatabase: HikrDatabase
    var firestoreDatabase: FirebaseFirestore
    var repo: Repo
    var firebaseAuth: FirebaseAuth
}