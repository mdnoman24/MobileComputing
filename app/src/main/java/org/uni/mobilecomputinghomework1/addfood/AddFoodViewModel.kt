package org.uni.mobilecomputinghomework1.addfood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.uni.mobilecomputinghomework1.datasource.Food
import org.uni.mobilecomputinghomework1.datasource.FoodDao
import javax.inject.Inject

@HiltViewModel
class AddFoodViewModel @Inject constructor(
    private val dao: FoodDao
) : ViewModel() {
    fun addFood(food: Food) {
        viewModelScope.launch {
            dao.insertFood(food)
        }
    }
}
