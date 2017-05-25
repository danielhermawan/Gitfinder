package co.folto.gitfinder.ui.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.folto.gitfinder.R
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.util.inflate
import kotlinx.android.synthetic.main.item_repos.view.*

/**
 * Created by Daniel on 5/24/2017 for GitFInder project.
 */
class MainAdapter(val repos: List<Repo>, val itemClick: (Repo) -> Unit)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(parent.inflate(R.layout.item_repos), itemClick)

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
            = holder.bind(repos[position])

    override fun getItemCount(): Int = repos.size

    class ViewHolder(val view: View, val itemClick: (Repo) -> Unit): RecyclerView.ViewHolder(view) {
        fun bind(repo: Repo) =
            with(itemView) {
                textName.text = repo.name
                setOnClickListener { itemClick(repo) }
            }
    }
}