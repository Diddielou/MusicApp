package fhnw.emoba.freezerapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import fhnw.emoba.freezerapp.data.Track
import org.json.JSONObject

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TrackTest {

    private lateinit var trackAsString: String

    @Before

    fun setUp(){

        trackAsString = """
            {
               "id":138217489,
               "readable":true,
               "title":"My Emotions Are Blinding",
               "title_short":"My Emotions Are Blinding",
               "title_version":"",
               "isrc":"QZ5TF1600002",
               "link":"https:\/\/www.deezer.com\/track\/138217489",
               "share":"https:\/\/www.deezer.com\/track\/138217489?utm_source=deezer&utm_content=track-138217489&utm_term=0_1638472984&utm_medium=web",
               "duration":221,
               "track_position":2,
               "disk_number":1,
               "rank":105971,
               "release_date":"2017-03-10",
               "explicit_lyrics":false,
               "explicit_content_lyrics":0,
               "explicit_content_cover":2,
               "preview":"https:\/\/cdns-preview-1.dzcdn.net\/stream\/c-11bbacd87b50f8f1e1cb68cc426ef0c9-4.mp3",
               "bpm":95,
               "gain":-7.8,
               "md5_image":"23355ea5fefbe122814b940b3acbac04",
               "artist":{
                  "id":127257,
                  "name":"Tennis",
                  "link":"https:\/\/www.deezer.com\/artist\/127257",
                  "share":"https:\/\/www.deezer.com\/artist\/127257?utm_source=deezer&utm_content=artist-127257&utm_term=0_1638472984&utm_medium=web",
                  "picture":"https:\/\/api.deezer.com\/artist\/127257\/image",
                  "picture_small":"https:\/\/e-cdns-images.dzcdn.net\/images\/artist\/5cfd553f0481a8437d2866c8a81df127\/56x56-000000-80-0-0.jpg",
                  "picture_medium":"https:\/\/e-cdns-images.dzcdn.net\/images\/artist\/5cfd553f0481a8437d2866c8a81df127\/250x250-000000-80-0-0.jpg",
                  "picture_big":"https:\/\/e-cdns-images.dzcdn.net\/images\/artist\/5cfd553f0481a8437d2866c8a81df127\/500x500-000000-80-0-0.jpg",
                  "picture_xl":"https:\/\/e-cdns-images.dzcdn.net\/images\/artist\/5cfd553f0481a8437d2866c8a81df127\/1000x1000-000000-80-0-0.jpg",
                  "radio":true,
                  "tracklist":"https:\/\/api.deezer.com\/artist\/127257\/top?limit=50",
                  "type":"artist"
               },
               "album":{
                  "id":14839831,
                  "title":"Yours Conditionally",
                  "link":"https:\/\/www.deezer.com\/album\/14839831",
                  "cover":"https:\/\/api.deezer.com\/album\/14839831\/image",
                  "cover_small":"https:\/\/e-cdns-images.dzcdn.net\/images\/cover\/23355ea5fefbe122814b940b3acbac04\/56x56-000000-80-0-0.jpg",
                  "cover_medium":"https:\/\/e-cdns-images.dzcdn.net\/images\/cover\/23355ea5fefbe122814b940b3acbac04\/250x250-000000-80-0-0.jpg",
                  "cover_big":"https:\/\/e-cdns-images.dzcdn.net\/images\/cover\/23355ea5fefbe122814b940b3acbac04\/500x500-000000-80-0-0.jpg",
                  "cover_xl":"https:\/\/e-cdns-images.dzcdn.net\/images\/cover\/23355ea5fefbe122814b940b3acbac04\/1000x1000-000000-80-0-0.jpg",
                  "md5_image":"23355ea5fefbe122814b940b3acbac04",
                  "release_date":"2017-03-10",
                  "tracklist":"https:\/\/api.deezer.com\/album\/14839831\/tracks",
                  "type":"album"
               },
               "type":"track"
            }
            """.trimIndent()

    }

    @Test
    fun testConstructor() {
        //given
        val json = JSONObject(trackAsString)

        //when
        val result = Track(json)

        //then
        with(result) {
            assertEquals(138217489, id)
            assertEquals("My Emotions Are Blinding", title)
            assertEquals("https://api.deezer.com/album/14839831/image", album!!.cover)
            assertEquals("Tennis", artist!!.name)
        }
    }

    @Test
    fun testSecondConstructor() {
        //given
        val json = JSONObject(trackAsString)

        //when
        val result = Track(json)

        //then
        with(result) {
            assertEquals(138217489, id)
            assertEquals("My Emotions Are Blinding", title)
            assertEquals("https://api.deezer.com/album/14839831/image", album!!.cover)
            assertEquals("Tennis", artist!!.name)
        }
    }

}