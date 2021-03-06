package com.goldcompany.apps.koreabike.db.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "list_item")
class FavoriteListItem(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo var no: Long?,
    @ColumnInfo var station: String,
    @ColumnInfo var parkingBike: String,
    @ColumnInfo var rackBike: String,
    @ColumnInfo var date: Long = Date().time
)