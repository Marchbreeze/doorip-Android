package com.going.data.datasource;

import com.going.data.dto.BaseResponse
import com.going.data.dto.request.EnterTripRequestDto
import com.going.data.dto.request.StartInviteTripRequestDto
import com.going.data.dto.response.EnterTripResponseDto
import com.going.data.dto.response.StartInviteTripResponseDto
import retrofit2.http.Body
import retrofit2.http.Path

interface EnterTripDataSource {
    suspend fun postEnterTrip(
        @Body request: EnterTripRequestDto,
    ): BaseResponse<EnterTripResponseDto>

    suspend fun postStartInviteTrip(
        @Path("tripId") tripId: Long,
        @Body request: StartInviteTripRequestDto,
    ): BaseResponse<StartInviteTripResponseDto>
}
