package ua.zloydi.recipeapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import ua.zloydi.recipeapp.R
import ua.zloydi.recipeapp.data.ErrorProvider
import ua.zloydi.recipeapp.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
            ErrorProvider.service.getErrors().receiveAsFlow().collect {
                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        (supportFragmentManager.fragments.first() as MainFragment)
            .onNewIntent(intent)
    }
}