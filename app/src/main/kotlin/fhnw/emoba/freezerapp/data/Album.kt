package fhnw.emoba.freezerapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.impl.DEFAULT_IMAGE
import fhnw.emoba.freezerapp.data.impl.IJSON
import org.json.JSONObject

data class Album(override val json: JSONObject?) : IJSON(json) {
    val id = json?.optInt("id")
    val title = json?.optString("title")
    val cover = json?.optString("cover")
    val coverSmall = json?.optString("cover_small")
    val coverBig = json?.optString("cover_big")
    val coverXl = json?.optString("cover_xl")
    val tracklist = json?.optString("tracklist")
    var tracks by mutableStateOf(emptyList<Track>())
    var coverImageSmall by mutableStateOf(DEFAULT_IMAGE)
    var coverImageBig by mutableStateOf(DEFAULT_IMAGE)
    var artist = json?.optJSONObject("artist")?.let { Artist(it) }

    // unused fields
    val upc = json?.optString("upc")
    val link = json?.optString("link")
    val share = json?.optString("share")
    val coverMedium = json?.optString("cover_medium")
    val md5Image = json?.optString("md5_image")
    val genreId = json?.optInt("genre_id")
    val label = json?.optString("label")
    val nbTracks = json?.optInt("nb_tracks")
    val duration = json?.optInt("duration")
    val fans = json?.optInt("fans")
    val rating = json?.optInt("rating")
    val releaseDate = json?.optString("release_date")
    val recordType = json?.optString("record_type")
    val available = json?.optBoolean("available")
    val explicitLyrics = json?.optBoolean("explicit_lyrics")
    val explicitContentLyrics = json?.optInt("explicit_content_lyrics")
    val explicitContentCover = json?.optInt("explicit_content_cover")

    constructor(jsonString: String?) : this(JSONObject(jsonString!!)) {
        val tracks = json?.optJSONArray("tracks")?.map { Track(it) }

        if (tracks != null) {
            this.tracks = tracks
        }
    }

}
