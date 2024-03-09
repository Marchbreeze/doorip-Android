package com.going.presentation.profile.participant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.going.domain.entity.request.ParticipantProfileRequestModel
import com.going.domain.entity.response.ParticipantProfileResponseModel
import com.going.domain.repository.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ParticipantProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository,
) : ViewModel() {
    private val _participantProfile =
        MutableSharedFlow<ParticipantProfileResponseModel?>()
    val participantProfile: SharedFlow<ParticipantProfileResponseModel?> = _participantProfile

    lateinit var profileTmp: ParticipantProfileResponseModel

    var number: Int = 0
    var tripId: Long = 0

    fun getUserInfoState(participantId: Long) {
        viewModelScope.launch {
            profileRepository.getParticipantProfile(ParticipantProfileRequestModel(participantId))
                .onSuccess {
                    number = it.result
                    profileTmp = it
                    _participantProfile.emit(it)
                }.onFailure {
                    _participantProfile.emit(null)
                }
        }
    }
}