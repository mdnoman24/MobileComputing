//package org.uni.mobilecomputinghomework1.home
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.FloatingActionButton
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import org.uni.mobilecomputinghomework1.R
//import org.uni.mobilecomputinghomework1.Screens
//import org.uni.mobilecomputinghomework1.ui.theme.CoffeeCream
//import org.uni.mobilecomputinghomework1.ui.theme.EspressoBlack
//import org.uni.mobilecomputinghomework1.ui.theme.LatteBrown
//import org.uni.mobilecomputinghomework1.ui.theme.Caramel
//
//
//@Composable
//fun HomeScreen(
//    navController: NavController,
//    viewModel: HomeViewModel = hiltViewModel()
//) {
//    val foodList by viewModel.foodList.collectAsState()
//
//    Scaffold(
//        floatingActionButton = {
//            FloatingActionButton(
//                containerColor = Caramel,
//                contentColor = EspressoBlack,
//                modifier = Modifier.size(64.dp),
//                onClick = { navController.navigate(Screens.AddFood.route) }
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Add,
//                    contentDescription = stringResource(R.string.label_add)
//                )
//            }
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(CoffeeCream) // Set background color
//                .padding(paddingValues) // Avoid overlap with Scaffold padding
//        ) {
//            LazyColumn(
//                modifier = Modifier.fillMaxSize()
//            ) {
//                items(foodList) { food ->
//                    FoodItem(
//                        food = food,
//                        navController = navController,
//                    )
//                }
//            }
//        }
//    }
//}






package org.uni.mobilecomputinghomework1.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.uni.mobilecomputinghomework1.R
import org.uni.mobilecomputinghomework1.Screens
import org.uni.mobilecomputinghomework1.ui.theme.CoffeeCream
import org.uni.mobilecomputinghomework1.ui.theme.EspressoBlack
import org.uni.mobilecomputinghomework1.ui.theme.Caramel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val foodList by viewModel.foodList.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Caramel,
                contentColor = EspressoBlack,
                modifier = Modifier.size(64.dp),
                onClick = { navController.navigate(Screens.AddFood.route) }
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp) // Adjust size to fit inside FAB
                        .clip(CircleShape) // Ensure border follows circular shape
                        .border(2.dp, EspressoBlack, CircleShape) // Border around the icon
                        .padding(4.dp) // Padding to avoid icon touching border
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(R.string.label_add),
                        tint = EspressoBlack,
                        modifier = Modifier.fillMaxSize() // Fill the box area
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(CoffeeCream) // Set background color
                .padding(paddingValues) // Avoid overlap with Scaffold padding
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(foodList) { food ->
                    FoodItem(
                        food = food,
                        navController = navController,
                    )
                }
            }
        }
    }
}
