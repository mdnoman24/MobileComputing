package org.uni.mobilecomputinghomework1.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.uni.mobilecomputinghomework1.datasource.Food
import org.uni.mobilecomputinghomework1.datasource.FoodDao
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val dao: FoodDao
) : ViewModel() {

    val maxId: StateFlow<Int?> = dao.getMaxId()
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    fun getFoodById(foodId: Int): Flow<Food> {
        return dao.getFood(foodId)
    }

}