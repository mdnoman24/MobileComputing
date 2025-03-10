package org.uni.mobilecomputinghomework1.addfood

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.uni.mobilecomputinghomework1.R
import org.uni.mobilecomputinghomework1.datasource.Food
import org.uni.mobilecomputinghomework1.saveImageToInternalStorage

import org.uni.mobilecomputinghomework1.ui.theme.CoffeeCream
import org.uni.mobilecomputinghomework1.ui.theme.Gold
import org.uni.mobilecomputinghomework1.ui.theme.Caramel
import org.uni.mobilecomputinghomework1.ui.theme.EspressoBlack
import org.uni.mobilecomputinghomework1.ui.theme.LatteBrown


@Composable
fun AddFoodScreen(
    navController: NavController,
    viewModel: AddFoodViewModel = hiltViewModel()
) {
    var loadedUrl by remember { mutableStateOf<String?>(null) }
    var imageUrl by remember { mutableStateOf("") }
    var imagePath by remember { mutableStateOf("") }

    var foodDescription by remember { mutableStateOf("") }
    var foodName by remember { mutableStateOf("") }

    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        loadedUrl = uri.toString()
        uri?.let {
            imagePath = saveImageToInternalStorage(context, it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(CoffeeCream) // Set background color
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .background(color = Caramel, shape = RoundedCornerShape(16.dp))
                    .clickable { imagePickerLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (loadedUrl != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(loadedUrl)
                            .crossfade(true)
                            .listener(
                                onError = { _, _ ->
                                    Toast.makeText(
                                        context,
                                        "Image Loading Failed",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    loadedUrl = null
                                }
                            )
                            .build(),
                        contentDescription = stringResource(R.string.label_loaded_image),
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Text(stringResource(R.string.label_tap_to_pick_image))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = imageUrl,
                onValueChange = { imageUrl = it },
                label = { Text(stringResource(R.string.label_enter_image_url)) },
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            loadedUrl = imageUrl
                            imagePath = loadedUrl!!
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Load Image"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = foodName,
                onValueChange = { foodName = it },
                label = { Text(stringResource(R.string.label_food_name)) },
                modifier = Modifier.fillMaxWidth(),

            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = foodDescription,
                onValueChange = { foodDescription = it },
                label = { Text(stringResource(R.string.label_food_description)) },
                modifier = Modifier.fillMaxWidth(),

            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    colors = ButtonColors(
                        containerColor = Caramel,
                        contentColor = EspressoBlack,
                        disabledContentColor = EspressoBlack,
                        disabledContainerColor = Caramel
                    ),
                    onClick = {
                        navController.navigateUp()
                    }
                ) {
                    Text(stringResource(R.string.label_cancel))
                }
                Button(
                    modifier = Modifier.weight(1f),
                    enabled = foodName.isNotBlank() && foodDescription.isNotBlank(),
                    colors = ButtonColors(
                        containerColor = Caramel,
                        contentColor = EspressoBlack,
                        disabledContentColor = EspressoBlack,
                        disabledContainerColor = Caramel
                    ),
                    onClick = {
                        viewModel.addFood(
                            Food(
                                imagePath = imagePath,
                                name = foodName,
                                description = foodDescription
                            )
                        )
                        navController.navigateUp()
                    }
                ) {
                    Text(stringResource(R.string.label_add))
                }
            }
        }
    }
}
