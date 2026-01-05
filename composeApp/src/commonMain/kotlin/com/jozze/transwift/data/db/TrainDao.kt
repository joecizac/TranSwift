package com.jozze.transwift.data.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainDao {

    @Query("SELECT * FROM trains")
    fun getAllTrains(): Flow<List<TrainEntity>>

    @Query("SELECT * FROM trains WHERE train_number = :number")
    fun getTrainByNumber(number: String): Flow<List<TrainEntity>>
}