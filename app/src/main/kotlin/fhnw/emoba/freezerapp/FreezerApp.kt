package fhnw.emoba.freezerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.emoba.EmobaApp
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.ui.AppUI


object FreezerApp : EmobaApp {

    override fun initialize(activity: ComponentActivity) {
    }

    @Composable
    override fun CreateUI() {
        AppUI(FreezerModel)
    }

}

