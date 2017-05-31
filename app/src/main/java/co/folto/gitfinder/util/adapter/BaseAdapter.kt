package co.folto.gitfinder.util.adapter

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by Daniel on 5/31/2017 for GitFInder project.
 */
abstract class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    protected var items: MutableList<ViewType> = ArrayList()
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    init {
        setupDelegate(delegateAdapters)
    }

    abstract fun setupDelegate(delegateAdapter: SparseArrayCompat<ViewTypeDelegateAdapter>)

    abstract fun addData(datas: MutableList<ViewType>)

    abstract fun refreshData(datas: MutableList<ViewType>)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = delegateAdapters.get(viewType).onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
            = delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, this.items[position])

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int
            = items.get(position).getViewType()


    protected fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}