package com.nolawiworkineh.wppractice1.data.di

import com.nolawiworkineh.wppractice1.data.PostsModel
import com.nolawiworkineh.wppractice1.data.RetrofitClient
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

}