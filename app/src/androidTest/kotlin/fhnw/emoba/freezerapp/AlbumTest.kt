package fhnw.emoba.freezerapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import fhnw.emoba.freezerapp.data.Album
import org.json.JSONObject

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AlbumTest {

    private lateinit var albumAsString: String

    @Before
    fun setUp(){

        albumAsString = """
            {
            		"id": "14839831",
            		"title": "Yours Conditionally",
            		"upc": "659061672741",
            		"link": "https://www.deezer.com/album/14839831",
            		"share": "https://www.deezer.com/album/14839831?utm_source=deezer&utm_content=album-14839831&utm_term=0_1638463372&utm_medium=web",
            		"cover": "https://api.deezer.com/album/14839831/image",
            		"cover_small": "https://e-cdns-images.dzcdn.net/images/cover/23355ea5fefbe122814b940b3acbac04/56x56-000000-80-0-0.jpg",
            		"cover_medium": "https://e-cdns-images.dzcdn.net/images/cover/23355ea5fefbe122814b940b3acbac04/250x250-000000-80-0-0.jpg",
            		"cover_big": "https://e-cdns-images.dzcdn.net/images/cover/23355ea5fefbe122814b940b3acbac04/500x500-000000-80-0-0.jpg",
            		"cover_xl": "https://e-cdns-images.dzcdn.net/images/cover/23355ea5fefbe122814b940b3acbac04/1000x1000-000000-80-0-0.jpg",
            		"md5_image": "23355ea5fefbe122814b940b3acbac04",
            		"genre_id": 85,
            		"genres": {
            		"data": [
            		{
            			"id": 85,
            			"name": "Alternative",
            			"picture": "https://api.deezer.com/genre/85/image",
            			"type": "genre"
            		}
            		]
            	},
            		"label": "Mutually Detrimental",
            		"nb_tracks": 10,
            		"duration": 2177,
            		"fans": 821,
            		"release_date": "2017-03-10",
            		"record_type": "album",
            		"available": true,
            		"tracklist": "https://api.deezer.com/album/14839831/tracks",
            		"explicit_lyrics": false,
            		"explicit_content_lyrics": 0,
            		"explicit_content_cover": 2,
            		"contributors": [
            		{
            			"id": 127257,
            			"name": "Tennis",
            			"link": "https://www.deezer.com/artist/127257",
            			"share": "https://www.deezer.com/artist/127257?utm_source=deezer&utm_content=artist-127257&utm_term=0_1638463372&utm_medium=web",
            			"picture": "https://api.deezer.com/artist/127257/image",
            			"picture_small": "https://e-cdns-images.dzcdn.net/images/artist/5cfd553f0481a8437d2866c8a81df127/56x56-000000-80-0-0.jpg",
            			"picture_medium": "https://e-cdns-images.dzcdn.net/images/artist/5cfd553f0481a8437d2866c8a81df127/250x250-000000-80-0-0.jpg",
            			"picture_big": "https://e-cdns-images.dzcdn.net/images/artist/5cfd553f0481a8437d2866c8a81df127/500x500-000000-80-0-0.jpg",
            			"picture_xl": "https://e-cdns-images.dzcdn.net/images/artist/5cfd553f0481a8437d2866c8a81df127/1000x1000-000000-80-0-0.jpg",
            			"radio": true,
            			"tracklist": "https://api.deezer.com/artist/127257/top?limit=50",
            			"type": "artist",
            			"role": "Main"
            		}
            		],
            		"artist": {
            		"id": "127257",
            		"name": "Tennis",
            		"picture": "https://api.deezer.com/artist/127257/image",
            		"picture_small": "https://e-cdns-images.dzcdn.net/images/artist/5cfd553f0481a8437d2866c8a81df127/56x56-000000-80-0-0.jpg",
            		"picture_medium": "https://e-cdns-images.dzcdn.net/images/artist/5cfd553f0481a8437d2866c8a81df127/250x250-000000-80-0-0.jpg",
            		"picture_big": "https://e-cdns-images.dzcdn.net/images/artist/5cfd553f0481a8437d2866c8a81df127/500x500-000000-80-0-0.jpg",
            		"picture_xl": "https://e-cdns-images.dzcdn.net/images/artist/5cfd553f0481a8437d2866c8a81df127/1000x1000-000000-80-0-0.jpg",
            		"tracklist": "https://api.deezer.com/artist/127257/top?limit=50",
            		"type": "artist"
            	},
            		"type": "album",
            		"tracks": {
            		"data": [ ]
            	}
            }
            """.trimIndent()

    }

    @Test
    fun testConstructor() {
        //given
        val json = JSONObject(albumAsString)

        //when
        val result = Album(json)

        //then
        with(result) {
            assertEquals(14839831, id)
            assertEquals("Yours Conditionally", title)
            assertEquals("https://api.deezer.com/album/14839831/image", cover)
            assertEquals("Tennis", artist!!.name)
        }
    }

    @Test
    fun testSecondConstructor() {
        //given
        val json = JSONObject(albumAsString)

        //when
        val result = Album(json)

        //then
        with(result) {
            assertEquals(14839831, id)
            assertEquals("Yours Conditionally", title)
            assertEquals("https://api.deezer.com/album/14839831/image", cover)
            assertEquals("Tennis", artist!!.name)
        }
    }

}