package com.example.moviesapp.core.di

import com.example.moviesapp.data.repository.network.MovieApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

 /*   @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return ServerMovieDataSource()
    }*/

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideMovieDbClient(retrofit: Retrofit): MovieApiClient {
        return retrofit.create(MovieApiClient::class.java)
    }

}