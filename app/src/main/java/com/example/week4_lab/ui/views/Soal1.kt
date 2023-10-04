package com.example.week4_lab.ui.views

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4_lab.R
import com.example.week4_lab.data.dummy_data
import com.example.week4_lab.model.line_chat

@Composable
fun Soal1(lineList: List<line_chat>) {

    Column {
        Row(
            modifier = Modifier
                .background(
                    Color.Black
                )
                .fillMaxWidth()
                .padding(vertical = 14.dp, horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Chats",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.round_menu_24),
                contentDescription = "Menu",
                modifier = Modifier.size(38.dp)
            )
        }
        Column (
            modifier = Modifier.background(color = Color.Black)
        ){
            LazyColumn {
                items(lineList) {
                    ChatCard(
                        lineChat = it,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
        }

    }

}

@Composable
fun ChatCard(
    lineChat: line_chat,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(Color.Black, Color.Black)
    ) {
        TextButton(
            onClick = {
                      Toast.makeText(context,"${lineChat.username} clicked", Toast.LENGTH_SHORT ).show()
            }
        )
        {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.round_person_24),
                    contentDescription = "User",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.LightGray, shape = CircleShape)
                )

                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 12.dp, end = 5.dp)
                ) {
                    Text(
                        text = lineChat.username,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(bottom = 4.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                    Text(
                        text = lineChat.recentChat,

                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                }

                Text(
                    text = lineChat.chatDate,
                    textAlign = TextAlign.Right,
                    modifier = Modifier,
                    color = Color.White
                )
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Soal1Preview() {
    Soal1(lineList = dummy_data().get_data_line())
}
