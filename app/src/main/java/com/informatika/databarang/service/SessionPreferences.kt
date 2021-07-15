package com.informatika.databarang.service

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SessionPreferences(activity: Activity) {
    private val KEY_LOGIN ="LOGIN"
    private val KEY_USERNAME = "USERNAME"

    val sessionLogin = activity.getSharedPreferences(KEY_LOGIN, Context.MODE_PRIVATE)

    fun actionLogin(userName : String) {
        val edit = sessionLogin.edit()
        edit.putString(KEY_USERNAME, userName)
        edit.apply()
    }
    fun actionLaout() {
        val edit = sessionLogin.edit()
        edit.Clear().apply()
    }
    fun getUserName(): String{
        return sessionLogin.getString(KEY_USERNAME, null)

    }
}