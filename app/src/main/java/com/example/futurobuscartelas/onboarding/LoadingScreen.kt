package com.example.futurobuscartelas.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.futurobuscartelas.R
import com.example.futurobuscartelas.ui.theme.*

class MainViewModel : ViewModel() {
    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    init {

        viewModelScope.launch {
            delay(1500)
            _isLoading.value = false
        }
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LoadingScreen() {
    val infiniteTransition = rememberInfiniteTransition(label = "Infinite transition")

    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            tween(2000, easing = LinearEasing),
            RepeatMode.Restart
        ),
        label = "Animation Loading"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .background(BackGroundColor)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.gear_green),
                contentDescription = "",
                modifier = Modifier
                    .size(120.dp)
                    .rotate(angle),
                contentScale = ContentScale.FillWidth
            )



        }
    }
}