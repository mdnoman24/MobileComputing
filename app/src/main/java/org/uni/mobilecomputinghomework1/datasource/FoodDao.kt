package org.uni.mobilecomputinghomework1.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: Food)

    @Query("SELECT * from food_table WHERE id = :id")
    fun getFood(id: Int): Flow<Food>

    @Query("SELECT * from food_table ORDER BY id ASC")
    fun getAllFoods(): Flow<List<Food>>

    @Query("SELECT MAX(id) FROM food_table")
    fun getMaxId(): Flow<Int?>

}
