package co.folto.gitfinder.ui.favorite

import android.support.v4.util.SparseArrayCompat
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.adapter.RepoDelegateAdapter
import co.folto.gitfinder.util.adapter.AdapterConstant
import co.folto.gitfinder.util.adapter.BaseAdapter
import co.folto.gitfinder.util.adapter.ViewType
import co.folto.gitfinder.util.adapter.ViewTypeDelegateAdapter

/**
 * Created by Daniel on 8/18/2017 for GitFInder project.
 */
class FavoriteAdapter(itemClick: (Repo) -> Unit): BaseAdapter() {

    private val delegateAdapter = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        delegateAdapter.put(AdapterConstant.REPO, RepoDelegateAdapter(itemClick))
    }

    override fun getAdapter() = delegateAdapter

    override fun addData(datas: MutableList<ViewType>) {
        if(items.size > 0 ) {
            val initPosition = items.size - 1
            items.addAll(datas)
            notifyItemRangeChanged(initPosition, items.size + 1)
        }

    }

    override fun refreshData(datas: MutableList<ViewType>) {
        items = datas
        notifyDataSetChanged()
    }






}