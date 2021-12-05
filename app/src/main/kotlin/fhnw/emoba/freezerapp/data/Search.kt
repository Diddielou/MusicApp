package fhnw.emoba.freezerapp.data

import fhnw.emoba.freezerapp.data.impl.ISearch
import org.json.JSONObject

class Search(override val json: JSONObject?) : ISearch(json) {
    override val id = json?.optInt("id")
    override val readable = json?.optBoolean("readable")
    override val title = json?.optString("title")
    override val titleShort = json?.optString("title_short")
    override val titleVersion = json?.optString("title_version")
    override val link = json?.optString("link")
    override val duration = json?.optInt("duration")
    override val rank = json?.optInt("rank")
    override val explicitLyrics = json?.optBoolean("explicit_lyrics")
    override val preview = json?.optString("preview")
    override val artist = json?.optJSONObject("artist")?.let { Artist(it) }
    override val album = json?.optJSONObject("album")?.let { Album(it) }

    // for tests
    override fun equals(other: Any?): Boolean {
        if (other is ISearch) {
            return id == other.id
        }
        return false
    }

}