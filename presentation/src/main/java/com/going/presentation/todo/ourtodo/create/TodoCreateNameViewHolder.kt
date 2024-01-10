package com.going.presentation.todo.ourtodo.create

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.going.presentation.R
import com.going.presentation.databinding.ItemTodoCreateNameBinding
import com.going.ui.extension.setOnSingleClickListener

class TodoCreateNameViewHolder(
    val binding: ItemTodoCreateNameBinding,
    private val isFixed: Boolean
) : RecyclerView.ViewHolder(binding.root) {

    private val whiteColor = ContextCompat.getColor(binding.root.context, R.color.white_000)
    private val grayColor = ContextCompat.getColor(binding.root.context, R.color.gray_300)

    fun onBind(item: String) {
        binding.run {
            tvTodoName.text = item

            if (item == "김상호") {
                tvTodoName.setBackgroundResource(R.drawable.sel_todo_shape_red500_fill)
            } else {
                tvTodoName.setBackgroundResource(R.drawable.sel_todo_shape_gray400_fill)
            }

            if (isFixed) {
                tvTodoName.isSelected = true
                tvTodoName.setTextColor(whiteColor)
            } else {
                layoutTodoName.setOnClickListener {
                    tvTodoName.isSelected = !tvTodoName.isSelected
                    if (tvTodoName.isSelected) {
                        tvTodoName.setTextColor(whiteColor)
                    } else {
                        tvTodoName.setTextColor(grayColor)
                    }
                }
            }
        }
    }

}