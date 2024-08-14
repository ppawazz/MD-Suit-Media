package com.paw.mysuitmedia.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.paw.mysuitmedia.model.UserRepository
import com.paw.mysuitmedia.model.response.DataItem
import com.paw.mysuitmedia.model.service.ApiConfig
import kotlinx.coroutines.flow.Flow

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository(ApiConfig.getApiService())
    val users: Flow<PagingData<DataItem>> = Pager(
        config = PagingConfig(pageSize = 5),
        pagingSourceFactory = { userRepository.getUserPagingSource(5) }
    ).flow.cachedIn(viewModelScope)
}