package fhnw.emoba.freezerapp.data

import org.json.JSONObject

data class Radio(val json: JSONObject) {
    val id = json.getInt("id")
    val title = json.getString("title")
    val description = json.getString("description") // URL
    val picture_small = json.getString("picture_small") // URL
    val picture_medium = json.getString("picture_medium") // URL
    val picture_big  = json.getString("picture_big") // URL
    val picture_xl = json.getString("picture_xl") // URL
    val tracklist = json.getString("tracklist") // URL
}