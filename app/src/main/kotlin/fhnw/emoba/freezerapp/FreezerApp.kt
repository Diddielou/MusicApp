package fhnw.emoba.freezerapp

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import fhnw.emoba.EmobaApp
import fhnw.emoba.freezerapp.data.impl.RemoteMusicService
import fhnw.emoba.freezerapp.data.impl.TrackRepository
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.MusicModel
import fhnw.emoba.freezerapp.ui.FreezerUI


object FreezerApp : EmobaApp {

    private lateinit var freezerModel : FreezerModel
    private lateinit var musicModel : MusicModel

    override fun initialize(activity: ComponentActivity) {
        val trackRepository = TrackRepository()
        val service = RemoteMusicService()
        freezerModel = FreezerModel
        musicModel = MusicModel(trackRepository, service)
        // model.getFavoriteTracksAsync()
    }

    @Composable
    override fun CreateUI() {
        FreezerUI(FreezerModel)
    }

}

