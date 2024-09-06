package id.go.ngawikab.siketan.data.farm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import id.go.ngawikab.siketan.data.farm.model.farm.response.Petani
import id.go.ngawikab.siketan.data.farm.remote.FarmApi
import timber.log.Timber

class PetaniPagingSource(
    private val service: FarmApi,
    private val penyuluh: Int
) : PagingSource<Int, Petani>() {

    private val STARTING_PAGE_INDEX = 1
    private val PAGE_SIZE = 20

    override fun getRefreshKey(state: PagingState<Int, Petani>): Int? =
        state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Petani> =
        try {
            val position = params.key ?: STARTING_PAGE_INDEX

            // Fetch semua
            val res = service.getPetani(penyuluh)
            val list = res.body()?.petanis ?: emptyList()

            // Calculate sublist buat return as the current page (pagging manual)
            val fromIndex = (position - 1) * PAGE_SIZE
            val toIndex = kotlin.math.min(fromIndex + PAGE_SIZE, list.size)

            val currentPage = if (fromIndex < toIndex) list.subList(fromIndex, toIndex) else emptyList()

            Timber.tag("PagingLoad").d("SUCCESS LOAD")
            LoadResult.Page(
                data = currentPage,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (toIndex >= list.size) null else position + 1
            )

        } catch (e: Exception) {
            Timber.tag("PagingLoad").d(e.message.toString())
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Timber.tag("PagingLoad").d(e.message.toString())
            LoadResult.Error(e)
        }
}