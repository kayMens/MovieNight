package com.movienight.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.movienight.app.domain.model.Movie
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val carouselState = rememberCarouselState { state.carousel.count() }
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // Featured
            HorizontalMultiBrowseCarousel(
                state = carouselState,
                preferredItemWidth = 260.dp,
                itemSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier
                    .height(250.dp)
            ) { index ->
                CarouselItemContent(
                    item = state.carousel[index],
                    modifier = Modifier.maskClip(MaterialTheme.shapes.large)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Highly Rated
            CategoryTitle(
                title = "Shelly's Finest",
            )

            MoviesRow(state.movies.take(10))

            Spacer(modifier = Modifier.height(30.dp))

            //Current Movies
            CategoryTitle(
                title = "Now Watching",
            )

            MoviesRow(state.movies.take(10))

            Spacer(modifier = Modifier.height(30.dp))

            //Old Movies
            CategoryTitle(
                title = "Back in Time",
            )

            MoviesRow(state.movies.take(10))

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun CarouselItemContent(
    item: Movie,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.height(240.dp)
            .background(
                color = MaterialTheme.colorScheme.background
            )
    ) {
        AsyncImage(
            model = item.poster,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        // Overlay Title
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        startY = 50.0f,
                        colors = listOf(Color.Black.copy(alpha = 0.1f), Color.Black)
                    )
                ),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = item.title,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun MoviesRow(
    movies: List<Movie>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier
            .padding()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(movies) { item ->
            MovieCard(item)
        }
    }
}

@Composable
fun MovieCard(item: Movie) {
    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .width(160.dp)
            .height(180.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = item.poster,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            // Overlay Title
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = item.title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.Start,
                    maxLines = 2,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun CategoryTitle(
    title: String
) {
    Text(
        text = title,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}