package com.paw.mysuitmedia.model

import com.paw.mysuitmedia.model.service.ApiService
import com.paw.mysuitmedia.model.paging.UserPagingSource

class UserRepository(private val apiService: ApiService) {

    fun getUserPagingSource(pageSize: Int) = UserPagingSource(apiService, pageSize)
}