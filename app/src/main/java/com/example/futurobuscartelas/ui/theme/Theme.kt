package com.example.futurobuscartelas.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.futurobuscartelas.R

// Paddings

val DefaultHorizontalPadding = 24.dp
val DefaultVerticalPadding = 64.dp

// Common Components

@Composable
fun ArrowBackButton(navController: NavController) {
    var clicked by remember { mutableStateOf(false) }

    // Definindo a cor de fundo animada
    val targetColor = if (clicked) ClickAnimationColor else BackGroundColor
    val animatedColor by animateColorAsState(
        targetColor,
        animationSpec = tween(durationMillis = 300)
    )

    Box(
        contentAlignment = Alignment.Center, // Centraliza o conteúdo da Box
        modifier = Modifier
            .size(36.dp) // Tamanho da Box
            .clip(shape = CircleShape)
            .background(animatedColor) // Forma circular e cor animada
            .clickable {
                clicked = !clicked
                navController.popBackStack()
            }
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back_green),
            contentDescription = "Botão voltar",
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Fit
        )
    }
}


@Composable
fun BaseScreenLayout(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackGroundColor)
            .padding(
                start = DefaultHorizontalPadding,
                end = DefaultHorizontalPadding,
                top = DefaultVerticalPadding,
                bottom = 0.dp
            )
    ) {

        content()

    }
}

