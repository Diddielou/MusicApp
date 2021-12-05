package fhnw.emoba.freezerapp.data

import fhnw.emoba.freezerapp.data.impl.IJSON
import org.json.JSONObject

/** For parts of the backend I called upon a fellow student's assistance. */
data class SearchWrapper<T>(val json: JSONObject, val factory: (json: JSONObject) -> T) where T : IJSON {
    val data = json.optJSONArray("data").map { factory(it) }
    val total = json.optInt("total")
    val next = json.optString("next")

    constructor(jsonString: String, factory: (json: JSONObject) -> T) : this(JSONObject(jsonString), factory)
}
