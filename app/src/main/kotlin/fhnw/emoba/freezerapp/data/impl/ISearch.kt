package fhnw.emoba.freezerapp.data.impl

import fhnw.emoba.freezerapp.data.Album
import fhnw.emoba.freezerapp.data.Artist
import org.json.JSONObject

abstract class ISearch(override val json: JSONObject?) : IJSON(json) {
    abstract val id: Int?
    abstract val readable: Boolean?
    abstract val title: String?
    abstract val titleShort: String?
    abstract val titleVersion: String?
    abstract val link: String?
    abstract val duration: Int?
    abstract val rank: Int?
    abstract val explicitLyrics: Boolean?
    abstract val preview: String? // mp3
    abstract val artist: Artist?
    abstract val album: Album?

    override fun equals(other: Any?): Boolean {
        if (other is ISearch) {
            return id == other.id
        }

        return false
    }
}