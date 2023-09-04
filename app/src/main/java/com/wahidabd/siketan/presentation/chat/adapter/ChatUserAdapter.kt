package go.ngawikab.siketan.presentation.chat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.wahidabd.library.presentation.adapter.BaseAsyncRecyclerAdapter
import com.wahidabd.library.presentation.adapter.viewholder.BaseAsyncItemViewHolder
import com.wahidabd.siketan.data.chat.model.response.ChatPetaniDataResponse
import com.wahidabd.siketan.databinding.ItemChatUserBinding


/**
 * Created by Wahid on 8/20/2023.
 * Github github.com/wahidabd.
 */


class ChatUserAdapter(
    private val context: Context,
    items: ArrayList<ChatPetaniDataResponse> = arrayListOf(),
    private val onItemClick: (id: Int) -> Unit
) : BaseAsyncRecyclerAdapter<ChatPetaniDataResponse, ChatUserAdapter.ChatUserViewHolder>(items){
    override fun getViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemChatUserBinding.inflate(LayoutInflater.from(context), parent, false)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatUserAdapter.ChatUserViewHolder {
        return ChatUserViewHolder(getViewBinding(parent, viewType))
    }

    inner class ChatUserViewHolder(binding: ViewBinding): BaseAsyncItemViewHolder<ChatPetaniDataResponse>(binding){
        override fun bind(data: ChatPetaniDataResponse) = with(binding as ItemChatUserBinding){

        }
    }
}