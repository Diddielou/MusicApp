package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayCircleOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fhnw.emoba.R
import fhnw.emoba.freezerapp.data.*
import fhnw.emoba.freezerapp.data.impl.RemoteFreezerService
import fhnw.emoba.freezerapp.data.impl.ISearch
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.Screen
import fhnw.emoba.freezerapp.model.Tab
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// 1 RadioScreen lädt noch keine Lieder (keine sichtbar)
// 2 von FavTab > Songklick > geht nicht auf Player
// 3 Player: wenn viel Text, Player Buttons werden runtergedrückt
// 4 Deezer image prominenter hinstellen
// 5 CircularProgressIndicator lädt immer noch oben
// 7 Farben DarkTheme besser machen (knalliger)
// !! Klickt man NextTrack und hat es keine Lieder mehr, stürzt ab !!
// Abbreviate text in Title (ListItem)
// 10 if selectedTrack == null, don't open player and Snackbar
// TODO: Favoriten durchsuchen funktioniert noch nicht
// TODO: Kehrt man vom Player zurück, ist die Stelle in der Liste nicht mehr gleich.

/**
 * Ideas for further features
 * - Search through favorites
 * - When going back from player, user should see list where they left it.
 */


@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun MainScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState           = scaffoldState,
        topBar                       = { TopBar(model, scaffoldState) },
        drawerContent                = { Drawer(model) },
        content                      = { DefaultBody(model) },
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    snackbarData = data
                )
            }
        }
    )
}

/** Contains TopAppBar and SearchField */
@ExperimentalComposeUiApi
@Composable
private fun TopBar(model: FreezerModel, scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    Column {
        TopAppBar(
            title = { Text(text = Screen.MAIN.title ) },
            navigationIcon = { DrawerIcon(scaffoldState = scaffoldState) },
            actions = {
                GoToPlayerButton(model = model, scope = scope, scaffoldState = scaffoldState)
            }
        )
        SearchField(model = model)
    }
}


@ExperimentalComposeUiApi
@Composable
fun SearchField(model: FreezerModel){
    with(model) {
        val keyboard = LocalSoftwareKeyboardController.current
        TextField(
            value = searchFilter,
            onValueChange = { searchFilter = it },
            placeholder = { Text("Songs or albums") },
            trailingIcon = {
                IconButton(onClick = {
                    keyboard?.hide()
                    searchFilter = ""
                    when (selectedTab) {
                        Tab.TRACKS -> searchTrack(searchFilter)
                        Tab.ALBUMS -> searchAlbum(searchFilter)
                        Tab.RADIO -> loadRadios()
                        Tab.FAVORITES -> getFavoritesList()
                    }
                })
                { Icon(Icons.Filled.Clear, "Delete") }
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                autoCorrect  = false,
                keyboardType = KeyboardType.Ascii),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboard?.hide()
                    when (selectedTab) {
                        Tab.TRACKS -> searchTrack(searchFilter)
                        Tab.ALBUMS -> searchAlbum(searchFilter)
                        Tab.RADIO -> loadRadios()
                        Tab.FAVORITES -> getFavoritesList()
                    }
            }),
            modifier = Modifier
                .fillMaxWidth()
                .onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == Key.Enter.nativeKeyCode) {
                        keyboard?.hide()
                        when (selectedTab) {
                            Tab.TRACKS -> searchTrack(searchFilter)
                            Tab.ALBUMS -> searchAlbum(searchFilter)
                            Tab.RADIO -> loadRadios()
                            Tab.FAVORITES -> getFavoritesList()
                        }
                    }
                    return@onKeyEvent true
                }
            )
    }
}

/** Contains Tabs and their content */
@ExperimentalMaterialApi
@Composable
private fun DefaultBody(model: FreezerModel) {
    with(model) {
        Column{
            TabRow(selectedTabIndex = selectedTab.ordinal) {
                for (tab in Tab.values()) {
                    Tab(
                        text  = { Text(tab.title) },
                        selected = tab == selectedTab,
                        onClick = { selectedTab = tab} )
                }
            }
            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                when (selectedTab) {
                    Tab.TRACKS -> TrackList(model = model)
                    Tab.ALBUMS -> AlbumList(model = model)
                    Tab.RADIO -> RadioList(model = model)
                    Tab.FAVORITES -> FavoritesList(model = model)
                }
                if (selectedTab == Tab.RADIO) {
                    loadRadios()
                }
            }
        }
    }
}


/** TAB SCREENS
 * Find Tracks in Shared */

