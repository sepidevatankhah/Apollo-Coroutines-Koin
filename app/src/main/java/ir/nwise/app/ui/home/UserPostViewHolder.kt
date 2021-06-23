package ir.nwise.app.ui.home

import ir.nwise.app.databinding.ItemPostBinding
import ir.nwise.app.domain.models.PostResponse
import ir.nwise.app.ui.base.BaseViewHolder

class UserPostViewHolder(
    val binding: ItemPostBinding,
    private val onItemClicked: (PostResponse) -> Unit
) : BaseViewHolder<PostResponse>(binding) {

    override fun bind(model: PostResponse) {
        with(model)
        {
            binding.postTitle.text = title
            body?.apply {
                binding.postBody.text = if (this.length > 120) {
                    subSequence(0, 119).toString() + " ..."
                } else this
            }
            binding.root.setOnClickListener { onItemClicked.invoke(model) }
        }
    }
}