package com.example.futurobuscartelas.telas.perfil

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.futurobuscartelas.api.BuscarApi
import com.example.futurobuscartelas.api.ErrorState
import com.example.futurobuscartelas.api.SuccessState
import com.example.futurobuscartelas.dto.UpdateSenhaDTO
import com.example.futurobuscartelas.dto.UpdateUsuarioDTO
import com.example.futurobuscartelas.koin.SessaoUsuario
import com.example.futurobuscartelas.login.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.koin.java.KoinJavaComponent.inject
import com.example.futurobuscartelas.login.UserRepository
import com.example.futurobuscartelas.models.Usuario
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class PerfilViewModel() : ViewModel() {

    private val buscarApi: BuscarApi by inject(BuscarApi::class.java)

    var errorState = mutableStateOf(ErrorState())
    var successState = mutableStateOf(SuccessState())

    var isLoading = mutableStateOf(false)
        private set


    fun atualizarUsuario(usuarioDTO: UpdateUsuarioDTO, sessaoUsuario: SessaoUsuario) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val resposta = buscarApi.atualizar(sessaoUsuario.id, usuarioDTO)
                if (resposta.isSuccessful) {
                    val usuario = resposta.body() as Usuario
                    sessaoUsuario.nome = usuario.nome
                    sessaoUsuario.sobrenome = usuario.sobrenome
                    sessaoUsuario.email = usuario.email

                    successState.value = SuccessState(
                        isSuccessfull = true,
                        message = "Usuário atualizado com sucesso."
                    )
                } else if (resposta.code() == 409) {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Email já cadastrado no sistema."
                    )
                } else {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Um erro inesperado ocorreu. Entre em contato com o suporte."
                    )
                }
            } catch (e: Exception) {
                errorState.value = ErrorState(
                    hasError = true,
                    message = "Erro ao atualizar o usuário: ${e.localizedMessage}"
                )
            } finally {
                isLoading.value = false
            }
        }
    }

    fun atualizarSenha(senhaAtual: String, novaSenha: String,  sessaoUsuario: SessaoUsuario) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val updateSenhaDTO = UpdateSenhaDTO(senhaAtual, novaSenha)
                val resposta = buscarApi.atualizarSenha(sessaoUsuario.id, updateSenhaDTO)
                if (resposta.isSuccessful) {
                    successState.value = SuccessState(
                        isSuccessfull = true,
                        message = "Senha atualizada com sucesso."
                    )
                } else if (resposta.code() == 401) {
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Senha atual incorreta."
                    )
                } else {
                Log.i("api", "Erro ao atualizar senha: ${resposta.code()} - ${resposta.message()}")
                    errorState.value = ErrorState(
                        hasError = true,
                        message = "Um erro inesperado ocorreu. Entre em contato com o suporte."
                    )
                }
            } catch (e: Exception) {
                Log.i("api", "Erro ao atualizar senha: ${e.localizedMessage}")
                Log.i("api", "Erro ao atualizar senha: ${e.message}")
                errorState.value = ErrorState(
                    hasError = true,
                    message = "Erro ao atualizar a senha: ${e.localizedMessage}"
                )
            } finally {
                isLoading.value = false
            }
        }
    }
}