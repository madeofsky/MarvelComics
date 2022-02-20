package com.lucas.marvelheroes.di

import com.lucas.marvelheroes.network.MarvelHeroesServiceInstance
import com.lucas.marvelheroes.utils.MarvelHeroesConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelHeroesModule {

    @Singleton
    @Provides
    fun getMarvelHeroesServiceInstanceInterface(retrofit: Retrofit): MarvelHeroesServiceInstance {
        return retrofit.create(MarvelHeroesServiceInstance::class.java)
    }

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MarvelHeroesConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}