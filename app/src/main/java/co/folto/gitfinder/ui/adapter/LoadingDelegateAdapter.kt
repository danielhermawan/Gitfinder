package co.folto.gitfinder.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import co.folto.gitfinder.R
import co.folto.gitfinder.util.adapter.ViewType
import co.folto.gitfinder.util.adapter.ViewTypeDelegateAdapter
import co.folto.gitfinder.util.inflate

/**
 * Created by Daniel on 5/30/2017 for GitFInder project.
 */
class LoadingDelegateAdapter: ViewTypeDelegateAdapter {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
            = LoadingViewHolder(parent.inflate(R.layout.item_progress))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {}

    class LoadingViewHolder(val view: View) : RecyclerView.ViewHolder(view)

}

