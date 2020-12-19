package com.demo.leanagri.utils.callbacks;

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class PaginationScrollListener :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = (recyclerView.layoutManager as LinearLayoutManager?)!!
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()


        if (visibleItemCount + firstVisibleItemPosition >=
            totalItemCount && firstVisibleItemPosition >= 0
        ) {
            if (!isLoading() && !isLastPage()) {
                loadMoreItems()

            }
        }
    }

    protected abstract fun loadMoreItems()
    protected abstract fun  isLastPage() : Boolean
    protected abstract fun  isLoading(): Boolean

}