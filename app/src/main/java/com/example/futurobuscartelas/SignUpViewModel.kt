package com.example.futurobuscartelas

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    var nome = mutableStateOf("")
    var sobrenome = mutableStateOf("")
    var email = mutableStateOf("")
    var senha = mutableStateOf("")
    var checked = mutableStateOf(false)

}