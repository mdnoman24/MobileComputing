package org.uni.mobilecomputinghomework1

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import org.uni.mobilecomputinghomework1.addfood.AddFoodScreen
import org.uni.mobilecomputinghomework1.detail.FoodDetailScreen
import org.uni.mobilecomputinghomework1.home.HomeScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen(navController)
        }
        composable(route = Screens.Food.route) {
            val foodId = it.arguments?.getString("id")?.toIntOrNull() ?: 6
            FoodDetailScreen(
                foodId = foodId,
                navController = navController
            )
        }
        composable(
            route = Screens.AddFood.route,
            deepLinks = listOf(navDeepLink { uriPattern = "myapp://addfood" })
        ) {
            AddFoodScreen(navController = navController)
        }
        // Camera Screen Route - Implemented directly in Navigation.kt
        composable(route = Screens.Camera.route) {
            val context = LocalContext.current
            var imageUri by remember { mutableStateOf<Uri?>(null) }
            var hasPermission by remember {
                mutableStateOf(
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                )
            }

            val cameraLauncher = rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
                if (success) imageUri = imageUri
            }

            val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                hasPermission = granted
            }

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                if (hasPermission) {
                    Button(onClick = {
                        val file = createImageFile(context)
                        imageUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
                        cameraLauncher.launch(imageUri)
                    }) {
                        Text("Open Camera")
                    }
                } else {
                    Button(onClick = { permissionLauncher.launch(Manifest.permission.CAMERA) }) {
                        Text("Request Camera Permission")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                imageUri?.let {
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = "Captured Image",
                        modifier = Modifier.fillMaxWidth().height(300.dp).padding(8.dp)
                    )
                }
            }
        }
    }
}

fun createImageFile(context: Context): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
}
