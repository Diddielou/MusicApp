package fhnw.emoba.freezerapp.data

import org.json.JSONObject

data class Track(val json: JSONObject) {

    val id              = json.getInt("id")
    val readable        = json.getBoolean("readable")
    val title           = json.getString("title")
    val share           = json.getString("share")
    val duration        = json.getInt("duration")
    val track_position  = json.getInt("track_position")
    val preview         = json.getString("preview")
    val artist          = Artist(json.getJSONObject("artist"))
    val album           = Album(json.getJSONObject("album"))

/*
    var artist = Artist(json?.optJSONObject("artist"))
    var tracks by mutableStateOf(emptyList<Track>())
    var coverImage by mutableStateOf(DEFAULT_IMAGE)
    var coverImageBig by mutableStateOf(DEFAULT_IMAGE)
 */
}
