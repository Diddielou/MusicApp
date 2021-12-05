package fhnw.emoba.freezerapp.model

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.freezerapp.data.*
import fhnw.emoba.freezerapp.data.impl.*
import fhnw.emoba.freezerapp.data.impl.ISearch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class FreezerModel(val service: IFreezerService) {

    // Texts
    var drawerTitle = "Settings"

    // Screen
    var currentScreen by mutableStateOf(Screen.MAIN)
    var selectedTab by mutableStateOf(Tab.TRACKS)

    // Theme
    var darkTheme by mutableStateOf(false)
        private set

    fun toggleTheme() {
        darkTheme = !darkTheme
    }
    fun toggleThemeText(): String {
        return if (darkTheme) {
            "Go light"
        } else {
            "Go dark"
        }
    }

    // Music data
    var searchFilter by mutableStateOf("")
    var isLoading     by mutableStateOf(false)
    var isLoaded     by mutableStateOf(false)

    private val modelScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    // Player
    var playerIsReady by mutableStateOf(true) // false: as long as track's playing

    // Lists for tabs
    var foundTracksList: List<ISearch> by mutableStateOf(emptyList())
    var foundAlbumsList: List<Album> by mutableStateOf(emptyList())
    var radioStations: List<Radio> by mutableStateOf(emptyList())
    var favoriteTracksList: MutableList<ISearch> = mutableStateListOf()
    var selectedAlbum: Album? by mutableStateOf(null) // clicked on album
    var selectedTrack: ISearch? by mutableStateOf(null) // clicked on song
    var selectedRadio: Radio? by mutableStateOf(null) // clicked on radio
    var playlist: List<ISearch> by mutableStateOf(emptyList())
    var searchedFavorites: List<ISearch> by mutableStateOf(emptyList())


    /* Search functions */
    fun searchAlbum(searchFilter: String) {
        foundAlbumsList = emptyList()
        isLoading = true

        modelScope.launch(Dispatchers.IO){
            try {
                val tempList = service.searchAlbum(searchFilter)

                val tempListData = tempList.data
                    .filter { a -> a.album != null }
                    .distinctBy { a -> a.album?.id }
                val albums = mutableListOf<Album>()
                tempListData.forEach {
                    val currentAlbum = it.album!!
                    currentAlbum.artist = it.artist
                    loadSmallCover(album = currentAlbum)
                    albums.add(element = currentAlbum)
                }
                foundAlbumsList = albums
            } catch (e: Exception) {
                Log.d("Search album", e.message.toString())
            } finally {
                isLoading = false
            }
        }
    }

    fun searchTrack(searchFilter: String) { // Tab Song
        foundTracksList = emptyList()
        isLoading = true

        modelScope.launch(Dispatchers.IO) {
            try {
                val tempList = service.searchTrack(searchFilter)

                val tempListData = tempList.data
                tempListData.forEach {
                    val currentAlbum = it.album
                    if (currentAlbum != null) {
                        loadSmallCover(album = currentAlbum)
                    }
                }
                foundTracksList = tempListData
            } catch (e: Exception) {
                Log.d("Search track", e.message.toString())
            } finally {
                isLoading = false
            }
        }
    }

    fun searchFavorites(searchFilter: String) : List<ISearch>{
        val filteredList: MutableList<ISearch> = mutableStateListOf()

        if(searchFilter.isBlank()){
            return filteredList
        } else {
            searchedFavorites = filteredList
                .filter { it.title!!.contains(searchFilter) } as MutableList<ISearch>
             return filteredList
        }
    }


    /* Player */
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

    fun startPlayer() {
        playerIsReady = false
        try {
            if (isLoaded && !player.isPlaying) {
                player.start()
            } else {
                player.reset()
                player.setDataSource(selectedTrack?.preview)
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

    fun playNext() {
        val trackIndex = playlist.indexOf(selectedTrack)
        if (trackIndex >= 0) {
            if (trackIndex != playlist.count() -1) {
                openPlayer(playlist[trackIndex + 1], playlist)
            } else {
                openPlayer(playlist[0], playlist)
            }
        }
    }

    /* Open functions */
    fun openPlayer(track: ISearch, currentList: List<ISearch>) {
        playlist = currentList.toList()
        if (track.album != null && track.album?.coverImageBig == DEFAULT_IMAGE) {
            loadBigCover(album = track.album!!)
        }
        if (selectedTrack?.id != track.id) {
            selectedTrack = track
            isLoaded = false
            startPlayer()
        }
        currentScreen = Screen.PLAYER
    }

    fun openAlbum(album: Album) {
        if (selectedAlbum?.id != album.id) {
            selectedAlbum = album
        }
        modelScope.launch {
            try {
                val tracks = album.tracklist?.let { service.requestTracks(it) }
                if (tracks?.data != null && selectedAlbum != null) {
                    selectedAlbum!!.tracks = tracks.data
                    selectedAlbum!!.tracks.forEach {
                        it.album = album
                        loadBigCover(album)
                    }
                }
            } catch (e: Exception) {
                Log.d("Open album", e.message.toString())
            }
        }
        currentScreen = Screen.ALBUMDETAILS
    }

    fun openRadio(radio: Radio) {
        selectedRadio = radio
        isLoading = true

        modelScope.launch {
            try {
                val tracks = radio.tracklist?.let { service.requestTracks(it) }
                if (tracks?.data != null) {
                    foundTracksList = tracks.data
                    foundTracksList.forEach {
                        if (it.album != null) {
                            loadBigCover(radio)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("Open radio", e.message.toString())
                foundTracksList = emptyList()
            } finally {
                isLoading = false
            }
        }
        currentScreen = Screen.RADIODETAILS
    }

    fun loadRadios(): List<Radio> {
        if (radioStations.count() <= 0) {
            isLoading = true
            modelScope.launch {
                try {
                    radioStations = service.requestRadios().data

                    radioStations.forEach { radio ->
                        run {
                            loadSmallCover(radio)
                        }
                    }
                } catch (e: Exception) {}
                finally {
                    isLoading = false
                }
            }
        }
        return radioStations
    }


    /* Favorites */
    fun addToFavorites(track: ISearch?) {
        if (track != null && !favoriteTracksList.contains(track)) {
            favoriteTracksList.add(track)
        }
    }

    fun getFavoritesList(): MutableList<ISearch>  {
        return favoriteTracksList
    }

    fun isInFavorites(track: ISearch?): Boolean {
        return favoriteTracksList.contains(track)
    }

    fun removeFromFavorites(track: ISearch?) {
        favoriteTracksList.remove(track)
    }

    /* Images */
    private fun loadBigCover(album: Album) {
        modelScope.launch {
            if (album.coverXl != null) {
                try {
                    album.coverImageBig = bitmap(album.coverXl).asImageBitmap()
                } catch (e: Exception) {
                    album.coverImageBig = DEFAULT_IMAGE
                }
            } else if (album.coverBig != null) {
                try {
                    album.coverImageBig = bitmap(album.coverBig).asImageBitmap()
                } catch (e: Exception) {
                    album.coverImageBig = DEFAULT_IMAGE
                }
            }
        }
    }

    private fun loadBigCover(radio: Radio) {
        modelScope.launch {
            if (radio.pictureXl != null) {
                try {
                    radio.radioPictureBig = bitmap(radio.pictureXl).asImageBitmap()
                } catch (e: Exception) {
                    e.message?.let { Log.d("LoadImage", it) }
                    radio.radioPictureBig = DEFAULT_IMAGE
                }
            } else if (radio.pictureBig != null) {
                try {
                    radio.radioPictureBig = bitmap(radio.pictureBig).asImageBitmap()
                } catch (e: Exception) {
                    e.message?.let { Log.d("LoadImage", it) }
                    radio.radioPictureBig = DEFAULT_IMAGE
                }
            }
        }
    }


    private fun loadSmallCover(album: Album) {
        modelScope.launch {
            if (album.cover != null) {
                try {
                    album.coverImageSmall = bitmap(album.cover).asImageBitmap()
                } catch (e: Exception) {
                    e.message?.let { Log.d("LoadImage", it) }
                    album.coverImageSmall = DEFAULT_IMAGE
                }
            }
        }
    }

    private fun loadSmallCover(radio: Radio) {
        modelScope.launch {
            if (radio.picture != null) {
                try {
                    radio.radioPictureSmall = bitmap(radio.picture).asImageBitmap()
                } catch (e: Exception) {
                    e.message?.let { Log.d("LoadImage", it) }
                    radio.radioPictureSmall = DEFAULT_IMAGE
                }
            }
        }
    }





}