package com.example.futurobuscartelas.login

import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferencesKeys {
    val USER_ID = stringPreferencesKey("user_id")
    val USER_EMAIL = stringPreferencesKey("user_email")
    val USER_NOME = stringPreferencesKey("user_nome")
    val USER_SOBRENOME = stringPreferencesKey("user_sobrenome")
    val TOKEN = stringPreferencesKey("token")
    val FOTO_URL = stringPreferencesKey("foto_url")
}