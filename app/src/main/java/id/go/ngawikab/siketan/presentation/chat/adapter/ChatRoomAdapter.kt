package id.go.ngawikab.siketan.presentation.chat.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.exts.setImageUrl
import id.go.ngawikab.siketan.data.chat.model.response.ChatMessageResponse
import id.go.ngawikab.siketan.databinding.ItemChatLeftBinding
import id.go.ngawikab.siketan.databinding.ItemChatRightBinding
import id.go.ngawikab.siketan.utils.convertTimestamp


/**
 * Created by Wahid on 8/26/2023.
 * Github github.com/wahidabd.
 */


class ChatRoomAdapter(
    private val id: Int?,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val LEFT = 0
        private const val RIGHT = 1
    }


    private val differCallback = object : DiffUtil.ItemCallback<ChatMessageResponse>() {
        override fun areItemsTheSame(
            oldItem: ChatMessageResponse,
            newItem: ChatMessageResponse
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: ChatMessageResponse,
            newItem: ChatMessageResponse
        ): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this, differCallback)
    private var list = ArrayList<ChatMessageResponse>()

    private var listener: ((String) -> Unit)? = null
    fun setOnItemClickListener(listener: (String) -> Unit) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LEFT) LeftViewHolder(
            ItemChatLeftBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ), context
        )
        else RightViewHolder(
            ItemChatRightBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            ), context
        )
    }

    override fun getItemCount(): Int = list.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (holder) {
            is LeftViewHolder -> holder.bind(item, listener)
            is RightViewHolder -> holder.bind(item, listener)
        }
    }

    class RightViewHolder(private val binding: ItemChatRightBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: ChatMessageResponse, listener: ((String) -> Unit)? = null) = with(binding) {
            tvMessage.text = data.pesan
            tvTime.text = data.waktu?.convertTimestamp()

            img.visibility = if (data.attachment == null) View.GONE else View.VISIBLE
            img.setImageUrl(context, data.attachment.toString())

            img.onClick {
                listener?.invoke(data.attachment.toString())
            }
        }
    }

    class LeftViewHolder(private val binding: ItemChatLeftBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(data: ChatMessageResponse, listener: ((String) -> Unit)? = null) = with(binding) {
            tvMessage.text = data.pesan
            tvTime.text = data.waktu?.convertTimestamp()

            img.visibility = if (data.attachment == null) View.GONE else View.VISIBLE
            img.setImageUrl(context, data.attachment.toString())
            img.onClick {
                listener?.invoke(data.attachment.toString())
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].fromId == id) RIGHT else LEFT
    }

    fun addPrevMessages(messages: List<ChatMessageResponse>) {
        list.addAll(messages.asReversed())
        notifyDataSetChanged()
    }

    fun addNewMessage(message: ChatMessageResponse) {
        list.add(0, message)
        notifyDataSetChanged()
    }
}