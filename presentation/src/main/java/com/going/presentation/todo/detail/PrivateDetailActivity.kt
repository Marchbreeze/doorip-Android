package com.going.presentation.todo.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.going.presentation.R
import com.going.presentation.databinding.ActivityPrivateDetailBinding
import com.going.presentation.todo.detail.PublicDetailActivity.Companion.EXTRA_TODO_ID
import com.going.ui.base.BaseActivity
import com.going.ui.extension.EnumUiState
import com.going.ui.extension.setOnSingleClickListener
import com.going.ui.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PrivateDetailActivity :
    BaseActivity<ActivityPrivateDetailBinding>(R.layout.activity_private_detail) {

    private val viewModel by viewModels<PrivateDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewModel()
        initBackBtnClickListener()
        initDeleteBtnClickListener()
        initModBtnClickListener()
        setDetailData()
        observeTodoDetailState()
    }

    private fun initViewModel() {
        binding.vm = viewModel
    }

    private fun initBackBtnClickListener() {
        binding.btnMyTodoDetailBack.setOnSingleClickListener {
            finish()
        }
    }

    private fun initDeleteBtnClickListener() {
        binding.btnMyTodoDetailDelete.setOnSingleClickListener {
            // 삭제 서버 통신
            finish()
        }
    }

    private fun initModBtnClickListener() {
        binding.btnMyTodoDetailMod.setOnSingleClickListener {
            toast(getString(R.string.will_be_update))
        }
    }

    private fun setDetailData() {
        viewModel.getTodoDetailFromServer(intent.getLongExtra(EXTRA_TODO_ID, 0))
    }

    private fun observeTodoDetailState() {
        viewModel.todoDetailState.flowWithLifecycle(lifecycle).onEach { state ->
            when (state) {
                EnumUiState.LOADING -> return@onEach
                EnumUiState.SUCCESS -> return@onEach
                EnumUiState.FAILURE -> toast(getString(R.string.server_error))
                EnumUiState.EMPTY -> return@onEach
            }
        }.launchIn(lifecycleScope)
    }

}