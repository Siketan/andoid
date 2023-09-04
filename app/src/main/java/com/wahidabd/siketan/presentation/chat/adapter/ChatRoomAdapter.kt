package com.wahidabd.siketan.presentation.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wahidabd.siketan.databinding.ItemChatLeftBinding
import com.wahidabd.siketan.databinding.ItemChatRightBinding
import com.wahidabd.siketan.data.chat.model.response.ChatMessageResponse


/**
 * Created by Wahid on 8/26/2023.
 * Github github.com/wahidabd.
 */


class ChatRoomAdapter(private val id: Int?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
//        get() = listDiffer.currentList
//        set(value) = listDiffer.submitList(value.asReversed())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LEFT) LeftViewHolder(
            ItemChatLeftBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
        else RightViewHolder(
            ItemChatRightBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        when (holder) {
            is LeftViewHolder -> holder.bind(item)
            is RightViewHolder -> holder.bind(item)
        }
    }

    class RightViewHolder(private val binding: ItemChatRightBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatMessageResponse) = with(binding) {
            tvMessage.text = data.pesan
        }
    }

    class LeftViewHolder(private val binding: ItemChatLeftBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ChatMessageResponse) = with(binding) {
            tvMessage.text = data.pesan
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