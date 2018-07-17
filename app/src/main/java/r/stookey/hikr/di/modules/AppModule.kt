package r.stookey.hikr.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



/*Module to hook into Dagger2 to provide the Application for injection
* Will be used in the creation of Room Database
* */

@Module
class AppModule{

    lateinit var application: Application

    fun AppModule(app: Application){
        application = app
    }


    @Provides
    @Singleton
    fun provideApplication(): Application{
        return application
    }
}