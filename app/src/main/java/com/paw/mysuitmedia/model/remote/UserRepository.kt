package com.paw.mysuitmedia.model.remote

import com.paw.mysuitmedia.model.remote.service.ApiService
import com.paw.mysuitmedia.view.paging.UserPagingSource

class UserRepository(private val apiService: ApiService) {

    fun getUserPagingSource(pageSize: Int) = UserPagingSource(apiService, pageSize)
}