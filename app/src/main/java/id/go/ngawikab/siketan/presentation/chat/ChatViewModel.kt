package id.go.ngawikab.siketan.presentation.chat

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import id.go.ngawikab.siketan.data.farm.model.farm.response.Petani
import id.go.ngawikab.siketan.domain.farm.FarmUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class ChatViewModel(
    private val useCase: FarmUseCase
) : ViewModel() {

    fun users(id:Int) : Flow<PagingData<Petani>> {
        return useCase.getPetanibyPaging(id).distinctUntilChanged()
    }
    
}