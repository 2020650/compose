package com.ing.composeplayground.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.ing.composeplayground.data.Item
import com.ing.composeplayground.data.ItemViewModel

@Composable
fun ItemListScreen(itemViewModel: ItemViewModel) {
    Column {
        MyTopBar(itemViewModel)
        MyList(itemViewModel)
    }
}

@Composable
fun MyTopBar(itemViewModel: ItemViewModel) {
    val context = LocalContext.current
    TopAppBar(
        title = { Text("My Beautiful List") },
        actions = {
            IconButton(onClick = {
                toast(context, "Refreshing...")
                itemViewModel.onRefresh()
            }) {
                Icon(Icons.Default.Refresh, "Refresh")
            }
        }
    )
}


@Composable
fun MyList(
    itemViewModel: ItemViewModel
) {
    val context = LocalContext.current
    val items: List<Item> by itemViewModel.itemList.observeAsState(emptyList())
    
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        items.forEach { item ->
            Spacer(modifier = Modifier.height(4.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        toast(context, item.title)
                        itemViewModel.onItemClick(item)
                    },
                elevation = 4.dp,
                backgroundColor = Color.White,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(
                    width = 2.dp, color = if (item.value > 0) {
                        Color.Green
                    } else {
                        Color.Red
                    }
                )
            ) {
                ListItem(item.title, item.value)
            }
        }
    }
}

@Composable
fun ListItem(title: String, value: Float) {
    ConstraintLayout(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {

        val (titleRef, valueRef, imageRef) = createRefs()

        val arrow: Int
        val arrowColor: Color
        if (value >= 0) {
            arrow = android.R.drawable.arrow_up_float
            arrowColor = Color.Green
        } else {
            arrow = android.R.drawable.arrow_down_float
            arrowColor = Color.Red
        }

        Text(text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(titleRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(valueRef.start)
                    width = Dimension.fillToConstraints
                })

        Image(
            painter = painterResource(arrow),
            contentScale = ContentScale.FillHeight,
            contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .constrainAs(imageRef) {
                    top.linkTo(titleRef.top)
                    bottom.linkTo(titleRef.bottom)
                    end.linkTo(parent.end)
                },
            colorFilter = ColorFilter.tint(arrowColor)
        )

        Text(text = value.toString(),
            fontStyle = FontStyle.Italic,
            fontSize = 12.sp,
            modifier = Modifier
                .padding(12.dp)
                .wrapContentWidth()
                .constrainAs(valueRef) {
                    end.linkTo(imageRef.start)
                    top.linkTo(titleRef.top)
                    bottom.linkTo(titleRef.bottom)
                })
    }
}

@Preview
@Composable
fun MyTestListItem() {
    ListItem(title = "Title test", value = 234.5f)
}

private fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}