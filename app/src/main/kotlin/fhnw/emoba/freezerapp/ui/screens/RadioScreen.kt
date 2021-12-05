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

/**  When clicked on a Radio Station in the Radio tab / list */

/** When clicked on an album in the Album tab */
@ExperimentalMaterialApi
@Composable
fun RadioScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { GeneralTopBar(model, Screen.RADIODETAILS.title, Screen.MAIN, scaffoldState = scaffoldState) },
        content = { Body(model) }
    )
}


@ExperimentalMaterialApi
@Composable
private fun Body(model: FreezerModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier               = Modifier
            .fillMaxSize()
            .padding(10.dp, 20.dp, 20.dp, 0.dp))
    {
        Header(model = model) // Column
        RadioTracksList(model = model) // LazyColumn
    }
}


@Composable
private fun Header(model: FreezerModel) {
    Column(verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 20.dp)) {
        RadioCover(model = model) // Row
        RadioDetails(model = model) // Row
    }
}

/**  Radio cover */
@Composable
private fun RadioCover(model: FreezerModel) {
    with(model) {
        Row(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp)){
            if(isLoading){
                LoadingIndicator()
            } else {
                selectedRadio?.let { RadioImageBig(it) }
            }
        }
    }
}

/**  Radio title */
@Composable
private fun RadioDetails(model: FreezerModel) {
    with(model) {
        Row {
            selectedRadio?.title?.let { Heading1(it) }
        }
    }
}

/** List with all tracks in this radio */
@ExperimentalMaterialApi
@Composable
private fun RadioTracksList(model: FreezerModel) {
    val state = rememberLazyListState()
    with(model) {
        if (isLoading) {
            LoadingIndicator()
        } else {
            LazyColumn(state = state) {
                items(model.foundTracksList) { RadioTrackRow(model = model, track = it) // {  it.album?.let { it -> AlbumCoverSmall(album = it) } }
                }
            }
        }
    }
}

/** One row in the radio tracks list */
@ExperimentalMaterialApi
@Composable
private fun RadioTrackRow(model: FreezerModel, track: ISearch) { // , image: @Composable () -> Unit
    with(track){
        ListItem(
            modifier = Modifier.clickable(onClick = {
                model.openPlayer(track, model.foundTracksList)
                model.currentScreen = Screen.PLAYER
            }),
            // icon = image, // TODO should first be fixed before release
            text = { Text(text = title!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
            secondaryText = { Text(album!!.title!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
            overlineText = { Text(artist!!.name!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
            trailing = { FavoriteIcon(model = model, track = track) }
        )
    }
    Divider()
}
