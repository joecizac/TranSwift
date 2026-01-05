package com.jozze.transwift.data.db

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule WHERE train_id = :trainId")
    fun getScheduleForTrain(trainId: Int): Flow<List<ScheduleEntity>>

    @Query("SELECT * FROM schedule WHERE station_name = :stationName ORDER BY arrival_time ASC")
    fun getScheduleForTrainForStation(stationName: String): Flow<List<ScheduleEntity>>

//    @Query("SELECT DISTINCT train_id FROM schedule WHERE station_name IN (:sourceStationName, :destinationStationName) ORDER BY arrival_time ASC")
//    fun getScheduleForConnectingTrain(
//        sourceStationName: String,
//        destinationStationName: String
//    ): Flow<List<ScheduleEntity>>
}