package r.stookey.hikr.di


import android.app.Application
import android.content.Context
import android.location.LocationManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Component
import r.stookey.hikr.Repo
import r.stookey.hikr.db.HikrDatabase
import r.stookey.hikr.db.dao.PostDAO
import r.stookey.hikr.db.dao.UserDAO
import r.stookey.hikr.di.modules.LocationModule
import r.stookey.hikr.di.modules.StorageModule
import r.stookey.hikr.ui.activities.Login
import javax.inject.Singleton


@Singleton
@Component(modules = [StorageModule::class, LocationModule::class])
interface HikrComponent {

    var userDAO: UserDAO
    var postDAO: PostDAO
    var hikrDatabase: HikrDatabase
    var firestoreDatabase: FirebaseFirestore
    var repo: Repo
    var firebaseAuth: FirebaseAuth
    var context: Context
    var locationManager: LocationManager

}