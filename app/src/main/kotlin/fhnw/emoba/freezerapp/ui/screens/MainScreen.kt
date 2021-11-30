package fhnw.emoba.freezerapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fhnw.emoba.R
import fhnw.emoba.freezerapp.data.Album
import fhnw.emoba.freezerapp.data.Radio
import fhnw.emoba.freezerapp.data.Track
import fhnw.emoba.freezerapp.model.FreezerModel
import fhnw.emoba.freezerapp.model.FreezerModel.title
import fhnw.emoba.freezerapp.model.Tab
import kotlinx.coroutines.launch


@Composable
fun MainScreen(model: FreezerModel){
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState           = scaffoldState,
        topBar                       = { TopBar(scaffoldState) },
        drawerContent                = { Drawer(model) },
        content                      = { DefaultBody(model) }
    )
}


@Composable
private fun TopBar(scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(
            text = title) },
        navigationIcon = { DrawerIcon(scaffoldState = scaffoldState) },
        actions = {
            SearchIcon()
        }
    )
}

// TODO: do something with search button
@Composable
fun SearchIcon(){
    IconButton(onClick = { /* doSomething() */ }) { // TODO: open textfield when clicked on search icon
        Icon(Icons.Filled.Search, contentDescription = "Search")
    }
}


// Drawer
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
        // TODO better placement of Deezer Logo
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
                .padding(top = 10.dp, bottom = 10.dp)
        )
        {
            Text(model.drawerTitle, style = MaterialTheme.typography.h5)
        }
        Divider()
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment     = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 10.dp, 20.dp, 10.dp)
                .clickable(onClick = { toggleTheme() })
        ){
            Switch(checked      = darkTheme,
                onCheckedChange = { toggleTheme() })
            Text(text = toggleThemeText() )
        }
        Divider()
    }
}

@Composable
private fun DefaultBody(model: FreezerModel) {
    with(model) {
        Column() {
            TabRow(selectedTabIndex = selectedTab.ordinal) {
                for (tab in Tab.values()) {
                    Tab(text     = { Text(tab.title) },
                        selected = tab == selectedTab,
                        onClick  = { selectedTab = tab }
                    )
                }
            }
            // TODO: assign content (depending on selectedTab)
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = content,
                style = TextStyle(fontSize = 42.sp)
            )
        }
    }
}


// TabScreens
// TODO not sure if right

@ExperimentalMaterialApi
@Composable
private fun RadioScreen(radios: List<Radio>){
    val state = rememberLazyListState()
    LazyColumn(state = state){
        items(radios) { RadioRow(it) }
    }
}

@ExperimentalMaterialApi
@Composable
private fun RadioRow(radio: Radio) {
    with(radio){
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.deezerlogo), // TODO imageVector = make image out of picture_small
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Text(title)
            Text(description)
            Icon(Icons.Outlined.Favorite, contentDescription = null)
        }
    }
    Divider()
}



@ExperimentalMaterialApi
@Composable
private fun AlbumScreen(albums: List<Album>){
    val state = rememberLazyListState()
    LazyColumn(state = state){
        items(albums) { AlbumRow(it) }
    }
}

@ExperimentalMaterialApi
@Composable
private fun AlbumRow(album: Album) {
    with(album){
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.deezerlogo), // TODO imageVector = make image out of cover_small
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Text(title)
            Text(artist.name)
            Icon(Icons.Outlined.Favorite, contentDescription = null)
        }
    }
    Divider()
}



@ExperimentalMaterialApi
@Composable
private fun TrackScreen(tracks: List<Track>){
    val state = rememberLazyListState()
    LazyColumn(state = state){
        items(tracks) { TrackRow(it) }
    }
}

@ExperimentalMaterialApi
@Composable
private fun TrackRow(track: Track) {
    with(track){
        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.deezerlogo), // TODO imageVector = make image out of track.album.cover_small
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            Text(title)
            Text(artist.name)
            Icon(Icons.Outlined.Favorite, contentDescription = null)
        }
    }
    Divider()
}





@Preview()
@Composable
private fun ScreenPreview() {
    MainScreen(FreezerModel)
}
