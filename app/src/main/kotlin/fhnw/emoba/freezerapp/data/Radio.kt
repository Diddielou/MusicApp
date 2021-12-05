package fhnw.emoba.freezerapp.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import fhnw.emoba.freezerapp.data.impl.DEFAULT_IMAGE
import fhnw.emoba.freezerapp.data.impl.IJSON
import org.json.JSONObject

data class Radio(override val json: JSONObject?) : IJSON(json) {
    val id = json?.optInt("id")
    val title = json?.optString("title")
    val description = json?.optString("description") // URL
    val picture = json?.optString("picture") // URL
    val pictureBig = json?.optString("picture_big") // URL
    val pictureXl = json?.optString("picture_xl") // URL
    val tracklist = json?.optString("tracklist") // URL
    var tracks by mutableStateOf(emptyList<Track>())
    var radioPictureSmall by mutableStateOf(DEFAULT_IMAGE)
    var radioPictureBig by mutableStateOf(DEFAULT_IMAGE)

    // unused fields
    val md5Image = json?.optString("md5_image")
    val pictureSmall = json?.optString("picture_small") // URL
    val pictureMedium = json?.optString("picture_medium") // URL
    val share = json?.optString("share")

    constructor(jsonString: String?) : this(JSONObject(jsonString!!)) {
        val tracks = json?.optJSONArray("tracks")?.map { Track(it) }

        if (tracks != null) {
            this.tracks = tracks
        }
    }

}