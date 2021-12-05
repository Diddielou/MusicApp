package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.impl.RemoteFreezerService
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen


@Composable
fun PlayerScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { GeneralTopBar(model, Screen.PLAYER.title, Screen.MAIN, scaffoldState = scaffoldState) },
        content = { Body(model) }
    )
}


@Composable
private fun Body(model: FreezerModel) {
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally,
           modifier            = Modifier
               .fillMaxSize()
               .padding(40.dp, 25.dp, 40.dp, 40.dp))
    {
        SongView(model) // Row
        PlayerControls(model) // Row
    }
}


@Composable
private fun SongView(model: FreezerModel){
    Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
    //Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.Top){
        AlbumCover(model = model) // Row
        SongInformation(model = model) // Row
    }
}

@Composable
private fun AlbumCover(model: FreezerModel) {
    with(model) {
        Row{
            selectedTrack?.album?.let { AlbumCoverBig(it) }
        }
    }
}

@Composable
private fun SongInformation(model: FreezerModel) {
    with(model) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth()
                .requiredHeight(190.dp)
        ){
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(modifier = Modifier.fillMaxWidth()
                        .padding(0.dp)
                        .height(28.dp)
                ){
                    Column(horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()) {
                        selectedTrack?.let { FavoriteIcon(model = model, track = it) }
                    }
                }
                Row{
                    Column(horizontalAlignment = Alignment.Start,
                           modifier = Modifier.fillMaxWidth()){
                        Row{
                            selectedTrack?.title?.let { Heading1(text = it) }
                        }
                        Row {
                            selectedTrack?.artist?.name?.let { Heading2(text = it) }
                        }
                        Row{
                            selectedTrack?.album?.title?.let { Heading3(text = it) }
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun PlayerControls(model: FreezerModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .requiredHeight(100.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
        ){
            PlayFromStartButton(model) // no container
            PlayPauseButton(model) // no container
            PlayNextButton(model) // no container
    }
}

@Composable
private fun PlayNextButton(model: FreezerModel) {
    with(model) {
        IconButton(onClick = { playNext() }) {
            Icon(Icons.Filled.SkipNext, "Play next", modifier = Modifier.size(50.dp))
        }
    }
}

@Composable
private fun PlayFromStartButton(model: FreezerModel) {
    with(model) {
        IconButton(onClick = { fromStart() }) {
            Icon(Icons.Filled.SkipPrevious, "Play next", modifier = Modifier.size(50.dp))
        }
    }
}

@Composable
private fun PlayPauseButton(model: FreezerModel){
    with(model){
        if(playerIsReady){
            IconButton(onClick = { startPlayer() },
                modifier = Modifier
                    .background(
                        SolidColor(Color.White),
                        shape = CircleShape,
                        alpha = if (playerIsReady) 1.0f else 0.3f
                    )
                    .size(72.dp),
                enabled = playerIsReady) {
                Icon(Icons.Filled.PlayArrow, "Play song",
                    tint = if(playerIsReady) Color.Black else Color.White,
                    modifier = Modifier
                        .size(50.dp))
            }
        }
        else {
            IconButton(onClick = { pausePlayer() },
                modifier = Modifier
                    .background(Color.White, shape = CircleShape)
                    .size(72.dp)) {
                Icon(Icons.Filled.Pause, "Pause song",
                    modifier = Modifier.size(48.dp),
                    tint = if(!playerIsReady) Color.Black else Color.White
                )
            }
        }
    }
}




@Preview()
@Composable
private fun SongInformationPreview(){
    SongInformation(FreezerModel(service = RemoteFreezerService()))
}