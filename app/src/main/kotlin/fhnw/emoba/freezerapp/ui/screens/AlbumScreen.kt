package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.impl.ISearch
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen


/** When clicked on an album in the Album tab */
@ExperimentalMaterialApi
@Composable
fun AlbumScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { GeneralTopBar(model, Screen.ALBUMDETAILS.title, Screen.MAIN, scaffoldState = scaffoldState) },
        content = { Body(model) }
    )
}

@ExperimentalMaterialApi
@Composable
private fun Body(model: FreezerModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier            = Modifier
            .fillMaxSize()
            .padding(10.dp, 20.dp, 20.dp, 0.dp))
    {
        Header(model = model) // Column
        AlbumTracksList(model = model) // LazyColumn
    }
}


@Composable
private fun Header(model: FreezerModel) {
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 20.dp)) {
        AlbumCover(model = model) // Row
        AlbumDetails(model = model) // Row
    }
}


/**  Album cover */
@Composable
private fun AlbumCover(model: FreezerModel) {
    with(model) {
        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp)){
            if(isLoading){
                LoadingIndicator()
            } else {
                selectedAlbum?.let { AlbumCoverMedium(it) }
            }
        }
    }
}

/**  Album title and details */
@Composable
private fun AlbumDetails(model: FreezerModel) {
    with(model) {
        Row {
            selectedAlbum?.title?.let { Heading1(it) }
        }
        Row {
            selectedAlbum?.artist?.name?.let { Heading2(text = it) }
        }
    }
}

/** List with all tracks in this album */
@ExperimentalMaterialApi
@Composable
private fun AlbumTracksList(model: FreezerModel) {
    val state = rememberLazyListState()
    with(model) {
        if (isLoading) {
            LoadingIndicator()
        } else {
            LazyColumn(state = state) {
                model.selectedAlbum?.let { album ->
                    items(album.tracks){
                        AlbumTrackRow(model, it)
                    }
                }
            }
        }
    }
}

/** One row in the album tracks list */
@ExperimentalMaterialApi
@Composable
private fun AlbumTrackRow(model: FreezerModel, track: ISearch) {
    with(track){
        ListItem(
            modifier = Modifier.clickable(onClick = {
                model.selectedAlbum?.tracks?.let { model.openPlayer(track, it) }
                model.currentScreen = Screen.PLAYER
            }),
            text = { title?.let { Text(it, overflow = TextOverflow.Ellipsis, maxLines = 1) } },
            secondaryText = { model.selectedAlbum?.artist?.name?.let { Text(it) } },
            trailing = { FavoriteIcon(model = model, track = track) }
        )
    }
    Divider()
}


