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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovFlexApp {
                MovFlexContent()
            }
        }
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
}
