package com.lucas.marvelheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucas.marvelheroes.ui.MarvelHeroesListFragment
import com.lucas.marvelheroesactivity.R
import com.lucas.marvelheroesactivity.databinding.MarvelHeroesActivityBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelHeroesActivity : AppCompatActivity() {

    private lateinit var binding: MarvelHeroesActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MarvelHeroesActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MarvelHeroesListFragment())
                .commitNow()
        }
    }
}