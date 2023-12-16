package com.example.populartv.ui.screens.bottomnavigation.popular

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.populartv.data.model.GenreId
import com.example.populartv.data.model.moviedetail.Genre
import com.example.populartv.ui.component.MovieItemList
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
fun Popular(
    navController: NavController,
    genres: ArrayList<Genre>? = null,
) {

    var refreshing by remember { mutableStateOf(false) }
    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(3000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = refreshing),
        onRefresh = { refreshing = true },
    ) {


        val popularViewModel = hiltViewModel<PopularViewModel>()
        MovieItemList(
            navController = navController,
            movies = popularViewModel.popularMovies,
            genres = genres,
            selectedName = popularViewModel.selectedGenre.value
        ) {
            popularViewModel.filterData.value = GenreId(it?.id.toString())
            it?.let {
                popularViewModel.selectedGenre.value = it
            }
        }
    }
}