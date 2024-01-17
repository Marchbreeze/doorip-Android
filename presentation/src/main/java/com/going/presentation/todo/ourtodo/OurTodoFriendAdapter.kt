package com.going.presentation.todo.ourtodo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.going.domain.entity.response.TripParticipantModel
import com.going.presentation.databinding.ItemTodoFriendsBinding

class OurTodoFriendAdapter : RecyclerView.Adapter<OurTodoFriendViewHolder>() {

    private var itemList = mutableListOf<TripParticipantModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OurTodoFriendViewHolder {
        val binding: ItemTodoFriendsBinding =
            ItemTodoFriendsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OurTodoFriendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OurTodoFriendViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun submitList(newItems: List<TripParticipantModel>) {
        this.itemList.clear()
        this.itemList.addAll(newItems)
        notifyDataSetChanged()
    }
}