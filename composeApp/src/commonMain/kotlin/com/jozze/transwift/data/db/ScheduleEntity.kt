package com.jozze.transwift.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "schedule",
    foreignKeys = [ForeignKey(
        entity = TrainEntity::class,
        parentColumns = ["id"],
        childColumns = ["train_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ScheduleEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "train_id") val trainId: Int,
    @ColumnInfo(name = "station_name") val stationName: Int,
    @ColumnInfo(name = "arrival_time") val arrivalTime: Int,
    @ColumnInfo(name = "stop_sequence") val stopSequence: Int,
)
