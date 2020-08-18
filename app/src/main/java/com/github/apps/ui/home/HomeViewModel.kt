package com.github.apps.ui.home

import android.text.Editable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.apps.R
import com.github.apps.databinding.ItemUserBinding
import com.github.apps.domain.models.User
import com.github.apps.domain.usecases.user.IUserUsecases
import com.github.apps.ui.base.BaseViewModel
import com.github.apps.ui.home.adapter.UserListAdapter
import com.github.apps.utils.AndroidUtils
import com.github.apps.utils.SchedulerProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

open class HomeViewModel(baseUsecase: IUserUsecases, schedulerProvider: SchedulerProvider)
    : BaseViewModel<IUserUsecases, HomeNavigator>(baseUsecase, schedulerProvider) {

    var search = ObservableField<String>()
    var isEmpty = ObservableBoolean(true)
    var isLoadingLoadMore = false

    var userListAdapter = UserListAdapter(ArrayList(), ::goToDetailUser)

    var page: Int = 1

    override fun defineLayout() {
        appBarTitle.set(AndroidUtils.getString(R.string.app_name))
    }

    fun afterTextSearchChanged(s: Editable) {
        page = 1
        search.set(s.toString())
        doGetUser(page)
    }

    fun getAdapter(): UserListAdapter {
        return userListAdapter
    }

    private fun goToDetailUser(user: User, binding: ItemUserBinding) {

    }


    private fun doGetUser(page: Int) {
        viewModelScope.launch {
            if (!isLoadingLoadMore) {
                isLoading(true)
            }
            try {
                val responseApi = baseUsecase.getUser(search.get(), page)
                checkResponse(responseApi.blockingGet())
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun checkResponse(responseApi: Any?) {
        isLoading(false)
        if (responseApi is String) {
            navigator?.showError(responseApi.toString())
        } else {
            populateData(responseApi as List<User>)
        }
        if (isLoadingLoadMore) {
            isLoadingLoadMore = false
        }
    }

    private fun populateData(list: List<User>) {
        isEmpty.set(list.isEmpty())
        if (!isLoadingLoadMore) {
            userListAdapter.clearItems()
        } else {
            userListAdapter.clearLatest()
        }
        userListAdapter.addItems(list)
        runBlocking { }
    }

    override fun onSuccess(o: Any?) {
        isLoading(false)
        if (o is Boolean) {
            navigator?.movePage()
        } else {
            navigator?.showError(o.toString())
        }
    }

    fun loadMoreData(linearLayoutManager: LinearLayoutManager?) {
        if (!isLoadingLoadMore) {
            if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == userListAdapter.itemCount - 1) {
                userListAdapter.addItem(User())
                isLoadingLoadMore = true
                doGetUser(++page);
            }
        }

    }


}