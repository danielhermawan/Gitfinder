package co.folto.gitfinder.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.folto.gitfinder.R
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.util.adapter.ViewType
import co.folto.gitfinder.util.adapter.ViewTypeDelegateAdapter
import co.folto.gitfinder.util.formatDate
import co.folto.gitfinder.util.inflate
import co.folto.gitfinder.util.loadNetworkImage
import co.folto.gitfinder.util.obtainDrawable
import com.bumptech.glide.request.RequestOptions
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
                imagePlaceholder.loadNetworkImage(context, repo.owner.avatarUrl,
                        options = RequestOptions().circleCrop())
                textName.text = repo.fullName
                textDescription.text = repo.description
                textStarCount.text = repo.stargazersCount.toString()
                textForkCount.text = repo.forks.toString()
                textWatchCount.text = repo.watchers.toString()
                textUpdated.text = repo.createdAt.formatDate("MM/yyyy")
                if(repo.privated)
                    imgPrivate.setImageDrawable(resources.obtainDrawable(R.drawable.ic_lock_outline_black_24dp, context))
                else
                    imgPrivate.setImageDrawable(resources.obtainDrawable(R.drawable.ic_lock_open_black_24dp, context))
                setOnClickListener { itemClick(repo) }
            }
    }
}