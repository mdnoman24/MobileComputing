package org.uni.mobilecomputinghomework1.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imagePath: String,
    val name: String,
    val description: String
)