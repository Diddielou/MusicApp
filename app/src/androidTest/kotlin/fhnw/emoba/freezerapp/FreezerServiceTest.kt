package fhnw.emoba.freezerapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import fhnw.emoba.freezerapp.data.impl.RemoteFreezerService

import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FreezerServiceTest {

    @Test
    fun testRequestAlbum(){
        // given
        val service = RemoteFreezerService()
        val albumId = 355921

        // when
        val getAlbum = service.requestAlbum(albumId)

        // then
        assertEquals("xx", getAlbum.title)
        assertEquals("The xx", getAlbum.artist?.name)
    }

    @Test
    fun testRequestTracks(){
        // given
        val service = RemoteFreezerService()
        val tracksFromURL = "https://api.deezer.com/album/355921/tracks"

        // when
        val getTracks = service.requestTracks(tracksFromURL).data

        // then
        assertEquals("Intro", getTracks.first().title)
        assertEquals("The xx", getTracks.first().artist?.name )
    }

    @Test
    fun testRequestRadios(){
        // given
        val service = RemoteFreezerService()

        // when
        val getRadios = service.requestRadios().data

        // then
        assertEquals(37151, getRadios.first().id)
        assertEquals("Hits", getRadios.first().title)
    }


    @Test
    fun testSearchAlbum(){
        // given
        val service = RemoteFreezerService()

        // when
        val searchAlbum = service.searchAlbum("Yours Conditionally").data

        // then
        assertEquals("Tennis", searchAlbum.first().artist?.name)
        assertEquals("Yours Conditionally", searchAlbum.first().album?.title)
    }

    @Test
    fun testSearchTrack(){
        // given
        val service = RemoteFreezerService()

        // when
        val searchTrack = service.searchTrack("In The Morning I'll Be Better").data

        // then
        assertEquals("In The Morning I’ll Be Better", searchTrack.first().album?.title)
        assertEquals("Tennis", searchTrack.first().artist?.name)
    }


    @Test
    fun testSearchEverything(){
        // given
        val service = RemoteFreezerService()

        // when
        val searchTrack = service.searchEverything("i need a dollar").data
        val searchAlbum = service.searchEverything("oh wonder").data
        val searchArtist = service.searchEverything("the marias").data

        // then
        assertEquals("Aloe Blacc", searchTrack.first().artist?.name)
        assertEquals("Good Things", searchTrack.first().album?.title)

        assertEquals("Best Of 2021: Indie Alternative", searchAlbum.first().album?.title)
        assertEquals("Oh Wonder", searchAlbum.first().artist?.name)

        assertEquals("The Mar\u00edas", searchArtist.first().artist?.name)
        assertEquals("Hush", searchArtist.first().title)
        assertEquals("https://www.deezer.com/track/1328724552", searchArtist.first().link)
    }

}