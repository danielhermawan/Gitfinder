package co.folto.gitfinder.ui.main

import android.support.v4.util.SparseArrayCompat
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.adapter.LoadingDelegateAdapter
import co.folto.gitfinder.ui.adapter.RepoDelegateAdapter
import co.folto.gitfinder.util.adapter.*

/**
 * Created by Daniel on 5/24/2017 for GitFInder project.
 */
class MainAdapter(val itemClick: (Repo) -> Unit)
    : BaseAdapter() {

    private val loadingItem = LoadingItem()

    override fun setupDelegate(delegateAdapter: SparseArrayCompat<ViewTypeDelegateAdapter>) {
        delegateAdapter.put(AdapterConstant.LOADING, LoadingDelegateAdapter())
        delegateAdapter.put(AdapterConstant.REPO, RepoDelegateAdapter(itemClick))
    }

    override fun addData(datas: MutableList<ViewType>) {
        if(items.size > 0 ){
            val initPosition = items.size - 1
            items.removeAt(initPosition)
            notifyItemRangeChanged(initPosition, items.size + 1)
        }
        items.addAll(datas)
        items.add(loadingItem)
        notifyDataSetChanged()
    }

    override fun refreshData(datas: MutableList<ViewType>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())
        items.addAll(datas)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }
}