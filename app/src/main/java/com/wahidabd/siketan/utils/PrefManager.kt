package com.wahidabd.siketan.utils

import android.content.Context
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.siketan.data.auth.model.LoginPenyuluhRequest
import com.wahidabd.siketan.domain.auth.model.LoginRequest
import com.wahidabd.siketan.domain.auth.model.RegisterRequest
import com.wahidabd.siketan.domain.auth.model.User


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

    fun setAttemptLogin(request: LoginRequest) = with(Constant){
        prefs.edit().apply {
            putString(NIK, request.nik)
            putString(PASSWORD, request.password)
        }.apply()
    }

    fun setAttemptLoginPenyuluh(request: LoginPenyuluhRequest) = with(Constant){
        prefs.edit().apply {
            putString(NIK, request.nip)
            putString(PASSWORD, request.password)
        }.apply()
    }

    fun getAttemptLogin(): LoginRequest = with(Constant){
        return LoginRequest(
            nik = prefs.getString(NIK, "").toString(),
            password = prefs.getString(PASSWORD, "").toString()
        )
    }

    fun getAttemptLoginPenyuluh(): LoginPenyuluhRequest = with(Constant){
        return LoginPenyuluhRequest(
            nip = prefs.getString(NIK, "").toString(),
            password = prefs.getString(PASSWORD, "").toString()
        )
    }

    fun getLogin(): Boolean = with(Constant) {
        return prefs.getBoolean(LOGIN, false)
    }

    fun setUser(data: User) = with(Constant) {
        prefs.edit().apply{
            putInt(USER_ID, data.id ?: 0)
            putString(USER_PHOTO, data.foto)
            putString(USER_WA, data.NoWa)
            putString(USER_ADDRESS, data.alamat)
            putString(USER_DESA, data.desa)
            putString(USER_KECAMATAN, data.kecamatan)
            putString(USER_NAME, data.nama)
            putString(ROLE, data.role)
        }.apply()
    }

    fun getUser(): User = with(Constant){
        return User(
            id = prefs.getInt(USER_ID, 0),
            foto = prefs.getString(USER_PHOTO, emptyString()),
            nama = prefs.getString(USER_NAME, emptyString()),
            NoWa = prefs.getString(USER_WA, emptyString()),
            alamat = prefs.getString(USER_ADDRESS, emptyString()),
            desa = prefs.getString(USER_DESA, emptyString()),
            kecamatan = prefs.getString(USER_KECAMATAN, emptyString()),
            role = prefs.getString(ROLE, emptyString()),
        )
    }

    fun logout(){
        prefs.edit().apply {
            clear()
        }.apply()
    }

}