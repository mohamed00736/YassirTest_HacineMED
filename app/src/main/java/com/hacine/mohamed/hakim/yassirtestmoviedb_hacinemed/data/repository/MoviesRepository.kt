package com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.repository

import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.network.Api
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.network.SafeApiCall
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: Api, ) : SafeApiCall {
    suspend fun getTrendingMovies() = safeApiCall {
        api.getTrendingMovies()
    }

    suspend fun getMovieById(movieid :String ) = safeApiCall {
        api.getMovieById(movieid)
    }
}