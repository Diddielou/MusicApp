package fhnw.emoba.freezerapp.data

import androidx.compose.runtime.mutableStateOf
import org.json.JSONArray
import org.json.JSONObject

data class Album(val json: JSONObject) {

    val id = json.getInt("id")
    val title  = json.getString("title")
    val share = json.getString("share")
    val cover_small= json.getString("cover_small") // URL
    val cover_medium = json.getString("cover_medium") // URL
    val cover_big = json.getString("cover_big") // URL
    val cover_XL  = json.getString("cover_XL") // URL
    val available = json.getBoolean("available")
    val artist = Artist(json.getJSONObject("artist"))
    val tracks = tracks(json.getJSONArray("tracks"))

    //var coverImage by mutableStateOf(DEFAULT_IMAGE)
    //var coverImageBig by mutableStateOf(DEFAULT_IMAGE)

    private fun tracks(trackList: JSONArray) : List<Track>{
        val list: MutableList<Track> = mutableListOf()
        for (i in 0 until trackList.length()) {
            list.add(Track(trackList.getJSONObject(i)))
        }
        return list
    }

}
