package com.example.futurobuscartelas.login

import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferencesKeys {
    val USER_ID = stringPreferencesKey("user_id")
    val USER_NAME = stringPreferencesKey("user_name")
    val TOKEN = stringPreferencesKey("token")
    val FOTO_URL = stringPreferencesKey("foto_url")
}