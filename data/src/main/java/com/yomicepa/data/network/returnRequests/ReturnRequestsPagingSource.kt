package com.yomicepa.data.network.returnRequests

import com.yomicepa.data.network.RetrofitClientExt.apiCall
import com.yomicepa.data.network.base.BasePagingSource

class ReturnRequestsPagingSource(private val returnRequestsApi: ReturnRequestsApi) :
    BasePagingSource<ReturnRequestContent>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReturnRequestContent> {
        val pageCursor = params.key ?: LIST_STARTING_INDEX
        return try {
            apiCall {
                returnRequestsApi.getReturnRequests(pageCursor)
            }.map {
                response = it.content
            }

            val prevCursor = getPrevCursor(pageCursor)
            val nextCursor = getNextCursor(pageCursor, params.loadSize)

            LoadResult.Page(data = response, prevKey = prevCursor, nextKey = nextCursor)
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}