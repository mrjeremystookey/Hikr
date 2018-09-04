package r.stookey.hikr.viewmodel

import android.arch.lifecycle.ViewModel
import r.stookey.hikr.di.Injector

class ProfileViewModel constructor(var userID: String) : ViewModel() {

    private val repo = Injector.get().repo


    fun testRepo() {

    }

}