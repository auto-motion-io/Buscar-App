package com.example.futurobuscartelas.login

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.Serializable

// Extensão para inicializar o DataStore
val Context.dataStore by preferencesDataStore("user_prefs")

// Modelo para os dados do usuário
data class UserData  (
    val idUsuario: Int,
    val email: String,
    val nome: String,
    val sobrenome: String,
    val token: String,
    val fotoUrl: String?
): Serializable

// Repositório responsável por acessar a DataStore
class UserRepository(private val context: Context) {

    // Função para salvar dados no DataStore
    suspend fun saveUserData(user: UserData) {
        context.dataStore.edit { preferences ->
            preferences[UserPreferencesKeys.USER_ID] = user.idUsuario.toString()
            preferences[UserPreferencesKeys.USER_EMAIL] = user.email
            preferences[UserPreferencesKeys.USER_NOME] = user.nome
            preferences[UserPreferencesKeys.USER_SOBRENOME] = user.sobrenome
            preferences[UserPreferencesKeys.TOKEN] = user.token
            preferences[UserPreferencesKeys.FOTO_URL] = user.fotoUrl ?: ""
        }
    }

    // Função para obter dados do usuário da DataStore
    fun getUserData(): Flow<UserData?> {
        return context.dataStore.data.map { preferences ->
            val id = preferences[UserPreferencesKeys.USER_ID]?.toIntOrNull()
            val email = preferences[UserPreferencesKeys.USER_EMAIL]
            val nome = preferences[UserPreferencesKeys.USER_NOME]
            val sobrenome = preferences[UserPreferencesKeys.USER_SOBRENOME]
            val token = preferences[UserPreferencesKeys.TOKEN]
            val fotoUrl = preferences[UserPreferencesKeys.FOTO_URL]


                UserData(id?:0,email?:"", nome?:"",sobrenome?:"", token?:"", fotoUrl)

        }
    }

    // Função para limpar dados armazenados (logout)
    suspend fun clearUserData() {
        context.dataStore.edit { it.clear() }
        Log.i("user","limpando")
    }
}
