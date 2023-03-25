package com.onirutla.movflex

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.onirutla.movflex.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
//                R.id.movieDetailFragment -> hideBottomAppbar()
                R.id.tvDetailFragment -> hideBottomAppbar()
                else -> showBottomAppbar()
            }
        }

    }

    private fun showBottomAppbar() {
        binding.run {
            bottomNav.visibility = View.VISIBLE
        }
    }

    private fun hideBottomAppbar() {
        binding.run {
            bottomNav.visibility = View.GONE
        }
    }
}
