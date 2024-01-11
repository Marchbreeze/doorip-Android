package com.going.presentation.dashboard.triplist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.going.domain.entity.response.DashBoardModel
import com.going.presentation.databinding.ItemDashBoardCompletedBinding
import com.going.ui.extension.ItemDiffCallback

class CompletedAdapter(
    private val listener: OnDashBoardSelectedListener
) : ListAdapter<DashBoardModel.DashBoardTripModel, CompletedViewHolder>(diffUtil) {

    interface OnDashBoardSelectedListener {
        fun onDashBoardSelectedListener(tripCreate: DashBoardModel.DashBoardTripModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedViewHolder {
        val binding: ItemDashBoardCompletedBinding =
            ItemDashBoardCompletedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CompletedViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: CompletedViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val diffUtil = ItemDiffCallback<DashBoardModel.DashBoardTripModel>(
            onItemsTheSame = { old, new -> old.title == new.title },
            onContentsTheSame = { old, new -> old == new },
        )
    }

}