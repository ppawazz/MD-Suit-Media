package com.paw.mysuitmedia.view.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.paw.mysuitmedia.model.remote.response.DataItem
import com.paw.mysuitmedia.model.remote.service.ApiService

class UserPagingSource(
    private val apiService: ApiService, private val pageSize: Int
) : PagingSource<Int, DataItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getUsers(page = position, perPage = pageSize)
            val users = response.data.map {
                DataItem(
                    id = it.id,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    email = it.email,
                    avatar = it.avatar
                )
            }

            LoadResult.Page(
                data = users,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (users.size < pageSize) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}