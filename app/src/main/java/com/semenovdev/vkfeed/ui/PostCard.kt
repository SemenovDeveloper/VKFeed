package com.semenovdev.vkfeed.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semenovdev.vkfeed.R

@Composable
fun PostCard() {
    Card(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 4.dp)
        ) {
            PostHeader()
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            PostContent()
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            PostFooter()
        }
    }
}


@Composable
fun PostHeader() {
    Row(
      modifier = Modifier
          .height(50.dp)
          .fillMaxWidth()
          .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Image(
                painter = painterResource(R.drawable.post_comunity_thumbnail),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = "Типичный программист",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "14:00",
                    color = MaterialTheme.colorScheme.secondary,
                )
            }
        }
        Image(
            painter = painterResource(R.drawable.ic_menu),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
        )
  }
}

@Composable
fun PostContent() {
    Column {
        Text(
            text = "Кричу: единственным разработчиком AnduinOS, дистрибутива Linux с интерфейсом как у Windows 11, оказался сотрудник Microsoft Андуин Сюэ",
            fontWeight = FontWeight.SemiBold
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            painter = painterResource(R.drawable.post_content_image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PostFooter() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        StatisticItem(
            text = "921",
            painterResourceId = R.drawable.ic_views_count
        )
        Row {
            StatisticItem(
                text = "206",
                painterResourceId = R.drawable.ic_share

            )
            StatisticItem(
                text = "11",
                painterResourceId = R.drawable.ic_comment
            )
            StatisticItem(
                text = "491",
                painterResourceId = R.drawable.ic_like
            )
        }
    }
}

@Composable
fun StatisticItem(
    text: String,
    painterResourceId: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.padding(horizontal = 8.dp),
            painter = painterResource(painterResourceId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
        )
        Text(
            text = text,
            color = MaterialTheme.colorScheme.secondary
        )

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostCard() {
    PostCard()
}

@Preview(showBackground = true)
@Composable
fun PreviewPostCardDark() {
    PostCard()
}