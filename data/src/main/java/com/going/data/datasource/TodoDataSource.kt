package com.going.data.datasource

import com.going.data.dto.BaseResponse
import com.going.data.dto.NullableBaseResponse
import com.going.data.dto.request.TodoCreateRequestDto
import com.going.data.dto.response.TodoResponseDto

interface TodoDataSource {

    suspend fun getTodoListData(
        tripId: Long,
        category: String,
        progress: String
    ): BaseResponse<List<TodoResponseDto>>

    suspend fun postToCreateTodoData(
        tripId: Long,
        request: TodoCreateRequestDto
    ): NullableBaseResponse<Unit>

}