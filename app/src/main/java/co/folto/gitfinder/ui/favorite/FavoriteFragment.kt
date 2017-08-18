package co.folto.gitfinder.ui.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.folto.gitfinder.GitfinderApplication
import co.folto.gitfinder.R
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.repodetail.DetailActivity
import co.folto.gitfinder.util.adapter.DividerItemDecoration
import co.folto.gitfinder.util.setDefaultColors
import co.folto.gitfinder.util.showSnack
import kotlinx.android.synthetic.main.fragment_trending.*
import javax.inject.Inject

/**
 * Created by Daniel on 8/18/2017 for GitFInder project.
 */
class FavoriteFragment: Fragment(), FavoriteContract.View  {

    @Inject
    lateinit var presenter: FavoritePresenter
    private val repoAdapter = FavoriteAdapter({
        activity.startActivity(DetailActivity.newIntent(activity, it.fullName))
    })

    companion object {
        fun newInstance() = FavoriteFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        DaggerFavoriteComponent.builder()
                .dataComponent(GitfinderApplication.dataComponent)
                .favoriteModule(FavoriteModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
        = inflater?.inflate(R.layout.fragment_favorite, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe()
        val linearLayoutManager = LinearLayoutManager(activity)
        with(rvRepos) {
            setHasFixedSize(true)
            adapter = repoAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST))
        }
        with(swipeContainer) {
            setDefaultColors(activity)
            setOnRefreshListener {
                presenter.loadFavoites()
            }
        }
    }

    override fun onDestroyView() {
        presenter.unsubscribe()
        super.onDestroyView()
    }

    override fun showLoading(active: Boolean) {
        swipeContainer.isRefreshing = active
    }

    override fun showRepos(repos: List<Repo>) {
        noRepos.visibility = android.view.View.GONE
        repoAdapter.refreshData(repos.toMutableList())
    }

    override fun showError(message: String) {
        view?.showSnack(message)
    }

    override fun showNoRepo(isError: Boolean) {
        if(isError)
            noTasks.text = resources.getString(R.string.main_no_repos_error)
        else
            noTasks.text = resources.getString(R.string.main_no_repos_found)
        noRepos.visibility = android.view.View.VISIBLE
    }
}