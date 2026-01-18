package com.zulfadar.feature.launchdetail

data class BookingState(
    val loading: Boolean = false,
    val isBooked: Boolean = false,
    val requireLogin: Boolean = false,
    val error: String? = null
)
