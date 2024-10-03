package id.go.ngawikab.siketan.data.farm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import id.go.ngawikab.siketan.data.farm.model.store.ProductResponse
import id.go.ngawikab.siketan.data.farm.remote.FarmApi
import timber.log.Timber

class StorePagingSource (
    private val service: FarmApi,
): PagingSource<Int, ProductResponse>() {

    private val STARTING_PAGE_INDEX = 1
    override fun getRefreshKey(state: PagingState<Int, ProductResponse>): Int? =
        state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponse> =
        try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val res =  service.getProductbyPaging(position)


            val list = res.body()!!.data
            Timber.tag("PagingLoad").d("SUCCESS LOAD")
            LoadResult.Page(
                data = list,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (list.isEmpty()) null else position + 1
            )

        }catch (e: Exception){
            Timber.tag("PagingLoad").d(e.message.toString())
            LoadResult.Error(e)
        }catch (e: HttpException){
            Timber.tag("PagingLoad").d(e.message.toString())
            LoadResult.Error(e)
        }
}