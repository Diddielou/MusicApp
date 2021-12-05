package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import fhnw.emoba.freezerapp.data.Album
import fhnw.emoba.freezerapp.data.Radio
import fhnw.emoba.freezerapp.data.impl.ISearch
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun GoToPlayerButton(model: FreezerModel, scope: CoroutineScope, scaffoldState: ScaffoldState){
    if(model.currentScreen != Screen.PLAYER) {
        IconButton(onClick = {
            if (model.selectedTrack != null) {
                model.currentScreen = Screen.PLAYER
            } else {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Please choose a song first.")
                }
            }
        })
        {
            Icon(Icons.Filled.PlayCircleOutline, contentDescription = "Go to player")
        }
    }
}

@Composable
fun LoadingIndicator(isLoading: Boolean = true) {
    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(modifier = Modifier.size(60.dp))
        }
    }
}

@Composable
fun GeneralTopBar(model: FreezerModel, title: String, screen: Screen, scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    with(model) {
        TopAppBar(
            title = { Text(text = title ) },
            navigationIcon = { IconButton(onClick = { currentScreen = screen }) {
                Icon(Icons.Filled.ArrowBack, "Back")
            } },
            actions = {
                GoToPlayerButton(model = model, scope = scope, scaffoldState = scaffoldState)
            }
        )
    }
}

@Composable
fun OnScreenMessage(message: String){
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.secondary)
    }
}

@Composable
fun Heading1(text: String){
    Text(text = text,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp),
        style = MaterialTheme.typography.h4,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        color = MaterialTheme.colors.secondaryVariant)
}

@Composable
fun Heading2(text: String){
    Text(text = text,
        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 5.dp),
        style = MaterialTheme.typography.h5,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1)
}

@Composable
fun Heading3(text: String){
    Text(text = text,
        modifier = Modifier.padding(0.dp),
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1)
}


@Composable
fun FavoriteIcon(model: FreezerModel, track: ISearch){
    if (model.isInFavorites(track)) {
        IconButton(onClick = {
            model.removeFromFavorites(track)
        }) {
            Icon(Icons.Outlined.Favorite,
                contentDescription = null,
                modifier = Modifier.padding(0.dp))
        }
    } else {
        IconButton(onClick = {
            model.addToFavorites(track)
        }) {
            Icon(Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier.padding(0.dp))
        }
    }
}

@Composable
fun RadioImageSmall(radio: Radio){
    if(radio.picture != null) {
        Image(
            bitmap = radio.radioPictureSmall,
            contentDescription = "Radio image",
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
fun RadioImageBig(radio: Radio){
    if(radio.picture != null) {
        Image(
            bitmap = radio.radioPictureBig,
            contentDescription = "Radio image",
            modifier = Modifier.size(200.dp)
        )
    }
}


@Composable
fun AlbumCoverSmall(album: Album){
    if(album.cover != null) {
        Image(
            bitmap = album.coverImageSmall,
            contentDescription = "Album cover",
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
fun AlbumCoverMedium(album: Album){
    if(album.cover != null) {
        Image(
            bitmap = album.coverImageBig,
            contentDescription = "Album cover",
            modifier = Modifier.size(200.dp)
        )
    }
}


@Composable
fun AlbumCoverBig(album: Album){
    if(album.cover != null) {
        Image(
            bitmap = album.coverImageBig,
            contentDescription = "Album cover",
            modifier = Modifier.size(350.dp)
        )
    }
}
