package com.yomicepa.data.network.base

import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class BasePagingSource<T : Any> : PagingSource<Int, T>() {

    protected var response: List<T> = listOf()

    abstract override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T>

    override fun getRefreshKey(state: PagingState<Int, T>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIST_PAGE_SIZE) ?: anchorPage?.nextKey?.minus(LIST_PAGE_SIZE)
        }

    protected fun getPrevCursor(pageCursor: Int) =
        if (pageCursor == LIST_STARTING_INDEX) null else pageCursor

    protected fun getNextCursor(pageCursor: Int, loadSize: Int) =
        if (response.size < loadSize || response.isEmpty()) null else pageCursor + 1


    companion object {
        const val LIST_PAGE_SIZE = 20
        const val LIST_STARTING_INDEX = 1
    }
}