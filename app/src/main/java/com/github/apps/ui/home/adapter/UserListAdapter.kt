package com.github.apps.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.apps.databinding.ItemUserBinding
import com.github.apps.databinding.LoadingRecyclerBinding
import com.github.apps.domain.models.User
import com.github.apps.ui.base.BaseAdapter

class UserListAdapter(data: ArrayList<User>, action: (User, ItemUserBinding) -> Unit) : BaseAdapter<User>(data) {

    private val action: (User, ItemUserBinding) -> Unit = action
    private lateinit var binding: ItemUserBinding

    private val VIEW_ITEM = 1
    private val VIEW_PROG = 0

    override fun createHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_ITEM) {
            binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false)
            GenericViewHolder(binding)
        } else {
            GenericViewHolder(LoadingRecyclerBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position)?.id != null) VIEW_ITEM else VIEW_PROG
    }

    override fun bindingViewHolder(holder: GenericViewHolder, position: Int) {
        if (holder.viewDataBinding is ItemUserBinding) {
            (holder.viewDataBinding as ItemUserBinding).itemViewModel =
                    UserListItemViewModel(getItem(position), action, binding)
        }
    }

}