package com.semenovdev.vkfeed.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.semenovdev.vkfeed.R
import com.semenovdev.vkfeed.domain.FeedPost
import com.semenovdev.vkfeed.domain.PostStatistic
import com.semenovdev.vkfeed.domain.StatisticType

@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    feedPost: FeedPost,
    onViewClickListener: (item: PostStatistic) -> Unit,
    onShareClickListener: (item: PostStatistic) -> Unit,
    onCommentClickListener: (item: PostStatistic) -> Unit,
    onLikeClickListener: (item: PostStatistic) -> Unit
) {
    Card(
        modifier = modifier
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
            PostHeader(
                avatarResId = feedPost.avatarResId,
                communityName = feedPost.communityName,
                publicationDate = feedPost.publicationDate
            )
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            PostContent(
                contentImageResId = feedPost.contentImageResId,
                contentText = feedPost.contentText
            )
            Spacer(
                modifier = Modifier.size(8.dp)
            )
            PostFooter(
                statistic = feedPost.statistics,
                onViewClickListener = onViewClickListener,
                onShareClickListener = onShareClickListener,
                onCommentClickListener = onCommentClickListener,
                onLikeClickListener = onLikeClickListener,
            )
        }
    }
}


@Composable
fun PostHeader(
    avatarResId: Int,
    communityName: String,
    publicationDate: String
) {

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
                painter = painterResource(avatarResId),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape)
            )
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(
                    text = communityName,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = publicationDate,
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
fun PostContent(
    contentImageResId: Int,
    contentText: String,
) {

    Column {
        Text(
            text = contentText,
            fontWeight = FontWeight.SemiBold
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 8.dp),
            painter = painterResource(contentImageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun PostFooter(
    statistic: List<PostStatistic>,
    onViewClickListener: (item: PostStatistic) -> Unit,
    onShareClickListener: (item: PostStatistic) -> Unit,
    onCommentClickListener: (item: PostStatistic) -> Unit,
    onLikeClickListener: (item: PostStatistic) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        val viewsItem = statistic.getItemByType(StatisticType.VIEWS)
        StatisticItem(
            text = viewsItem.count.toString(),
            painterResourceId = R.drawable.ic_views_count,
            onItemClickListener = {
                onViewClickListener(viewsItem)
            }
        )
        Row {
            val sharesItem = statistic.getItemByType(StatisticType.SHARES)
            StatisticItem(
                text = sharesItem.count.toString(),
                painterResourceId = R.drawable.ic_share,
                onItemClickListener = {
                    onShareClickListener(sharesItem)
                }

            )
            val commentsItem = statistic.getItemByType(StatisticType.COMMENTS)
            StatisticItem(
                text = commentsItem.count.toString(),
                painterResourceId = R.drawable.ic_comment,
                onItemClickListener = {
                    onCommentClickListener(commentsItem)
                }
            )
            val likesItem = statistic.getItemByType(StatisticType.LIKES)
            StatisticItem(
                text = likesItem.count.toString(),
                painterResourceId = R.drawable.ic_like,
                onItemClickListener = {
                    onLikeClickListener(likesItem)
                }
            )
        }
    }
}

private fun List<PostStatistic>.getItemByType(type: StatisticType): PostStatistic {
    return this.find {
        it.type == type
    } ?: throw IllegalStateException("StatisticItem type doesn't exist: ${this.toString()}")
}


@Composable
fun StatisticItem(
    text: String,
    painterResourceId: Int,
    onItemClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.clickable {
            onItemClickListener()
        },
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