package fhnw.emoba.freezerapp.data.impl

import fhnw.emoba.freezerapp.data.*
import org.json.JSONObject

class RemoteFreezerService : IFreezerService {

    val BASE_URL = "https://api.deezer.com"

    /* Search functions */
    override fun searchAlbum(searchString: String): SearchWrapper<Search> {
        return basicSearch(searchString, "album")
    }

    override fun searchTrack(searchString: String): SearchWrapper<Search> {
        return basicSearch(searchString, "track")
    }

    private fun basicSearch(searchString: String, type: String): SearchWrapper<Search> {
        return try {
            val url = "$BASE_URL/search?q=$type:\"$searchString\""

            SearchWrapper(content(url)) { Search(it) }
        } catch (e: Exception) {
            SearchWrapper(JSONObject()) { Search(it) }
        }
    }

    // only for tests
    override fun searchEverything(searchString: String): SearchWrapper<Search> {
        return try {
            val url = "$BASE_URL/search?q=$searchString"

            SearchWrapper(content(url)) { Search(it) }
        } catch (e: Exception) {
            SearchWrapper(JSONObject()) { Search(it) }
        }
    }

    /*  Get objects directly */
    // for opening album or radio
    override fun requestTracks(url: String): SearchWrapper<Track> {
        return try {
            SearchWrapper(content(url)) { Track(it) }
        } catch (e: Exception) {
            SearchWrapper(JSONObject()) { Track(it) }
        }
    }

    // to load radios
    override fun requestRadios(): SearchWrapper<Radio> {
        return try {
            val url = "$BASE_URL/radio/"

            SearchWrapper(content(url)) { Radio(it) }
        } catch (e: Exception) {
            SearchWrapper(JSONObject()) { Radio(it) }
        }
    }

    // only for tests
    override fun requestAlbum(id: Int): Album {
        return try {
            val url = "$BASE_URL/album/$id"
            Album(content(url))
        } catch (e: Exception) {
            Album(JSONObject())
        }
    }


}
