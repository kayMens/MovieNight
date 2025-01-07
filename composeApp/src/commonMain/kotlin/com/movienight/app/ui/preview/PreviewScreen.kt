package com.movienight.app.ui.preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.movienight.app.domain.model.Movie

@Composable
fun PreviewScreen(
    movie: Movie?
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .heightIn(max = 500.dp)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            AsyncImage(
                model = movie?.poster,
                contentDescription = movie?.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
            )

            Column {
                Text(
                    text = "${movie?.title}",
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2
                )

                Text(
                    text = "${movie?.runtimeString}",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Year",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "${movie?.year}",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Column {
                Text(
                    text = "Rating",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "${movie?.rating}",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Column {
                Text(
                    text = "Votes",
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = "${movie?.votes}",
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Plot",
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "${movie?.plot}"
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {}
        ) {
            Text(
                text = "Watch on IMDb",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}