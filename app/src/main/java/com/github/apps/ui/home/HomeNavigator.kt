package com.github.apps.ui.home

import com.github.apps.ui.base.BaseNavigator

interface HomeNavigator : BaseNavigator {
    fun showError(message: String)
    fun movePage()
}