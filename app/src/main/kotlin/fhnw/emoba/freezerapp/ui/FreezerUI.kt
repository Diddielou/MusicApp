package fhnw.emoba.freezerapp.ui

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import fhnw.emoba.freezerapp.ui.screens.MainScreen
import fhnw.emoba.freezerapp.ui.screens.PlayerScreen
import fhnw.emoba.freezerapp.ui.theme.FreezerAppTheme

@Composable
fun FreezerUI(model : FreezerModel){
    with(model) {
        FreezerAppTheme(model.darkTheme){
            Crossfade(targetState = currentScreen) { screen ->
                when (screen) {
                    Screen.MAIN   -> { MainScreen(model) }
                    Screen.PLAYER -> { PlayerScreen(model) }
                }
            }
        }
    }
}


@Preview
@Composable
fun Preview(){
    FreezerModel.currentScreen = Screen.MAIN
    FreezerUI(model = FreezerModel)
}
