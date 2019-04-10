package ph.com.southstardrug.www.southstardrug.util

import android.os.Handler
import android.support.v4.os.HandlerCompat.postDelayed
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.RecyclerView


internal class EndlessScrollListener(private val refreshList: RefreshList) : RecyclerView.OnScrollListener() {
    private var isLoading: Boolean = false
    private var hasMorePages: Boolean = false
    private var pageNumber = 0
    private var isRefreshing: Boolean = false
    private var pastVisibleItems: Int = 0

    init {
        this.isLoading = false
        this.hasMorePages = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val manager = recyclerView.layoutManager as LinearLayoutManager?

        val visibleItemCount = manager!!.childCount
        val totalItemCount = manager.itemCount
        val firstVisibleItems = manager.findFirstVisibleItemPosition()
        if (firstVisibleItems != null && firstVisibleItems > 0) {
            pastVisibleItems = firstVisibleItems
        }

        if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
            isLoading = true
            if (hasMorePages && !isRefreshing) {
                isRefreshing = true
                Handler().postDelayed(Runnable { refreshList.onRefresh(firstVisibleItems) }, 200)
            }else{
            }
        } else {
            isLoading = false
            //notifyMorePages()
        }
    }

    fun noMorePages() {
        this.hasMorePages = false
    }

    fun notifyMorePages() {
        isRefreshing = false
        pageNumber = pageNumber
    }

    internal interface RefreshList {
        fun onRefresh(pageNumber: Int)
    }
}