package fhnw.emoba.freezerapp.data.impl

import fhnw.emoba.freezerapp.data.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RemoteMusicService : MusicService {

    val BASE_URL = "https://api.deezer.com" // "https://api.openweathermap.org/data/2.5/onecall"
    /*

    override fun requestMusic(searchTerm: String): List<Artist> {
        val string = searchTerm.toString()

        val ARTIST_URL = "$BASE_URL/$string/"
        try {
            val url = URL(ARTIST_URL)
            val connection = url.openConnection() as HttpsURLConnection
            connection.connect()

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val jsonString = reader.readText()
            reader.close()

            return Artist(JSONObject(jsonString))

        } catch (e: Exception) {
            return emptyList()
            // doSomething
            // return Artist(artist, JSONObject())
            // return Forecast(city, JSONObject())
        }
    }

    override fun requestMusic(searchTerm: String): List<Album> {
        val string = album.toString()

        val ALBUM_URL = "$BASE_URL/$string/"
        try {
            val url = URL(ALBUM_URL)
            val connection = url.openConnection() as HttpsURLConnection
            connection.connect()

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val jsonString = reader.readText()
            reader.close()

            return Album(JSONObject(jsonString))

        } catch (e: Exception) {
            return emptyList()
            // doSomething
        }
    }

    override fun requestMusic(searchTerm: String): List<Track> {
        val string = track.toString()

        val TRACK_URL = "$BASE_URL/$string/"
        try {
            val url = URL(TRACK_URL)
            val connection = url.openConnection() as HttpsURLConnection
            connection.connect()

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val jsonString = reader.readText()
            reader.close()

            return Track(JSONObject(jsonString))

        } catch (e: Exception) {
            return emptyList()
            // doSomething
        }
    }


    override fun requestMusic(searchTerm: String): List<Radio> {
        val string = radio.toString()

        val RADIO_URL = "$BASE_URL/$string/"
        try {
            val url = URL(RADIO_URL)
            val connection = url.openConnection() as HttpsURLConnection
            connection.connect()

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val jsonString = reader.readText()
            reader.close()

            return Radio(JSONObject(jsonString))

        } catch (e: Exception) {
            return emptyList()
            // doSomething
        }
    }

     */

}

    // val apiKey    = "da050e1da82bb32f9846b2340d0d2b75"
    /*
    static const BASE_URL = 'https://api.deezer.com';
  static const GENRE_URL = "$BASE_URL/genres";
  static const _SONG_URL = "$BASE_URL/track";
  static const _ALBUM_URL = "$BASE_URL/album";
  static const _CHART_URL = "$BASE_URL/chart";
  static const _ARTIST_URL = "$BASE_URL/artist";
  static const _SEARCH_URL = "$BASE_URL/search";
  static const JWT_KEY = 'access_token';
     */

    /*


     */