/** Displays a list of tracks (Tracks tab) */
@ExperimentalMaterialApi
@Composable
private fun TrackList(model: FreezerModel){
    val state = rememberLazyListState()
    with(model) {
        if (isLoading) {
            LoadingIndicator()
        } else if(model.foundTracksList.isEmpty()) {
            OnScreenMessage("Please search for songs.")
        } else {
            LazyColumn(state = state) {
                items(model.foundTracksList) { TrackRow(model = model, track = it) {
                        //it.album?.let { it -> AlbumCoverSmall(album = it) }
                        AlbumCoverSmall(album = it.album!!)
                    }
                }
            }
        }
    }
}
/** One row in the tracks list */
@ExperimentalMaterialApi
@Composable
private fun TrackRow(model: FreezerModel, track: ISearch, image: @Composable () -> Unit) {
    with(track){
        ListItem(
            modifier = Modifier.clickable(onClick = {
                model.openPlayer(track, model.foundTracksList)
            }),
            icon = image,
            text = { Text(text = title!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
            secondaryText = { Text(artist!!.name!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
            trailing = { FavoriteIcon(model = model, track = track) }
        )
    }
    Divider()
}

/** Displays a list of albums (AlbumTab) */
@ExperimentalMaterialApi
@Composable
private fun AlbumList(model: FreezerModel){
    val state = rememberLazyListState()
    with(model) {
        if (isLoading) {
            LoadingIndicator()
        } else if(model.foundAlbumsList.isEmpty()) {
            OnScreenMessage("Please search for albums.")
        } else {
            LazyColumn(state = state) {
                items(model.foundAlbumsList) {
                        AlbumRow(model, it) { AlbumCoverSmall(album = it) }
                    }
                }
            }
    }
}
/** One row in the album list */
@ExperimentalMaterialApi
@Composable
private fun AlbumRow(model: FreezerModel, album: Album, image: @Composable () -> Unit) {
    with(album){
        ListItem(
        modifier = Modifier.clickable(onClick = {
            model.openAlbum(album)
        }),
        icon = image,
        text = { Text(title!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
        secondaryText = { Text(artist!!.name!!, overflow = TextOverflow.Ellipsis, maxLines = 1) }
        )
    }
    Divider()
}


/** Displays a list of radios (RadioTab) */
@ExperimentalMaterialApi
@Composable
private fun RadioList(model: FreezerModel){
    val state = rememberLazyListState()
    with(model) {
        if (isLoading) {
            LoadingIndicator()
        } else {
        LazyColumn(state = state) {
            items(model.radioStations) {
                    RadioRow(
                        model, it
                    ) {
                        RadioImageSmall(radio = it)
                    }
                }
            }
        }
    }
}
/** One row in the radio list */
@ExperimentalMaterialApi
@Composable
private fun RadioRow(model: FreezerModel, radio: Radio, image: @Composable () -> Unit) {
    with(radio){
        ListItem(
            modifier = Modifier.clickable(onClick = {
                model.openRadio(radio)
            }),
            icon = image,
            text = { Text(title!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
            secondaryText = { Text(description!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
        )
    }
    Divider()
}


/** Displays a list of all favorite tracks */
@ExperimentalMaterialApi
@Composable
private fun FavoritesList(model: FreezerModel){
    val state = rememberLazyListState()
    with(model) {
        if (isLoading) {
            LoadingIndicator()
        } else if(model.favoriteTracksList.isEmpty()) {
            OnScreenMessage("Your added favorites will appear here.")
        } else {
            LazyColumn(state = state) {
                items(model.favoriteTracksList) {
                    FavoritesRow(
                        model, it
                    ) {
                        // it.album?.let { it -> AlbumCoverSmall(album = it) }
                        AlbumCoverSmall(album = it.album!!)
                    }
                }
            }
        }
    }
}

/** One row in the favorite list */
@ExperimentalMaterialApi
@Composable
private fun FavoritesRow(model: FreezerModel, track: ISearch, image: @Composable () -> Unit) {
    with(track){
        ListItem(
            modifier = Modifier.clickable(onClick = {
                model.openPlayer(track, model.favoriteTracksList)
            }),
            icon = image,
            text = { Text(title!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
            secondaryText = { Text(artist!!.name!!, overflow = TextOverflow.Ellipsis, maxLines = 1) },
            trailing = { FavoriteIcon(model = model, track = track) }
        )
    }
    Divider()
}



/* Drawer */
@Composable
fun DrawerIcon(scaffoldState: ScaffoldState){
    val scope = rememberCoroutineScope()

    IconButton(onClick = { scope.launch{scaffoldState.drawerState.open() }}) {
        Icon(Icons.Filled.Menu, "Open drawer")
    }
}

@Composable
fun Drawer(model: FreezerModel) {
    Column {
        DrawerRow(model)
        Box(contentAlignment = Alignment.BottomCenter,
            modifier         = Modifier
                .fillMaxSize()
                .padding(25.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.deezerlogo),
                contentDescription = "Deezer Logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(315.dp)
            )
        }
    }
}

@Composable
fun DrawerRow(model: FreezerModel) {
    with(model) {
        Row(horizontalArrangement = Arrangement.Center,
            modifier              = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, bottom = 25.dp)
        )
        {
            Text(model.drawerTitle,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.secondary)
        }
        Divider()
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp, 20.dp, 10.dp)
                .clickable(onClick = { toggleTheme() })
        ){
            Switch(checked         = darkTheme,
                   onCheckedChange = { toggleTheme() })
            Text(text = toggleThemeText() )
        }
        Divider()
    }
}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Preview()
@Composable
private fun ScreenPreview() {
    MainScreen(FreezerModel(RemoteFreezerService()))
}
