package com.tryden.mortyfacts.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tryden.mortyfacts.network.response.GetCharacterByIdResponse
import com.tryden.mortyfacts.util.Constants.PAGE_SIZE
import com.tryden.mortyfacts.util.Constants.PREFETCH_DISTANCE

class CharactersViewModel: ViewModel() {

    private val repository = CharactersRepository()
    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = CharactersDataSourceFactory(viewModelScope, repository)
    val charactersPagedListLiveData: LiveData<PagedList<GetCharacterByIdResponse>> =
        LivePagedListBuilder(dataSourceFactory, pageListConfig).build()
}