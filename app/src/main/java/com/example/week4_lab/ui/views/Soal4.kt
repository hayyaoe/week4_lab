package com.example.week4_lab.ui.views

import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4_lab.R
import com.example.week4_lab.data.DataSource


@Composable
fun Soal4() {

    Box {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Black)
        ) {


            LazyVerticalGrid(
                columns = GridCells
                    .Fixed(3),
                modifier = Modifier.padding(bottom= 80.dp)
            ) {
                item(span = { GridItemSpan(3) }) {
                    SearchBar()
                }
                items(DataSource().loadExplore()) {
                    GridCard(content = it.content)
                }
            }
        }

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.fillMaxHeight()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Black),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                HomeButtons(message = "Ini Home", drawable = R.drawable.home)
                HomeButtons(message = "Ini Search", drawable = R.drawable.search)
                HomeButtons(message = "Ini Post", drawable = R.drawable.post)
                HomeButtons(message = "Ini Reels", drawable = R.drawable.reels)
                HomeButtons(message = "Ini Account", drawable = R.drawable.account)
            }
        }
    }

}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .padding(8.dp)
    ) {
        var searchText by remember { mutableStateOf("") }

        OutlinedSearchBox(
            modifier = Modifier.fillMaxWidth(),
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = "Search"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedSearchBox(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "Search",
    shape: Shape = CircleShape,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Search
    ),
    onSearch: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {

            Image(painter = painterResource(id = R.drawable.search), contentDescription = "Search", modifier = Modifier.size(20.dp))
            Text(text = placeholder, fontSize = 16.sp, color = Color.White, modifier = Modifier.padding(horizontal = 26.dp))
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        shape = shape,
        modifier = modifier,
        colors = TextFieldDefaults.textFieldColors(textColor = Color.White, containerColor = Color.Transparent)

    )
}

@Composable
fun GridCard(content: String) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = getResourceId(fileName = content)),
        contentDescription = content,
        modifier = Modifier
            .clickable {
                Toast
                    .makeText(context, content, Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(1.dp)
            .height(140.dp),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Soal4Preview() {
    Soal4()
}