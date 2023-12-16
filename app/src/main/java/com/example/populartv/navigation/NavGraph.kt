package com.example.populartv.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.populartv.R
import com.example.populartv.data.model.moviedetail.Genre
import com.example.populartv.ui.screens.artistdetail.ArtistDetail
import com.example.populartv.ui.screens.genre.GenreScreen
import com.example.populartv.ui.screens.moviedetail.TVDetail
import com.example.populartv.ui.screens.bottomnavigation.topRated.TopRated
import com.example.populartv.ui.screens.bottomnavigation.popular.Popular

@Composable
fun Navigation(
    navController: NavHostController, modifier: Modifier, genres: ArrayList<Genre>? = null,
) {
    NavHost(navController, startDestination = Screen.Home.route, modifier) {
        composable(Screen.Home.route) {
            TopRated(
                navController = navController,
                genres
            )
        }
        composable(Screen.Popular.route) {
            Popular(
                navController = navController,
                genres
            )
        }

        composable(
            Screen.NavigationDrawer.route.plus(Screen.NavigationDrawer.objectPath),
            arguments = listOf(navArgument(Screen.NavigationDrawer.objectName) {
                type = NavType.StringType
            })
        ) { backStack ->
            val genreId = backStack.arguments?.getString(Screen.NavigationDrawer.objectName)
            genreId?.let {
                GenreScreen(
                    navController = navController, genreId
                )
            }
        }
        composable(
            Screen.MovieDetail.route.plus(Screen.MovieDetail.objectPath),
            arguments = listOf(navArgument(Screen.MovieDetail.objectName) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.movie_detail)
            val movieId = it.arguments?.getInt(Screen.MovieDetail.objectName)
            movieId?.let {
                TVDetail(
                    navController = navController, movieId
                )
            }
        }
        composable(
            Screen.ArtistDetail.route.plus(Screen.ArtistDetail.objectPath),
            arguments = listOf(navArgument(Screen.ArtistDetail.objectName) {
                type = NavType.IntType
            })
        ) {
            label = stringResource(R.string.artist_detail)
            val artistId = it.arguments?.getInt(Screen.ArtistDetail.objectName)
            artistId?.let {
                ArtistDetail(
                    artistId
                )
            }
        }
    }
}

@Composable
fun navigationTitle(navController: NavController): String {
    return when (currentRoute(navController)) {
        Screen.MovieDetail.route -> stringResource(id = R.string.movie_detail)
        Screen.ArtistDetail.route -> stringResource(id = R.string.artist_detail)
        Screen.Login.route -> stringResource(id = R.string.login)
        else -> {
            ""
        }
    }
}

@Composable
fun currentRoute(navController: NavController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBeforeLast("/")
}
