package ir.nwise.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nwise.app.databinding.ItemPostBinding
import ir.nwise.app.domain.models.PostResponse
import ir.nwise.app.ui.base.BaseViewHolder
import ir.nwise.app.ui.utils.replaceAll

internal class UserPostAdapter(private val onItemClicked: (PostResponse) -> Unit) :
    RecyclerView.Adapter<BaseViewHolder<out PostResponse>>() {

    private val items = mutableListOf<PostResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out PostResponse> {
        val inflater = LayoutInflater.from(parent.context)
        return UserPostViewHolder(
            binding = ItemPostBinding.inflate(inflater, parent, false),
            onItemClicked = onItemClicked
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out PostResponse>, position: Int) {
        val model = items.getOrNull(position) ?: return
        when (holder) {
            is UserPostViewHolder -> holder.bind(model)
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitItems(newItems: List<PostResponse>) {
        items.replaceAll(newItems)
    }
}