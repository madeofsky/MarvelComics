package com.lucas.marvelheroes.di

import com.lucas.marvelheroes.data.MarvelHeroesServiceInstance
import com.lucas.marvelheroes.domain.MarvelHeroesDefaultRepository
import com.lucas.marvelheroes.util.MarvelHeroesConstants
import com.lucas.marvelheroes.util.MarvelHeroesDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MarvelHeroesModule {

    @Singleton
    @Provides
    fun provideMarvelHeroesServiceInstanceInterface(): MarvelHeroesServiceInstance =
        Retrofit.Builder()
            .baseUrl(MarvelHeroesConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelHeroesServiceInstance::class.java)

    @Singleton
    @Provides
    fun provideMarvelHeroesDefaultRepository(apiInstance: MarvelHeroesServiceInstance): MarvelHeroesDefaultRepository =
        MarvelHeroesDefaultRepository(apiInstance)

    @Singleton
    @Provides
    fun provideMarvelHeroesDispatchers(): MarvelHeroesDispatcherProvider = object : MarvelHeroesDispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main

        override val io: CoroutineDispatcher
            get() = Dispatchers.IO

        override val default: CoroutineDispatcher
            get() = Dispatchers.Default

        override val unconfined: CoroutineDispatcher
            get() = Dispatchers.Unconfined

    }
}