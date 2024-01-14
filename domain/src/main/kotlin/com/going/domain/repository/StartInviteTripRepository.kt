package com.going.domain.repository

import com.going.domain.entity.request.StartInviteTripRequestModel
import com.going.domain.entity.response.StartInviteTripModel

interface StartInviteTripRepository {
    suspend fun postStartInviteTrip(
        tripId: Long,
        requestStartInviteTripModel: StartInviteTripRequestModel
    ): Result<StartInviteTripModel>
}
