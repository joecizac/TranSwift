package com.jozze.transwift.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trains")
data class TrainEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "train_number") val trainNumber: String,
    @ColumnInfo(name = "train_code") val trainCode: String,
)