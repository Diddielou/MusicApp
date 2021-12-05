package fhnw.emoba.freezerapp

import androidx.activity.ComponentActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import fhnw.emoba.EmobaApp
import fhnw.emoba.freezerapp.data.impl.RemoteFreezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.FreezerUI


object FreezerApp : EmobaApp {

    private lateinit var freezerModel : FreezerModel

    override fun initialize(activity: ComponentActivity) {
        val service = RemoteFreezerService()
        //model.loadFavorites(activity)
        freezerModel = FreezerModel(service)
        // model.getFavoriteTracksAsync()
    }

    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    @Composable
    override fun CreateUI() {
        FreezerUI(freezerModel)
    }

}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview
@Composable
fun Preview(){
    FreezerUI(FreezerModel(RemoteFreezerService()))
}
