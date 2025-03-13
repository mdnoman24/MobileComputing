package org.uni.mobilecomputinghomework1.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import org.uni.mobilecomputinghomework1.R
import org.uni.mobilecomputinghomework1.Screens
import org.uni.mobilecomputinghomework1.ui.theme.CoffeeCream
import org.uni.mobilecomputinghomework1.ui.theme.Gold
import org.uni.mobilecomputinghomework1.ui.theme.Caramel
import org.uni.mobilecomputinghomework1.ui.theme.EspressoBlack
import org.uni.mobilecomputinghomework1.ui.theme.LatteBrown
import org.uni.mobilecomputinghomework1.ui.theme.MobileComputingHomework1Theme

@Composable
fun FoodDetailScreen(
    foodId: Int,
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val food = viewModel.getFoodById(foodId).collectAsState(null)
    val maxId by viewModel.maxId.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFCCFFCC)), // Light green background
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        AsyncImage(
            model = food.value?.imagePath,
            modifier = Modifier
                .size(299.dp)
                .clip(RoundedCornerShape(1.dp))
                .border(width = 2.dp, color = LatteBrown, shape = RoundedCornerShape(1.dp)), // Warm brown border
            contentScale = ContentScale.Crop,
            contentDescription = "food image"
        )
        Spacer(modifier = Modifier.height(92.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {

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
    }
}

