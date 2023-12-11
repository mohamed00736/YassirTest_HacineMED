package com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.network


import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.network.Constants.Companion.API_KEY
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.models.Movie
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.models.MovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("3/discover/movie")
    suspend fun getTrendingMovies(@Query("api_key") api_key: String = API_KEY): MovieList


    @GET("3/movie/{movie_id}")
    suspend fun getMovieById(@Path("movie_id") movie_id :String   ,@Query("api_key") api_key: String = API_KEY): Movie


}