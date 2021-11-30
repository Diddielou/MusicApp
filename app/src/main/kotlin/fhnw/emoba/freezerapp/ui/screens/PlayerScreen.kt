package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.model.FreezerModel

// TODO: get music via service

@Composable
fun PlayerScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { PlayerTopBar(model) }
    ){
        Body(model)
    }
}


@Composable
private fun PlayerTopBar(model: FreezerModel) {
    with(model) {
        TopAppBar(
            title          = { Text("Player Top Bar") },
            navigationIcon = { IconButton(onClick = { /* currentScreen = screen */ }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            } }

        )
    }
}

@Composable
private fun Body(model: FreezerModel) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier            = Modifier.fillMaxSize().padding(20.dp))
    {
        //Link("countrycode.org", "https://countrycode.org")

        //SearchField(model)

        PlayerButtons(model)
    }
}


@Composable
private fun PlayFromStartButton(model: FreezerModel, modifier: Modifier) {
    with(model) {
        IconButton(onClick = { fromStart() }, modifier = modifier) {
            Image(Icons.Filled.SkipPrevious, "", modifier = Modifier.size(48.dp))
        }
    }
}

@Composable
private fun PlayerButtons(model: FreezerModel){
    with(model){
        Box(modifier         = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            if (!playerIsReady) {
                PlayFromStartButton(model, Modifier.align(Alignment.CenterStart))
            }
            PlayPauseButton(model, Modifier.align(Alignment.Center))
        }
    }
}


@Composable
private fun PlayPauseButton(model: FreezerModel, modifier: Modifier){
    with(model){
        val enabled = trackId.length == 3
        if(playerIsReady){
            IconButton(onClick = { startPlayer() },
                modifier = Modifier.background(
                    SolidColor(Color.LightGray),
                    shape = CircleShape,
                    alpha = if(enabled) 1.0f else 0.3f)
                    .size(72.dp)
                    .then(modifier),
                enabled = enabled) {
                Icon(Icons.Filled.PlayArrow, "",
                    tint = if(enabled) Color.Black else Color.LightGray,
                    modifier = Modifier.size(48.dp).then(modifier))
            }
        }
        else {
            IconButton(onClick = { pausePlayer() },
                modifier = Modifier.background(Color.LightGray, shape = CircleShape).size(72.dp)
                    .then(modifier)) {
                Icon(Icons.Filled.Pause, "", modifier = Modifier.size(48.dp))
            }
        }
    }

}

@Preview(device = Devices.PIXEL_4)
@Composable
private fun ScreenPreview(){
    PlayerScreen(model = FreezerModel)
}
