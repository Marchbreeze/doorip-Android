package com.going.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.going.domain.entity.response.DashBoardModel
import com.going.domain.repository.DashBoardRepository
import com.going.ui.extension.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val dashBoardRepository: DashBoardRepository
) : ViewModel() {
    private val _dashBoardOngoingListState =
        MutableStateFlow<UiState<List<DashBoardModel>>>(UiState.Empty)
    val dashBoardOngoingListState: StateFlow<UiState<List<DashBoardModel>>> get() = _dashBoardOngoingListState

    private val _dashBoardCompletedListState =
        MutableStateFlow<UiState<List<DashBoardModel>>>(UiState.Empty)
    val dashBoardCompletedListState: StateFlow<UiState<List<DashBoardModel>>> get() = _dashBoardCompletedListState
    fun getTripListFromServer(
        progress: String
    ) {
        val dashBoardListState = if (progress == ONGOING) {
            _dashBoardOngoingListState
        } else {
            _dashBoardCompletedListState
        }
        dashBoardListState.value = UiState.Loading
        viewModelScope.launch {
            dashBoardRepository.getDashBoardList(progress)
                .onSuccess {
                    dashBoardListState.value = UiState.Success(it)
                }
                .onFailure {
                    dashBoardListState.value = UiState.Failure(it.message.orEmpty())
                }
        }
    }


    companion object {
        const val ONGOING = "incomplete"
        const val COMPLETED = "complete"
    }

}