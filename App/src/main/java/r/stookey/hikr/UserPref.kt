package r.stookey.hikr

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

class UserPref() {

    var LOGGED_IN_PREF = "logged_in_status"


    fun getPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setLoggedIn(context: Context, loggedIn: Boolean) {
        var editor = getPreferences(context).edit()
        editor.putBoolean(LOGGED_IN_PREF, loggedIn)
        editor.apply()
    }

    fun saveLoginInfo(context: Context, username: String, userID: String) {
        val editor = getPreferences(context).edit()
        editor.putString("username", username)
        editor.putString("userID", userID)
        editor.apply()
    }

    fun getUserID(context: Context): String {
        val editor = getPreferences(context)
        return editor.getString("userID", null)
    }

    fun getUsername(context: Context): String {
        val editor = getPreferences(context)
        return editor.getString("username", null)
    }


    fun isLoggedIn(context: Context): Boolean {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false)
    }


}