package r.stookey.hikr.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Singleton


@Singleton
class ViewModelFactory(val userID: String?) : ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java)) {
            return PostViewModel(userID!!) as T
        } else if (modelClass.isAssignableFrom(ListViewModel::class.java)){
            return ListViewModel(userID!!) as T
        } else {
            return ProfileViewModel(userID!!) as T
        }
    }
}


