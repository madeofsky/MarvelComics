package com.lucas.marvelheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucas.marvelheroes.ui.MarvelHeroesListFragment
import com.lucas.marvelheroesactivity.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelHeroesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marvel_heroes_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MarvelHeroesListFragment.newInstance())
                .commitNow()
        }
    }
}