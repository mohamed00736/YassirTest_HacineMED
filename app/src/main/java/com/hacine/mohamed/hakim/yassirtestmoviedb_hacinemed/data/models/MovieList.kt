package com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.models

import com.google.gson.annotations.SerializedName
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.models.Movie

data class MovieList(

    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<Movie> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null
)