package com.example.futurobuscartelas.login

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.RetrofitService
import com.example.futurobuscartelas.dto.LoginUsuarioDTO
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.dto.LoginUsuarioResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.java.KoinJavaComponent.inject

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {
    var email = mutableStateOf("")
    var senha = mutableStateOf("")

    var isLoading = mutableStateOf(false)

    private val _loginState = MutableStateFlow<LoginState>(LoginState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)

    @OptIn(DelicateCoroutinesApi::class)
    fun login() {
        val userLogin = LoginUsuarioDTO(email.value, senha.value)
        GlobalScope.launch(Dispatchers.IO) {
            Log.i("api", "Logando....")
            try {
                isLoading.value = true
                val resposta = buscarApi.login(userLogin)
                isLoading.value = false
                if (resposta.isSuccessful) {
                    Log.i("api", "Usuário logado com sucesso: ${resposta.body()}")
                    val userData = resposta.body()!!.let {
                        UserData(it.idUsuario,it.email, it.nome,it.sobrenome, it.token, it.fotoUrl)
                    }
                    userRepository.saveUserData(userData)
                    _loginState.update { LoginState.Success }
                } else {
                    Log.e("api", "Erro ao logar usuário: ${resposta.errorBody()?.string()}")
                    if (resposta.code() != 500) {
                        Log.i("api", "ifzada????")
                        _loginState.update { LoginState.Error("Usuário ou senha inválidos") }
                    } else {
                        _loginState.update { LoginState.Error("Ocorreu um erro inesperado") }
                    }
                }
            } catch (exception: Exception) {
                Log.e("api", "Exception ao tentar logar usuário", exception)
                _loginState.update { LoginState.Error("Erro inesperado") }
                isLoading.value = false
            }
        }
    }

    fun getUserData(): Flow<UserData?> = userRepository.getUserData()

    fun clearUserData() {
        Log.i("user", "teste")
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.clearUserData()
            _loginState.update { LoginState.Idle } // Resetar estado de login se necessário
        }
    }

}
