package com.hacine.mohamed.hakim.yassir_test_moviedb.di


import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.network.Api
import com.hacine.mohamed.hakim.yassirtestmoviedb_hacinemed.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    fun provideRepository(api: Api) : MoviesRepository {
        return MoviesRepository(api)
    }

}