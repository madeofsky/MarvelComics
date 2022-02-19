package com.lucas.marvelheroes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucas.marvelheroes.ui.MainFragment
import com.lucas.marvelheroesactivity.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}