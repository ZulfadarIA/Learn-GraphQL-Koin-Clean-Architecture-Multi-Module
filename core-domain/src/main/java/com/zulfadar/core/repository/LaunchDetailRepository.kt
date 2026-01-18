package com.zulfadar.core.repository

import com.zulfadar.core.common.Result
import com.zulfadar.core.model.Booking
import com.zulfadar.core.model.LaunchDetail
import kotlinx.coroutines.flow.Flow

interface LaunchDetailRepository {
    suspend fun getLaunchDetailById(launchId: String): Result<LaunchDetail>
    suspend fun booking(launchId: String): Result<Booking>
    suspend fun cancelBooking(launchId: String): Result<Booking>
    fun observeTripsBooked(): Flow<Int?>
}