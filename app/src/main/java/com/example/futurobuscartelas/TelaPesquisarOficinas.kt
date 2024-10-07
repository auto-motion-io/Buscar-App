package com.example.futurobuscartelas

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.ui.theme.TelaBaseOSP

@Composable
fun TelaPesquisarOficinas(navController: NavController) {
    TelaBaseOSP(
        navController = navController,
        titulo = stringResource(id = R.string.label_tituloOficinas))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPesquisarOficinasPreview() {
    val navController = rememberNavController()
    TelaPesquisarOficinas(navController)
}