package com.wahidabd.siketan.utils

import android.content.Context


/**
 * Created by Wahid on 6/9/2023.
 * Github github.com/wahidabd.
 */


class PrefManager(context: Context) {

    private val prefs = context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)

    fun setToken(value: String){
        prefs.edit().apply {
            putString(Constant.TOKEN, value)
        }.apply()
    }

    fun getToken(): String = with(Constant){
        return prefs.getString(TOKEN, "").toString()
    }

    fun login(state: Boolean) = with(Constant){
        val editor = prefs.edit()
        editor.apply {
            putBoolean(LOGIN, state)
        }.apply()
    }

    fun getLogin(): Boolean = with(Constant) {
        return prefs.getBoolean(LOGIN, false)
    }

    fun logout(){
        prefs.edit().apply {
            clear()
        }.apply()
    }

}