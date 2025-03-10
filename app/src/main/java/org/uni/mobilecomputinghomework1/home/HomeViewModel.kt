package org.uni.mobilecomputinghomework1.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.uni.mobilecomputinghomework1.FakeFoodData
import org.uni.mobilecomputinghomework1.datasource.FoodDao
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dao: FoodDao
) : ViewModel() {

    init {
        viewModelScope.launch {
            FakeFoodData.foodList.forEach { dao.insertFood(it) }
        }
    }

    val foodList = dao.getAllFoods().stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        emptyList()
    )

}
