package org.uni.mobilecomputinghomework1.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import org.uni.mobilecomputinghomework1.Screens
import org.uni.mobilecomputinghomework1.datasource.Food
import org.uni.mobilecomputinghomework1.ui.theme.MobileComputingHomework1Theme


import org.uni.mobilecomputinghomework1.ui.theme.CoffeeCream
import org.uni.mobilecomputinghomework1.ui.theme.Gold
import org.uni.mobilecomputinghomework1.ui.theme.Caramel
import org.uni.mobilecomputinghomework1.ui.theme.EspressoBlack
import org.uni.mobilecomputinghomework1.ui.theme.LatteBrown

@Composable
fun FoodItem(navController: NavController, food: Food) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(Caramel)
            .border(
                width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                navController.navigate(Screens.Food.createRoute(id = food.id))
            }
    ) {
        AsyncImage(
            model = food.imagePath,
            modifier = Modifier
                .size(72.dp)
                .padding(start = 7.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(width = 1.dp, color = LatteBrown, shape = RoundedCornerShape(10.dp)),
            contentDescription = food.name,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = LatteBrown)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = food.name,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                style = MaterialTheme.typography.titleMedium,
                color = EspressoBlack
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                text = food.description,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FoodItemPreview() {
    MobileComputingHomework1Theme {
        FoodItem(
            navController = rememberNavController(),
            food = Food(
                imagePath = "https://i.pinimg.com/736x/34/f4/8f/34f48f5c56c938642b80b0555e5adf82.jpg",
                name = "pizza",
                description = "A beloved Italian dish made with a baked dough base, topped with tomato sauce, cheese, and a variety of toppings like vegetables or meat."
            )
        )
    }
}