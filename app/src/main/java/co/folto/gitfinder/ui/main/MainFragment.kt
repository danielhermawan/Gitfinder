package co.folto.gitfinder.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.folto.gitfinder.R
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.util.DividerItemDecoration
import co.folto.gitfinder.util.EndlessRecyclerViewScrollListener
import co.folto.gitfinder.util.setDefaultColors
import co.folto.gitfinder.util.showSnack
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by Daniel on 5/26/2017 for GitFInder project.
 */
class MainFragment: Fragment(), MainContract.View{

    lateinit private var presenter: MainContract.Presenter

    private val repoAdapter = MainAdapter( { presenter.clickRepo(it) } )

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(activity)
        with(rvRepos) {
            setHasFixedSize(true)
            adapter = repoAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST))
            addOnScrollListener(EndlessRecyclerViewScrollListener(linearLayoutManager) {
                page, count, rv -> presenter.loadMoreRepos(page)
            })
        }
        with(swipeContainer) {
            setDefaultColors(activity)
            setOnRefreshListener {
                presenter.loadRepos()
            }
        }

    }

    override fun attachPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe();
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun setLoading(active: Boolean) {
        swipeContainer.isRefreshing = active
    }

    override fun showRepos(repos: List<Repo>) {
        repoAdapter.refreshData(repos.toMutableList())
    }

    override fun showMoreRepo(repos: List<Repo>) {
        repoAdapter.addData(repos.toMutableList())
    }


    override fun showError(message: String) {
        view?.showSnack(message)
    }

    override fun showNoRepo(isError: Boolean) {
        if(isError) noTasks.text = resources.getString(R.string.main_no_repos_error)
        noRepos.visibility = View.VISIBLE
    }

    override fun goToDetailRepo(id: Long) {

    }
}