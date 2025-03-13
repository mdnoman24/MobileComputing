package org.uni.mobilecomputinghomework1.map

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import org.uni.mobilecomputinghomework1.R
import org.uni.mobilecomputinghomework1.Screens
import org.uni.mobilecomputinghomework1.ui.theme.EspressoBlack
import org.uni.mobilecomputinghomework1.ui.theme.LatteBrown


@Composable
fun MapScreen(navController: NavController) {
    val restaurantLocation = LatLng(60.1699, 24.9384) // Example: Helsinki, Finland
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(restaurantLocation, 15f)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = rememberMarkerState(position = restaurantLocation),
                title = "Restaurant Location",
                snippet = "Best restaurant in town!"
            )
        }
    }

    Spacer(modifier = Modifier.height(12.dp))
    Button(
        colors = ButtonColors(
            containerColor = LatteBrown, // Warm brown button
            contentColor = EspressoBlack, // White text
            disabledContentColor = EspressoBlack,
            disabledContainerColor = LatteBrown
        ), onClick = {
            navController.popBackStack(Screens.Home.route, inclusive = false)
        }
    ) {
        Text(stringResource(R.string.label_home))
    }

}

