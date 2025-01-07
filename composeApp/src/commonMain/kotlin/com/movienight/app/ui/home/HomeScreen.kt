package com.movienight.app.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.movienight.app.domain.model.Movie
import com.movienight.app.ui.preview.PreviewScreen
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
    val sheetState = rememberModalBottomSheetState()

    var showMoviePreview by remember { mutableStateOf(false) }
    var movie: Movie? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = Unit) {
        viewModel.init()
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(
                    bottom = padding.calculateBottomPadding()
                )
                .verticalScroll(scrollState)
        ) {
            Hero(
                state.todayPick,
                modifier = Modifier.graphicsLayer {
                    alpha = 1f - ((scrollState.value.toFloat() / scrollState.maxValue) * 1.5f)
                    translationY = 0.3f * scrollState.value
                }
            )
            {
                showMoviePreview = true
                movie = it
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Featured
            CategoryTitle("Featured")

            HorizontalMultiBrowseCarousel(
                state = carouselState,
                preferredItemWidth = 240.dp,
                itemSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 16.dp),
                modifier = Modifier
                    .height(250.dp)
            ) { index ->
                val item = state.carousel[index]
                CarouselItemContent(
                    item = item,
                    modifier = Modifier.maskClip(MaterialTheme.shapes.large)
                        .clickable {
                            showMoviePreview = true
                            movie = item
                        }
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Highly Rated
            CategoryTitle("Shelly's Finest")

            MoviesRow(
                movies = state.shellysFinest
            ) {
                showMoviePreview = true
                movie = it
            }

            Spacer(modifier = Modifier.height(30.dp))

            //Current Movies
            CategoryTitle("Now Watching")

            MoviesRow(
                movies = state.nowWatching
            ) {
                showMoviePreview = true
                movie = it
            }

            Spacer(modifier = Modifier.height(30.dp))

            //Old Movies
            CategoryTitle("Back in Time")

            MoviesRow(
                movies = state.backInTime
            ) {
                showMoviePreview = true
                movie = it
            }

            Spacer(modifier = Modifier.height(20.dp))
        }

        if (showMoviePreview) {
            ModalBottomSheet(
                modifier = Modifier
                    .padding(top = padding.calculateTopPadding()),
                sheetState = sheetState,
                onDismissRequest = {
                    showMoviePreview = false
                }
            ) {
                PreviewScreen(movie)
            }
        }
    }
}

@Composable
fun Hero(
    movie: Movie?,
    modifier: Modifier = Modifier,
    onClick: (Movie?) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
    ) {
        AsyncImage(
            model = movie?.poster,
            contentDescription = "cover",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clickable { onClick(movie) }
        )

        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.Black.copy(alpha = 0.2f)
                )
        ) {
            if (movie != null) {
                Text(
                    text = "Today's Pick: ${movie.title}",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    maxLines = 1,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(topEnd = 10.dp)
                        )
                        .padding(
                            vertical = 5.dp,
                            horizontal = 10.dp
                        )
                )
            }
        }
    }
}

@Composable
fun CarouselItemContent(
    item: Movie,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.height(220.dp)
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
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
fun MoviesRow(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    onClick: (Movie?) -> Unit
) {
    LazyRow(
        modifier = modifier
            .padding()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(movies) { item ->
            MovieCard(
                item = item,
                modifier = Modifier
                    .clickable { onClick(item) }
            )
        }
    }
}

@Composable
fun MovieCard(
    item: Movie,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier
            .width(160.dp)
            .height(180.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(bottom = 8.dp)
        ) {
            AsyncImage(
                model = item.poster,
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(165.dp, 185.dp)
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
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
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
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}