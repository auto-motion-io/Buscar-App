package com.example.futurobuscartelas.ui.theme

import androidx.compose.material3.CheckboxColors
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val BackGroundColor = Color(248, 247, 244)
val ClickAnimationColor = Color(241, 240, 238, 255)
val VerdeBuscar = Color(59, 86, 60, 255)
val InputContainerUnfocusedColor = Color(240, 239, 236)

val ErrorColor = Color(0xFFD9534F)

val CheckBoxColorsMotion = CheckboxColors(
    checkedCheckmarkColor = VerdeBuscar,
    uncheckedCheckmarkColor = InputContainerUnfocusedColor,
    checkedBoxColor = InputContainerUnfocusedColor,
    uncheckedBoxColor = InputContainerUnfocusedColor,
    disabledCheckedBoxColor = Color.Black,
    disabledUncheckedBoxColor = Color.Black,
    disabledIndeterminateBoxColor = Color.Black,
    checkedBorderColor = VerdeBuscar,
    uncheckedBorderColor = Color(210, 210, 210),
    disabledBorderColor = Color(0, 0, 0),
    disabledUncheckedBorderColor = Color.Black,
    disabledIndeterminateBorderColor = Color.Black
)