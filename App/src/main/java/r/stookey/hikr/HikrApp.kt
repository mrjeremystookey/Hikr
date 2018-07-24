package r.stookey.hikr

import android.app.Application
import r.stookey.hikr.di.DaggerHikrComponent
import r.stookey.hikr.di.HikrComponent
import r.stookey.hikr.di.modules.LocationModule
import r.stookey.hikr.di.modules.StorageModule

class HikrApp: Application() {

    lateinit var component: HikrComponent
        private set

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        component = DaggerHikrComponent.builder()
                .storageModule(StorageModule(this))
                .locationModule(LocationModule(this))
                .build()
    }

    companion object {
        private var INSTANCE: HikrApp? = null
        @JvmStatic
        fun get(): HikrApp = INSTANCE!!
    }

}