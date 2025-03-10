package org.uni.mobilecomputinghomework1

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.uni.mobilecomputinghomework1.datasource.FoodDao
import org.uni.mobilecomputinghomework1.datasource.FoodDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FoodDatabase {
        return Room.databaseBuilder(context, FoodDatabase::class.java, "food_database").build()
    }

    @Provides
    fun provideFoodDao(database: FoodDatabase): FoodDao {
        return database.foodDao()
    }
}
