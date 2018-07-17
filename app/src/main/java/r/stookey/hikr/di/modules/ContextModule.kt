package r.stookey.hikr.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule{

    lateinit var mContext: Context

    fun ContextModule(context: Context){
        mContext = context
    }

    @Provides
    fun providesContext(): Context{
        return mContext.applicationContext
    }
}