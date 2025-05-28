package com.semenovdev.vkfeed.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.semenovdev.vkfeed.domain.Comment

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CommentsScreen(
    onBackPress: () -> Unit,
) {
    val viewModel: CommentsViewModel = viewModel()
    val screenState = viewModel.screenState.observeAsState(CommentsScreenState.Initial)
    val currentsState = screenState.value
    when(currentsState) {
        is CommentsScreenState.Comments ->  {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = { onBackPress() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                    Text(text = "Comments to FeedPost id: ${currentsState.post.id}")
                }
                LazyColumn {
                    items(
                        items = currentsState.comments,
                        key = {
                            it.id
                        }
                    ) { comment ->
                        CommentCard(comment)
                    }

                }
            }
        }
        CommentsScreenState.Initial -> {}
    }

}

@Composable
fun CommentCard(comment: Comment) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Image(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
            painter = painterResource(comment.authorAvatar),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Row {
                Text(
                    text = comment.authorName,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Comment id: ${comment.id}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = comment.text,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = comment.publicationDate,
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.secondary
            )
        }

    }
}