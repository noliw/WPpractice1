package com.nolawiworkineh.wppractice1.data.di

import com.nolawiworkineh.wppractice1.data.ApiService
import com.nolawiworkineh.wppractice1.data.PostsModel
import com.nolawiworkineh.wppractice1.data.PostsRepoImpl
import com.nolawiworkineh.wppractice1.data.RetrofitClient
import com.nolawiworkineh.wppractice1.domain.PostsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MukundPostModule{

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RetrofitClient.retrofit

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providePostsRepoImpl(apiService: ApiService): PostsRepo =
        PostsRepoImpl(apiService)

}