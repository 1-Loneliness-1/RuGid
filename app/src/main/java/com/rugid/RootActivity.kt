package com.rugid

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.rugid.databinding.ActivityRootBinding
import com.rugid.feature.main.ui.MainFragment

class RootActivity : AppCompatActivity() {

    private var binding: ActivityRootBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // Временно для реализации фичи с обновлением контента отображаем только главный экран в контейнере
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fcvRootContainer, MainFragment())
                .commit()
        }
    }
}