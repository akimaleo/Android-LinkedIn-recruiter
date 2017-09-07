package com.letit0or1.akimaleo.linkedinrecruiter.storage

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by akimaleo on 07.09.17.
 */

internal class UserUtil private constructor() {

    private var sharedPreferences: SharedPreferences? = null
    private var context: Context? = null


    fun saveData(login: String, password: String) {
        sharedPreferences!!.edit()
                .putString(FIELD_LOGIN, login)
                .putString(FIELD_PASSWORD, password)
                .apply()
    }

    val login: String
        get() = sharedPreferences!!.getString(FIELD_LOGIN, "")

    val password: String
        get() = sharedPreferences!!.getString(FIELD_PASSWORD, "")

    fun setContext(context: Context) {
        this.context = context
        sharedPreferences = context.getSharedPreferences(USER_SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }

    companion object {

        private val USER_SHARED_PREFERENCES = "uDataUtilStorage"
        private val FIELD_LOGIN = "login"
        private val FIELD_PASSWORD = "password"

        val instance = UserUtil()
    }
}
