package com.github.apps.utils

import androidx.recyclerview.widget.RecyclerView

class DraggingDirectionScrollListener(action: (Boolean) -> Unit)
    : RecyclerView.OnScrollListener() {

    private val action: (Boolean) -> Unit = action

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (dy <= 0) {
            action(true)
        } else {
            action(false)
        }

    }
}