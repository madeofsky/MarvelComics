package com.lucas.marvelheroes.di

import com.lucas.marvelheroes.data.MarvelComicsServiceInstance
import com.lucas.marvelheroes.domain.MarvelComicsRepository
import com.lucas.marvelheroes.domain.ComicsRepository
import com.lucas.marvelheroes.util.MarvelComicsConstants
import com.lucas.marvelheroes.util.ComicsDispatcherProvider
import dagger.Binds
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
interface MarvelComicsModule {

    @Binds
    fun provideMarvelComicsRepository(repositoryMarvel: MarvelComicsRepository): ComicsRepository

    companion object {
        @Singleton
        @Provides
        fun provideMarvelComicsServiceInstanceInterface(): MarvelComicsServiceInstance =
            Retrofit.Builder()
                .baseUrl(MarvelComicsConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MarvelComicsServiceInstance::class.java)

        @Singleton
        @Provides
        fun provideMarvelComicsDefaultRepository(apiInstance: MarvelComicsServiceInstance): MarvelComicsRepository =
            MarvelComicsRepository(apiInstance)

        @Singleton
        @Provides
        fun provideMarvelComicsDispatchers(): ComicsDispatcherProvider = object :
            ComicsDispatcherProvider {
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
}