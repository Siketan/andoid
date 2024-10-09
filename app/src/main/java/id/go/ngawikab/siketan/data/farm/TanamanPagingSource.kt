package id.go.ngawikab.siketan.data.farm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import id.go.ngawikab.siketan.data.farm.model.farm.response.PlantFarmerData
import id.go.ngawikab.siketan.data.farm.remote.FarmApi
import timber.log.Timber

class TanamanPagingSource(
    private val service: FarmApi,
    private val petani: Int
) : PagingSource<Int, PlantFarmerData>() {

    private val STARTING_PAGE_INDEX = 1
//    private val PAGE_SIZE = 20

    override fun getRefreshKey(state: PagingState<Int, PlantFarmerData>): Int? =
        state.anchorPosition?.let { pos ->
            state.closestPageToPosition(pos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(pos)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PlantFarmerData> =
        try {
//            val position = params.key ?: STARTING_PAGE_INDEX
//            // Fetch semua
//            val res = service.getTanaman(id = petani, page = position)
//            val list = res.body()?.data ?: emptyList()
//            // Calculate sublist buat return as the current page (pagging manual)
//            val fromIndex = (position - 1) * PAGE_SIZE
//            val toIndex = kotlin.math.min(fromIndex + PAGE_SIZE, list.size)
//            val currentPage = if (fromIndex < toIndex) list.subList(fromIndex, toIndex) else emptyList()
//            Timber.tag("PagingLoad").d("SUCCESS LOAD")
//            LoadResult.Page(
//                data = currentPage,
//                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
//                nextKey = if (toIndex >= list.size) null else position + 1
//            )
            val position = params.key ?: STARTING_PAGE_INDEX
            val res = service.getTanaman(id = petani, page = position)


            val list = res.body()!!.data
            Timber.tag("PagingLoad").d("SUCCESS LOAD")
            LoadResult.Page(
                data = list,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (list.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            Timber.tag("PagingLoad").d(e.message.toString())
            LoadResult.Error(e)
        } catch (e: HttpException) {
            Timber.tag("PagingLoad").d(e.message.toString())
            LoadResult.Error(e)
        }
}