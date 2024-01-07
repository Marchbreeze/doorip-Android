package com.going.presentation.todo.mytodo.todolist

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.going.presentation.todo.ourtodo.todolist.OurTodoCompleteFragment
import com.going.presentation.todo.ourtodo.todolist.OurTodoUncompleteFragment

class MyTodoViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MyTodoUncompleteFragment()
            else -> MyTodoCompleteFragment()
        }
    }
}