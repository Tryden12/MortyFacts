package com.tryden.mortyfacts.characters

import androidx.paging.PageKeyedDataSource
import com.tryden.mortyfacts.SharedRepository
import com.tryden.mortyfacts.SharedViewModel
import com.tryden.mortyfacts.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CharactersDataSource(
    private val coroutineScope: CoroutineScope,
    private val repository: CharactersRepository
) : PageKeyedDataSource<Int, GetCharacterByIdResponse>(){

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, GetCharacterByIdResponse>,
    ) {
        coroutineScope.launch {
            val page = repository.getCharactersPage(1) // safe to hard code 1

            if (page == null) {
                callback.onResult(emptyList(), null, null)
                return@launch
            }

            callback.onResult(page.results, null, getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>,
    ) {
        coroutineScope.launch {
            val page = repository.getCharactersPage(params.key)

            if (page == null) {
                callback.onResult(emptyList(), null)
                return@launch
            }

            callback.onResult(page.results, getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, GetCharacterByIdResponse>,
    ) {
        // Nothing to do
    }

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }
}