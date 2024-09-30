package com.example.futurobuscartelas

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.futurobuscartelas.ui.theme.telaBaseOSP


@Composable
fun TelaPesquisarPeças(navController: NavController) {
    telaBaseOSP(
        navController = navController,
        titulo = stringResource(R.string.label_pecas)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPesquisarPecasPreview() {
    val navController = rememberNavController();
    TelaPesquisarPeças(navController)
}