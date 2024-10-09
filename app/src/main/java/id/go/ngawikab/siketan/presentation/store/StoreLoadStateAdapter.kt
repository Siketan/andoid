package id.go.ngawikab.siketan.presentation.store
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import id.go.ngawikab.siketan.databinding.LoadStateFooterBinding

class StoreLoadStateAdapter(private val retry: () -> Unit)
    :LoadStateAdapter<StoreLoadStateAdapter.LoadStateAdapter>() {

    override fun onBindViewHolder(holder: StoreLoadStateAdapter.LoadStateAdapter, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): StoreLoadStateAdapter.LoadStateAdapter {
        val binding = LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateAdapter(binding)
    }

    inner class LoadStateAdapter(private val binding: LoadStateFooterBinding): RecyclerView.ViewHolder(binding.root){
        init {
            binding.btnRetry.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState){
            with(binding){
                progressBar.isVisible = loadState is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
                tvError.isVisible = loadState !is LoadState.Loading
            }
        }
    }
}