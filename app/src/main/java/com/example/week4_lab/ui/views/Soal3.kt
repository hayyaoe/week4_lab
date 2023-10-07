package com.example.week4_lab.ui.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week4_lab.R
import com.example.week4_lab.data.DataSource
import com.example.week4_lab.model.Feed
import com.example.week4_lab.model.Story
import com.example.week4_lab.model.Suggestion
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.random.Random


@Composable
fun Soal3(listFeed: List<Feed>) {

    Box {
        Column(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            LazyColumn(
                modifier = Modifier
            ) {

                item {
                    Header()
                    LazyStory(listStory = DataSource().loadStory())
                }
                items(listFeed) {
                    FeedPost(
                        feed = it,
                    )
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
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_dark),
            contentDescription = "Logo",
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.like),
                contentDescription = "Like",
            )

            Image(
                painter = painterResource(id = R.drawable.dm),
                contentDescription = "dm"
            )
        }
    }

}

@Composable
fun LazyStory(listStory: List<Story>) {
    LazyRow(
        modifier = Modifier.padding(bottom = 4.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        item {
            StoryUser(profile = Story("Your Story", "asa",isLiked =true, isBookmarked = true))
        }

        items(listStory) {
            StoryUser(
                profile = it
            )
        }
    }
}

@Composable
fun LazySuggest(listSuggestion: List<Suggestion>) {
    LazyRow(
        modifier = Modifier.padding(vertical = 4.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(listSuggestion) {
            FollowCard(
                suggestion = it
            )
        }
    }
}

@Composable
fun HomeButtons(message: String, drawable: Int) {
    val context = LocalContext.current
    IconButton(onClick = {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }) {
        Icon(
            painter = painterResource(id = drawable),
            contentDescription = "Like",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Composable
fun FeedPost(feed: Feed) {

    var isLiked by rememberSaveable { mutableStateOf(feed.isLiked) }
    var isSaved by rememberSaveable { mutableStateOf(feed.isBookmarked) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    val random = Random.nextInt(2)
    val resourceId = getResourceId(fileName = feed.profilePic)
    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.story),
                    contentDescription = "Border",
                    modifier = Modifier
                        .size(38.dp),
                    contentScale = ContentScale.Crop
                )

                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = "profile 1",
                    Modifier
                        .clip(shape = CircleShape)
                        .height(34.dp)
                        .width(34.dp),
                    contentScale = ContentScale.Crop
                )
            }

            Text(
                text = feed.username,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f),
                fontSize = 14.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Image(
                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = "more",
                modifier = Modifier
                    .size(24.dp)
            )
        }

        val contentId = getResourceId(fileName = feed.content)
        Image(
            painter = painterResource(id = contentId),
            contentDescription = "content",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                val context = LocalContext.current
                IconButton(onClick = {
                    isLiked = !isLiked
                    if (!isLiked) {
                        Toast.makeText(context, "Post Liked", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Post Unliked", Toast.LENGTH_SHORT).show()
                    }
                }) {

                    if (!isLiked) {
                        Icon(
                            painter = painterResource(id = R.drawable.like),
                            contentDescription = "Like",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.liked),
                            contentDescription = "Liked",
                            tint = Color.Red,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                IconButton(onClick = {
                    Toast.makeText(context, "Commented", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.comment),
                        contentDescription = "Comment",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(onClick = {
                    Toast.makeText(context, "Shared to Friend", Toast.LENGTH_SHORT).show()

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.messanger),
                        contentDescription = "Messenger",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            val context = LocalContext.current

            IconButton(onClick = {
                isSaved = !isSaved
                if (!isLiked) {
                    Toast.makeText(context, "Post Saved", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Removed from Save", Toast.LENGTH_SHORT).show()
                }
            }) {

                if (!isSaved) {
                    Icon(
                        painter = painterResource(id = R.drawable.save),
                        contentDescription = "Save",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.saved_light),
                        contentDescription = "Save",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        when (feed.likes) {
            0 -> {}
            1 -> {
                Text(
                    text = "${decimalFormating(feed.likes)} Like",
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .offset(y = (-10).dp)
                )
            }

            else -> {
                Text(
                    text = "${decimalFormating(feed.likes)} Likes",
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .offset(y = (-10).dp)
                )
            }
        }


        TextButton(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier.offset(y = (-15).dp),
            shape = RoundedCornerShape(0.dp)
        )
        {
            if (!isExpanded) {
                TextDifferentFontWeights(username = feed.username, caption = feed.captions)
            } else {
                TextDifferentFontWeights(
                    username = feed.username,
                    caption = feed.captions,
                    maxLine = 5000
                )
            }
        }

        Text(
            text = dateFormat(feed.date),
            fontSize = 12.sp,
            color = Color.LightGray,
            modifier = Modifier
                .offset(y = (-20).dp)
                .padding(horizontal = 12.dp)
        )
    }
    if (random==0) {
        LazySuggest(listSuggestion = DataSource().loadSuggestion())
    }
}

@SuppressLint("DiscouragedApi")
@Composable
fun getResourceId(fileName: String): Int {
    val context = LocalContext.current
    val resourceId = remember(fileName) {
        context.resources.getIdentifier(
            fileName,
            "drawable",
            context.packageName
        )
    }

    return resourceId
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun StoryUser(profile: Story) {

    val resourceId = getResourceId(fileName = profile.profilePic)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 6.dp)
            .width(80.dp),
    ) {
        val context = LocalContext.current
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.story),
                        contentDescription = "Border",
                        modifier = Modifier
                            .size(80.dp),
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painter = painterResource(id = resourceId),
                        contentDescription = "profile 1",
                        Modifier
                            .clip(shape = CircleShape)
                            .height(70.dp)
                            .width(70.dp)
                            .clickable {
                                Toast.makeText(context, "${profile.username} story", Toast.LENGTH_SHORT).show()
                            },
                        contentScale = ContentScale.Crop
                    )
                }


        }

        Text(
            text = profile.username,
            fontSize = 10.sp,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun FollowCard(suggestion: Suggestion) {

    var isFollowed by rememberSaveable { mutableStateOf(false) }
    OutlinedCard(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
        ,
        colors = CardDefaults.cardColors(Color.Black)
    ) {
        val resourceId = getResourceId(fileName = suggestion.profilePic)
        Box {

            Image(
                painter = painterResource(id = R.drawable.round_close_24),
                contentDescription = "Close",
                modifier = Modifier.padding(4.dp)
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = resourceId),
                    contentDescription = "profile",
                    Modifier
                        .clip(shape = CircleShape)
                        .height(100.dp)
                        .width(100.dp),
                    contentScale = ContentScale.Crop
                )

                Text(
                    text = suggestion.username,
                    fontWeight = SemiBold,
                    color = Color.White,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                val context = LocalContext.current
                Button(
                    onClick = {
                        isFollowed = !isFollowed
                        if (!isFollowed) {
                            Toast.makeText(context, "Followed", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Unfollowed", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = if (!isFollowed) {
                        ButtonDefaults.buttonColors(containerColor = Color.Magenta)
                    } else {
                        ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                    },

                    modifier = Modifier.width(120.dp)


                ) {
                    if (!isFollowed) {
                        Text(text = "follow")
                    } else {
                        Text(text = "followed")
                    }
                }
            }
        }
    }
}

@Composable
fun TextDifferentFontWeights(username: String, caption: String, maxLine: Int = 2) {
    val text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontWeight = Bold, fontSize = 14.sp)) {
            append(username)
        }
        withStyle(style = SpanStyle(fontWeight = Normal, fontSize = 14.sp)) {
            append(" $caption")
        }
    }

    Text(
        text = text,
        textAlign = TextAlign.Start,
        fontSize = 20.sp,
        maxLines = maxLine,
        color = Color.White,
        overflow = TextOverflow.Ellipsis,
    )
}

fun decimalFormating(value: Int): String {
    val decimalFormat = DecimalFormat("#,###")
    return decimalFormat.format(value)
}

fun dateFormat(dateString: String): String {

    val months = listOf("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December")

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(dateString)

        val calendar = Calendar.getInstance()
        calendar.time = date


        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

    return if (year == Calendar.getInstance().get(Calendar.YEAR)){
        "${months[month]} $day"
    } else{
        "${months[month]} $day, $year"
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Soal3Preview() {
    Soal3(
        listFeed = DataSource().loadFeed()
    )
//    StoryUser()
//    FeedPost(DataSource().loadFeed()[1])
//    FollowCard(DataSource().loadSuggestion()[0])
}