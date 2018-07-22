package r.stookey.hikr.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import dagger.MapKey
import r.stookey.hikr.Repo
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass


@Singleton
class ViewModelFactory(val userID: String?) : ViewModelProvider.Factory {

    var repo = Repo()
//    @Inject lateinit var repo: Repo

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(repo, userID!!) as T
        } else {
            return UserViewModel(repo, userID!!) as T
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
    @ViewModelKey(UserViewModel::class)
    internal  abstract fun userViewModel(viewModel: UserViewModel): ViewModel



    //Add more ViewModels here
}*/
