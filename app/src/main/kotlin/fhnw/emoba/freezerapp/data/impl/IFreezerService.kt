package fhnw.emoba.freezerapp.data.impl

import android.graphics.Bitmap
import androidx.compose.ui.graphics.asImageBitmap
import fhnw.emoba.freezerapp.data.*

val DEFAULT_IMAGE = Bitmap.createBitmap(
    500,
    500,
    Bitmap.Config.ALPHA_8).asImageBitmap()

interface IFreezerService {

    // Search
    fun searchEverything(searchString: String): SearchWrapper<Search>
    fun searchAlbum(searchString: String): SearchWrapper<Search> // searchAlbum
    fun searchTrack(searchString: String): SearchWrapper<Search> // searchTrack

    // get directly
    fun requestAlbum(id: Int): Album // for tests
    fun requestTracks(url: String): SearchWrapper<Track> // openRadio, openAlbum
    fun requestRadios(): SearchWrapper<Radio> // loadRadios (All on Deezer)

}