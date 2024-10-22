package com.example.futurobuscartelas.login


sealed class LoginState {
    object Idle : LoginState()  // Estado inicial, sem ação em andamento
    object Success : LoginState()  // Login realizado com sucesso
    data class Error(val message: String) : LoginState()  // Falha ao realizar o login
}