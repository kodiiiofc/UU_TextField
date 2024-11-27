package com.kodiiiofc.urbanuniversity.jetpackcompose.textfield

import android.os.Bundle
import android.view.KeyEvent.KEYCODE_ENTER
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicList()
        }
    }
}

@Composable
fun Item(text: String, onItemClick: () -> Unit) {

    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(CircleShape)
            .background(Color.White)
            .clickable { onItemClick() }
            .padding(16.dp, 8.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DynamicList() {

    val itemList = remember {
        mutableStateListOf<String>()
    }

/*    val string =
        "word ghjk"

    string.split(" ").forEach {
        itemList.add(it)
    }*/

    var currentValue by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Динамический список",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.White
        )

        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp))
                .background(Color.LightGray)
                .fillMaxWidth()
                .height(400.dp)
        ) {
            itemsIndexed(itemList) { index, item ->
                Item(
                    item
                ) {
                    itemList.removeAt(index)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = currentValue,
            onValueChange = {currentValue = it},
            singleLine = true,
            maxLines = 1,
            textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier.onKeyEvent {
                if (it.nativeKeyEvent.keyCode == KEYCODE_ENTER) {
                    itemList.add(currentValue)
                    currentValue = ""
                    true
                } else false
            }
        )

    }
}