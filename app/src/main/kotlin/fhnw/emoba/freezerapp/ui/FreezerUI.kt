package fhnw.emoba.freezerapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import fhnw.emoba.freezerapp.model.FreezerModel


@Composable
fun AppUI(model : FreezerModel){
    with(model){
        Box(contentAlignment = Alignment.Center,
            modifier         = Modifier.fillMaxSize()
        ){
            Text(text  = title,
                 style = TextStyle(fontSize = 42.sp))
        }
    }
}

@Preview
@Composable
fun Preview(){
    AppUI(FreezerModel)
}
