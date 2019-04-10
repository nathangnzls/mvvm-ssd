package ph.com.southstardrug.www.southstardrug.util

import android.content.Context
import android.content.SharedPreferences

class SessionManager(ctx: Context){

    var PRIVATE_MODE = 0
    lateinit var context: Context
    private val PREF_NAME = "myPref"
    private val IS_LOGGED_IN = "isRegistered"
    var pref: SharedPreferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    var editor: SharedPreferences.Editor = pref.edit()
    fun createLoginSession(session: Boolean) {
        editor.putBoolean(IS_LOGGED_IN, true)
        editor.commit()
    }
    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGGED_IN, false)
    }
    fun logout() {
        editor.putBoolean(IS_LOGGED_IN, false)
        editor.commit()
    }
}