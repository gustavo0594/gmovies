package com.gquesada.myapplication.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gquesada.domain.models.Movie
import com.gquesada.domain.usecases.MovieListUseCase
import com.gquesada.domain.usecases.MovieListUseCaseInput
import com.gquesada.movies.ui.MovieListScreen
import com.gquesada.myapplication.ui.theme.GMoviesTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var useCase: MovieListUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GMoviesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }


    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        val movieResponse = remember {
            mutableStateOf(emptyList<Movie>())
        }
        LaunchedEffect(key1 = Unit) {
            movieResponse.value = useCase(MovieListUseCaseInput(1))
            Log.d("TAVO_TEST", movieResponse.toString())
        }
        MovieListScreen(movies = movieResponse.value)

    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        GMoviesTheme {
            Greeting("Android")
        }
    }
}
