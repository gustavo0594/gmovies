package com.gquesada.movies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Paint
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.gquesada.domain.models.Movie
import com.gquesada.movies.R

@Composable
fun MovieListScreen(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(movies) { movie ->
            MovieItemCard(movie = movie)
        }

    }
}

@Composable
private fun MovieItemCard(
    movie: Movie,
    modifier: Modifier = Modifier,
) {
    val percentage = movie.voteAverage * 10
    Card(modifier = modifier.padding(vertical = 8.dp)) {
        Column {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                            .placeholder(R.drawable.img_placeholder)
                            .crossfade(true)
                            .transformations(RoundedCornersTransformation())
                            .build()
                    ),
                    contentDescription = movie.title,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                        .height(200.dp),
                )
                CircularProgressBar(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(end = 4.dp),
                    percentage = percentage,
                    trackColor = Color(0x8DFFFFFF),
                    fillColor = Color(0x8000FF00)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = movie.title,
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = movie.releaseDate ?: "",
                    fontStyle = FontStyle.Normal,
                    fontSize = 14.sp,
                )
            }

        }
    }
}

@Composable
private fun CircularProgressBar(
    modifier: Modifier = Modifier,
    percentage: Float,
    fillColor: Color,
    trackColor: Color = Color.LightGray,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = Color(0x80000000), shape = CircleShape)
            .padding(6.dp)
    ) {
        Canvas(modifier = Modifier.size(48.dp)) {
            val strokeWidth = 4.dp.toPx()

            // Draw background track
            drawArc(
                color = trackColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )

            // Draw progress arc
            drawArc(
                color = fillColor,
                startAngle = -90f,
                sweepAngle = (percentage / 100f) * 360f,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )
        }

        Text(
            text = "${percentage.toInt()}%",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
@Preview
private fun MovieItemCardPreview() {
    MovieItemCard(
        movie = Movie(
            genreIds = emptyList(),
            id = 1,
            originalLanguage = "",
            originalTitle = "Deadpool & Wolverine",
            overview = "",
            popularity = 34.00,
            posterPath = "/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
            releaseDate = "12 Jun, 2024",
            title = "Deadpool & Wolverine",
            video = false,
            voteAverage = 34f,
            voteCount = 4
        )
    )
}

@Composable
@Preview
private fun CircularPreview() {
    CircularProgressBar(
        percentage = 75f,
        trackColor = Color(0x8DFFFFFF),
        fillColor = Color(0x8000FF00),
    )
}