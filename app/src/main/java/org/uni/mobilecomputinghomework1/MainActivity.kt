package org.uni.mobilecomputinghomework1

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.uni.mobilecomputinghomework1.ui.theme.MobileComputingHomework1Theme

//TODO: change the project name
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileComputingHomework1Theme {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0)
                }
                val navController = rememberNavController()
                Navigation(navController)
                if (intent?.action == "OPEN_ADD_FOOD") {
                    navController.navigate(Screens.AddFood.route)
                }
            }
        }
        Intent(this, ShakeDetectionService::class.java).also { intent ->
            startService(intent)
        }
    }
}