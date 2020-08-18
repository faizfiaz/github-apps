package com.github.apps.ui.home.adapter

import androidx.core.view.ViewCompat
import androidx.databinding.ObservableField
import com.github.apps.databinding.ItemUserBinding
import com.github.apps.domain.models.User
import java.util.*

class UserListItemViewModel(itemData: User?, var actionDetail: (User, ItemUserBinding) -> Unit,
                            var binding: ItemUserBinding) : Observable() {

    val imageUrl = ObservableField<String>("")
    val name = ObservableField<String>()
    val score = ObservableField<String>()
    val url = ObservableField<String>()

    var data: User? = itemData

    init {
        imageUrl.set(data?.avatar)
        name.set(data?.name)
        score.set(data?.score.toString())
        url.set(data?.url.toString())
    }

    fun goDetail() {
        ViewCompat.setTransitionName(binding.oval, "oval")
        ViewCompat.setTransitionName(binding.tvCompanyName, "companyName")
        ViewCompat.setTransitionName(binding.tvName, "name")
        with(binding) {
            executePendingBindings()
        }
        actionDetail(data!!, binding)
    }

}