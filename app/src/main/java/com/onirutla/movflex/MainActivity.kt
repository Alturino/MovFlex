package com.onirutla.movflex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.onirutla.movflex.core.ui.MovFlexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovFlexApp {
                MovFlexContent()
            }
        }
//        setContentView(binding.root)

//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//        val navController = navHostFragment.navController
//
//        binding.bottomNav.setupWithNavController(navController)
//
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                movieR.id.movieDetailFragment -> hideBottomAppbar()
//                tvR.id.tvDetailFragment -> hideBottomAppbar()
//                movieR.id.movieMoreFragment -> hideBottomAppbar()
//                tvR.id.tvMoreFragment -> hideBottomAppbar()
//                else -> showBottomAppbar()
//            }
//        }

    }

    @Composable
    fun MovFlexApp(
        modifier: Modifier = Modifier,
        content: @Composable () -> Unit
    ) {
        MovFlexTheme {
            Surface(
                modifier = modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                content()
            }
        }
    }

    @Composable
    fun MovFlexContent() {
        val navController = rememberNavController()

        Navigation(navController = navController)
    }

//    private fun showBottomAppbar() {
//        binding.run {
//            bottomNav.visibility = View.VISIBLE
//        }
//    }

//    private fun hideBottomAppbar() {
//        binding.run {
//            bottomNav.visibility = View.GONE
//        }
//    }
}
