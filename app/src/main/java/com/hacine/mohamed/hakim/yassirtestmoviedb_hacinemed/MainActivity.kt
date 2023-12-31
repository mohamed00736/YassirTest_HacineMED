package com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hacine.mohamed.hakim.yassir_test_moviedb.presentation.MovieDetailScreen
import com.hacine.mohamed.hakim.yassir_test_moviedb.presentation.SplashScreen
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.presentation.MainDestinations
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.presentation.main_screen.MainScreen
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.ui.theme.YassirTestMovieDB_HacineMedTheme
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            val navController = rememberNavController()

            YassirTestMovieDB_HacineMedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    NavHost(
                        navController = navController,
                        startDestination = MainDestinations.Splash_Screen
                    ) {
                        composable(route = MainDestinations.Main_Screen) {
                            BackHandler(enabled = true) {
                                finish()
                            }
                            MainScreen{ movieId ->

                                navController.navigate("${MainDestinations.Detail_Screen_List}/"+movieId)

                            }
                        }

                        composable(route = "${MainDestinations.Splash_Screen}"){
                            SplashScreen{

                                navController.navigate(MainDestinations.Main_Screen)

                            }
                        }

                        composable(
                            route = "${MainDestinations.Detail_Screen_List}/{movieid}",
                            arguments = listOf(navArgument(name = "movieid") {
                                type = NavType.StringType
                            })

                        ){from ->

                            from.arguments?.getString("movieid").let { movieId ->
                                if (movieId != null) {
                                    MovieDetailScreen(movieid = movieId) {
                                        navController.navigateUp()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}