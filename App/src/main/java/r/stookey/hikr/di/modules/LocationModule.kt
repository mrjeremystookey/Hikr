package r.stookey.hikr.di.modules

import android.app.Application
import android.content.Context
import android.location.Location
import android.location.LocationManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class LocationModule(context: Context){

    private val mContext = context

    @Singleton
    @Provides
    fun providesLocationManager(): LocationManager{
        return mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

}