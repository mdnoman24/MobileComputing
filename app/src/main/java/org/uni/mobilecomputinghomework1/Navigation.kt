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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import org.uni.mobilecomputinghomework1.addfood.AddFoodScreen
import org.uni.mobilecomputinghomework1.detail.FoodDetailScreen
import org.uni.mobilecomputinghomework1.home.HomeScreen
import org.uni.mobilecomputinghomework1.ui.theme.LatteBrown
import org.uni.mobilecomputinghomework1.ui.theme.EspressoBlack

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

        // Camera Screen with Background Color
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

            // Background Color Applied
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFFD4A574)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
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

                            // Home Button
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = LatteBrown,
                                    contentColor = EspressoBlack,
                                    disabledContainerColor = LatteBrown,
                                    disabledContentColor = EspressoBlack
                                ),
                                onClick = {
                                    navController.popBackStack(Screens.Home.route, inclusive = false)
                                }
                            ) {
                                Text(stringResource(R.string.label_home))
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
    }
}

fun createImageFile(context: Context): File {
    val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
}
