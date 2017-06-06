package co.folto.gitfinder.ui.adapter

import android.support.v4.util.SparseArrayCompat
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.util.adapter.*

/**
 * Created by Daniel on 5/24/2017 for GitFInder project.
 */
class RepoAdapter(itemClick: (Repo) -> Unit)
    : BaseAdapter() {

    private val delegateAdapter = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = LoadingItem()

    init {
        delegateAdapter.put(AdapterConstant.LOADING, LoadingDelegateAdapter())
        delegateAdapter.put(AdapterConstant.REPO, RepoDelegateAdapter(itemClick))
    }

    override fun getAdapter() = delegateAdapter

    override fun addData(datas: MutableList<ViewType>) {
        if(items.size > 0){
            val initPosition = items.size - 1
            items.removeAt(initPosition)
            notifyItemRemoved(initPosition)
            items.addAll(datas)
            items.add(loadingItem)
            notifyItemRangeChanged(initPosition, items.size + 1)
        }
    }

    override fun refreshData(datas: MutableList<ViewType>) {
        items = datas
        items.add(loadingItem)
        notifyDataSetChanged()
    }
}