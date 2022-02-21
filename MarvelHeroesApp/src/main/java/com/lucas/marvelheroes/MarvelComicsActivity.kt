package com.lucas.marvelheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucas.marvelheroesapp.R
import com.lucas.marvelheroesapp.databinding.MarvelComicsActivityBinding
import com.lucas.marvelheroes.ui.MarvelComicsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelComicsActivity : AppCompatActivity() {

    private lateinit var binding: MarvelComicsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MarvelComicsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MarvelComicsListFragment())
                .commitNow()
        }
    }
}