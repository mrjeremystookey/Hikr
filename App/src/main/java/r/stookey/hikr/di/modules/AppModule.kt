package r.stookey.hikr.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class AppModule(private val app: Application) {


    @Provides
    fun provideAppContext(): Context {
        return app
    }


}