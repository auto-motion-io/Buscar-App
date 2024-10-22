package com.example.futurobuscartelas.login

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Extensão para inicializar o DataStore
val Context.dataStore by preferencesDataStore("user_prefs")

// Modelo para os dados do usuário
data class UserData(
    val idUsuario: Int,
    val nome: String,
    val token: String,
    val fotoUrl: String?
)

// Repositório responsável por acessar a DataStore
class UserRepository(private val context: Context) {

    // Função para salvar dados no DataStore
    suspend fun saveUserData(user: UserData) {
        context.dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.USER_ID] = user.idUsuario.toString()
            preferences[UserPreferencesKeys.USER_NAME] = user.nome
            preferences[UserPreferencesKeys.TOKEN] = user.token
            preferences[UserPreferencesKeys.FOTO_URL] = user.fotoUrl ?: ""
        }
    }

    // Função para obter dados do usuário da DataStore
    fun getUserData(): Flow<UserData?> {
        return context.dataStore.data.map { preferences ->
            val id = preferences[UserPreferencesKeys.USER_ID]?.toIntOrNull()
            val nome = preferences[UserPreferencesKeys.USER_NAME]
            val token = preferences[UserPreferencesKeys.TOKEN]
            val fotoUrl = preferences[UserPreferencesKeys.FOTO_URL]

            if (id != null && nome != null && token != null) {
                UserData(id, nome, token, fotoUrl)
            } else {
                null
            }
        }
    }

    // Função para limpar dados armazenados (logout)
    suspend fun clearUserData() {
        context.dataStore.edit { it.clear() }
        Log.i("user","clinando")
    }
}
