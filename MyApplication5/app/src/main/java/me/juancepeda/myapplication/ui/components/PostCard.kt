package me.juancepeda.myapplication.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.juancepeda.myapplication.model.Post

@Composable
fun PostCard(
    post: Post,
    onLikeClick: (Post) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(modifier =modifier.fillMaxWidth()) {

        PostHeader(post = post)

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(post.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Post de ${post.username}",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentScale = ContentScale.Crop
        )
        PostActions(
            post = post,
            onLikeClick = { onLikeClick(post) }
        )

        PostFooter(post = post)

        Divider(color = Color.LightGray.copy(alpha = 0.3f))
    }
}

@Composable
private fun PostHeader(post: Post){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = post.profileImageUrl,
            contentDescription = "Avatar de ${post.username}",
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .border(1.dp, Color.LightGray, CircleShape),
            contentScale = ContentScale.Crop

        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = post.username,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Más opciones",
            tint = Color.Black
        )

    }

}

@Composable
private fun PostActions(
    post: Post,
    onLikeClick: () -> Unit
){
    var liked by remember { mutableStateOf(post.isLiked) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            liked = !liked
            onLikeClick()
        }) {
            Icon(
                imageVector = if (liked) Icons.Filled.Favorite
                else Icons.Outlined.FavoriteBorder,
                contentDescription = "Like",
                tint = if (liked) Color.Red else Color.Black,
                modifier = Modifier.size(28.dp)

            )
        }
        IconButton(onClick = {}) {
            Icon(Icons.Outlined.ChatBubbleOutline, "Comentar")
        }
        IconButton(onClick = {}) {
            Icon(Icons.Outlined.Send, "Enviar")
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = {}) {
            Icon(Icons.Outlined.BookmarkBorder, "Guardar")
        }
    }
}

@Composable
private fun PostFooter(post: Post) {

    Column(modifier = Modifier.padding(horizontal = 16.dp).padding(bottom = 10.dp)) {

        Text(
            text = "${post.likes} Me gusta",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )

        Spacer(Modifier.height(2.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(post.username + " ")
                }
                append(post.caption)
            },
            fontSize = 14.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
