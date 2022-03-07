package com.lucas.marvelheroesapp

import com.lucas.marvelheroes.di.MarvelComicsModule
import com.lucas.marvelheroes.domain.MarvelComicsRepository
import com.lucas.marvelheroes.util.MarvelComicsDispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton

/**
 * If you want to provide fakes you should @UninstallModules(ModuleToUninstall::class) and create a
 * new TestModule annotated with @InstallIn(CorrectComponent::class), where you're going to define
 * your fakes to be used. Then just create a lateinit var annotated with @Inject to provide it instead
 * of creating a mock
 */

/**
 * Test classes that use @UninstallModules, @BindValue, or nested @InstallIn modules result in a
 * custom component being generated for that test. While this may be fine in most cases, it does
 * have an impact on build speed. The recommended approach is to use @TestInstallIn modules instead.
 */

/**
 * @TestInstallIn
 * Applies Globally
 * Easier configuration
 * Better for build speed
 *
 * @UninstallModules
 * Single test only
 * Greater flexibility
 * Worse for build speed
 */

//Uninstall production @InstallIn modules for this particular test.
@UninstallModules(MarvelComicsModule::class)
@HiltAndroidTest
class MainHiltTest {

    /**
     * this rule is set to 0 because it has to be initialized before any other rule, if the test
     * class has other rules. For example ActivityScenarioRule calls Activity#onCreate, which
     * (for Hilt activities) requires the Hilt component to perform injection. Thus, the
     * ActivityScenarioRule should run after the HiltAndroidRule to ensure that the component has
     * been properly initialized.
     */
    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fakeComicsRepository: MarvelComicsRepository

    /**
     * We can define a variable annotated with @BindValue to provide our fake or we can define a
     * nested test module inside our test class. @BindValue is an annotation that allows you to
     * easily bind fields in your test into the Dagger graph
     */
//    @BindValue
//    val fakeComicsRepository = FakeMarvelComicsRepository().getMarvelComics().resultData

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun someTest() = runBlocking {
        Assert.assertTrue(fakeComicsRepository.getMarvelComics().resultData != null)
    }

    @Module
//    @TestInstallIn(
//        components = [SingletonComponent::class],
//        replaces = [MarvelComicsModule::class]
//    )
    @InstallIn(SingletonComponent::class)
    interface TestMarvelComicsModule {

        @Binds
        fun provideMarvelComicsRepository(fakeRepository: FakeMarvelComicsRepository): MarvelComicsRepository

        companion object {
            @Singleton
            @Provides
            fun provideMarvelComicsDispatchers(): MarvelComicsDispatcherProvider = object :
                MarvelComicsDispatcherProvider {
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
}