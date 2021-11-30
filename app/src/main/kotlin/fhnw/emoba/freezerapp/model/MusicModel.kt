package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.Album
import fhnw.emoba.freezerapp.data.Artist
import fhnw.emoba.freezerapp.data.Radio
import fhnw.emoba.freezerapp.data.Track
import fhnw.emoba.freezerapp.data.impl.RemoteMusicService
import fhnw.emoba.freezerapp.data.impl.TrackRepository
import java.util.*

class MusicModel(val trackRepo: TrackRepository, val service: RemoteMusicService) {
    var title = "FHNW Player"
    var isDarkTheme by mutableStateOf(false)

    // Lists
    var albums: List<Album> by mutableStateOf(emptyList())
    var artists: List<Artist> by mutableStateOf(emptyList())
    var radios: List<Radio> by mutableStateOf(emptyList())
    var tracks: List<Track> by mutableStateOf(emptyList())
    var favouriteTracks: List<Track> by mutableStateOf(emptyList())


}