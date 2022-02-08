package com.ing.composeplayground

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ing.composeplayground.data.Item
import com.ing.composeplayground.data.ItemsViewModel
import com.ing.composeplayground.ui.theme.ComposePlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyAmazingUI()
                }
            }
        }
    }
}

@Composable
fun MyAmazingUI() {
    Column {
        MyTopBar()
        MyList(itemsList = ItemsViewModel.list)
    }
}

@Composable
fun MyTopBar() {
    val context = LocalContext.current
    TopAppBar(
        title = { Text("My Beautiful List") },
        actions = {
            IconButton(onClick = {
                toast(context, "Love")
            }) {
                Icon(Icons.Default.Favorite, "Love icon")
            }
        }
    )
}

@Composable
fun MyList(
    itemsList: List<Item>
) {
    val context = LocalContext.current
    LazyColumn(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemsList) { data ->
            Spacer(modifier = Modifier.height(4.dp))
            Card(
                modifier = Modifier.fillMaxSize(),
                elevation = 4.dp,
                backgroundColor = Color.LightGray,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(width = 2.dp, color = Color.Blue)
            ) {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        toast(context, data.name)
                    }
                ) {
                    item {
                        Image(
                            painter = painterResource(data.imageRes),
                            contentScale = ContentScale.FillHeight,
                            contentDescription = null,
                            modifier = Modifier.padding(12.dp)
                        )
                        Text(
                            text = data.name
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePlaygroundTheme {
        MyAmazingUI()
    }
}

private fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
