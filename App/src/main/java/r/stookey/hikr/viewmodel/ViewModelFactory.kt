package r.stookey.hikr.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import r.stookey.hikr.Repo
import javax.inject.Singleton


@Singleton
class ViewModelFactory(val userID: String?) : ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(userID!!) as T
        } else if (modelClass.isAssignableFrom(ListViewModel::class.java)){
            return ListViewModel(userID!!) as T
        } else {
            return ProfileViewModel() as T
        }
    }
}




/*@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)*/

/*@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    internal abstract fun postViewModel(viewModel: PostViewModel   ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    internal  abstract fun userViewModel(viewModel: ListViewModel): ViewModel



    //Add more ViewModels here
}*/
