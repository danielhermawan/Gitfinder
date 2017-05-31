package co.folto.gitfinder.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.folto.gitfinder.R
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.util.adapter.ViewType
import co.folto.gitfinder.util.adapter.ViewTypeDelegateAdapter
import co.folto.gitfinder.util.inflate
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repos.view.*

/**
 * Created by Daniel on 5/30/2017 for GitFInder project.
 */
class RepoDelegateAdapter(val itemClick: (Repo) -> Unit): ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
        = RepoViewHolder(parent.inflate(R.layout.item_repos), parent.context, itemClick)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as RepoViewHolder
        holder.bind(item as Repo)
    }

    class RepoViewHolder(val view: View, val context: Context, val itemClick: (Repo) -> Unit): RecyclerView.ViewHolder(view) {
        fun bind(repo: Repo) =
            with(itemView) {
                val imgUrl = "http://placehold.it/" +
                        "${R.dimen.main_placeholder_image}x${R.dimen.main_placeholder_image}/" +
                        "FF4081/ffffff/" +
                        "&text=${repo.name}"
                textName.text = repo.fullName
                Glide.with(context)
                    .load(imgUrl)
                    .into(imagePlaceholder)
                setOnClickListener { itemClick(repo) }
            }
    }
}