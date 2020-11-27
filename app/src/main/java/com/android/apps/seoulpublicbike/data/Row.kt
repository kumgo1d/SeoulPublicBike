package com.android.apps.seoulpublicbike.data

data class Row(
    val parkingBikeTotCnt: String,
    val rackTotCnt: String,
    val shared: String,
    val stationId: String,
    val stationLatitude: String,
    val stationLongitude: String,
    val stationName: String
)