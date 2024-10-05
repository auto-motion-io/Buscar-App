package com.example.futurobuscartelas.ui.theme

import android.app.Activity
import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.futurobuscartelas.R

// Paddings

val DefaultHorizontalPadding = 32.dp
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
fun ArrowBackButton(onClick: () -> Unit) {
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
            .clip(shape = CircleShape)
            .background(animatedColor) // Forma circular e cor animada
            .clickable {
                clicked = !clicked
                onClick()
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


@Composable
fun DefaultButtonMotion(
    text: String,
    isFilled: Boolean,
    modifier: Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .then(
                if (!isFilled)
                    Modifier.border(2.dp, VerdeBuscar, shape = RoundedCornerShape(100))
                else modifier
            )
            .clip(RoundedCornerShape(100)),
        colors = ButtonDefaults.buttonColors(
            containerColor = VerdeBuscar
        ),
        enabled = enabled
    ) {
        Text(
            text = text,
            color = if (isFilled) Color.White else VerdeBuscar,
            style = TextStyle(
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = PRODUCT_SANS_FAMILY
            ),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
        )

    }

}


@Composable
fun CustomInputMotion(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isPasswordField: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = VerdeBuscar,
            unfocusedTextColor = Color.Black,
            focusedLabelColor = VerdeBuscar,
            unfocusedLabelColor = Color.Gray,
            focusedBorderColor = VerdeBuscar,
            unfocusedBorderColor = Color.Transparent,
            unfocusedContainerColor = InputContainerUnfocusedColor
        ),
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        textStyle = TextStyle(fontSize = 16.sp),
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
fun UpperLabelText(value: String) {
    Text(
        text = value,
        modifier = Modifier.padding(0.dp, 0.dp, 6.dp, 10.dp),
        style = TextStyle(
            fontFamily = PRODUCT_SANS_FAMILY,
            fontWeight = FontWeight.Bold,
            color = VerdeBuscar,
            fontSize = 18.sp
        )
    )
}

@Composable
fun isKeyboardVisible(): Boolean {
    val context = LocalContext.current
    val rootView = (context as? Activity)?.window?.decorView
    val keyboardVisible = remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        val callback = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            rootView?.getWindowVisibleDisplayFrame(rect)
            val heightDiff = rootView!!.height - (rect.bottom - rect.top)
            keyboardVisible.value = heightDiff > 200 // Ajuste o valor conforme necessário
        }

        rootView?.viewTreeObserver?.addOnGlobalLayoutListener(callback)

        onDispose {
            rootView?.viewTreeObserver?.removeOnGlobalLayoutListener(callback)
        }
    }

    return keyboardVisible.value
}

