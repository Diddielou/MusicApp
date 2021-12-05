package fhnw.emoba.freezerapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.freezerapp.data.impl.RemoteFreezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import fhnw.emoba.freezerapp.ui.screens.AlbumScreen
import fhnw.emoba.freezerapp.ui.screens.MainScreen
import fhnw.emoba.freezerapp.ui.screens.PlayerScreen
import fhnw.emoba.freezerapp.ui.screens.RadioScreen
import fhnw.emoba.freezerapp.ui.theme.FreezerAppTheme

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun FreezerUI(model : FreezerModel){
    with(model) {
        FreezerAppTheme(model.darkTheme){
            Crossfade(targetState = currentScreen) { screen ->
                when (screen) {
                    Screen.MAIN   -> { MainScreen(model) }
                    Screen.PLAYER -> { PlayerScreen(model) }
                    Screen.ALBUMDETAILS -> { AlbumScreen(model) }
                    Screen.RADIODETAILS -> {  RadioScreen(model) }
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun Preview(){
    val model = FreezerModel(service = RemoteFreezerService())
    FreezerUI(model = model)
}
