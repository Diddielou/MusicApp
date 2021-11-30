package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object FreezerModel {
    // Texts
    var title = "Freezer Player"
    var drawerTitle = "Preferences"
    var content = "Hello Deezer"

    // Screen
    var currentScreen by mutableStateOf(Screen.MAIN)
    var selectedTab by mutableStateOf(Tab.TRACKS)

    // Theme
    var darkTheme by mutableStateOf(false)
    private set
    fun toggleTheme() {  // das Theme soll nur ueber toggleTheme geaendert werden koennen, also ist der setter 'private'
        darkTheme = !darkTheme
    }
    fun toggleThemeText(): String {
        return if (darkTheme){
            "Welcome to the dark side!"
        } else {
            "Light's on!"
        }
    }


    // Player
    var playerIsReady by mutableStateOf(true)

    var trackId by mutableStateOf("")
    private var currentlyPlaying = ""  // wird nur intern gebraucht, soll kein Recompose ausloesen, daher auch kein MutableState


    private val player = MediaPlayer().apply {
        setOnCompletionListener { playerIsReady = true }
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        setOnPreparedListener {
            start()
        }
    }

    fun startPlayer(){
        playerIsReady = false
        try {
            if (currentlyPlaying == trackId && !player.isPlaying) {
                player.start()
            } else {
                currentlyPlaying = trackId
                player.reset()
                player.setDataSource("https://api.deezer.com/${trackId}.mp3") // TODO
                player.prepareAsync()
            }
        } catch (e: Exception) {
            playerIsReady = true
        }
    }

    fun pausePlayer() {
        player.pause()
        playerIsReady = true
    }

    fun fromStart() {
        player.seekTo(0)
        player.start()
        playerIsReady = false
    }

}