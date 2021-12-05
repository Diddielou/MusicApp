package fhnw.emoba.freezerapp.data

import org.json.JSONObject

data class Artist(val json: JSONObject?) {
    val id = json?.optString("id")
    val name = json?.optString("name")
    val album = json?.optInt("nb_album")
    val radio = json?.optBoolean("radio")

    // unused fields
    val link = json?.optString("link")
    val share = json?.optString("share")
    val picture = json?.optString("picture")
    val pictureSmall = json?.optString("picture_small")
    val pictureMedium = json?.optString("picture_medium")
    val pictureBig = json?.optString("picture_big")
    val pictureXl = json?.optString("picture_xl")
    val fans = json?.optInt("nb_fan")
    val tracklist = json?.optString("tracklist")

    constructor(jsonString: String?) : this(JSONObject(jsonString!!))
}