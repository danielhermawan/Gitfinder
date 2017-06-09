package co.folto.gitfinder.ui.popular

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.folto.gitfinder.GitfinderApplication
import co.folto.gitfinder.R
import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.adapter.RepoAdapter
import co.folto.gitfinder.ui.repodetail.DetailActivity
import co.folto.gitfinder.util.adapter.DividerItemDecoration
import co.folto.gitfinder.util.adapter.EndlessRecyclerViewScrollListener
import co.folto.gitfinder.util.setDefaultColors
import co.folto.gitfinder.util.showSnack
import kotlinx.android.synthetic.main.fragment_trending.*
import javax.inject.Inject


/**
 * Created by Daniel on 6/6/2017 for GitFInder project.
 */
class PopularFragment: Fragment(), PopularContract.View {

    @Inject
    lateinit var repoRepository: RepoRepository
    lateinit private var presenter: PopularContract.Presenter
    private val repoAdapter = RepoAdapter( { activity.startActivity(DetailActivity.newIntent(activity, it.fullName)) } )

    companion object {
        fun newInstance() = PopularFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        GitfinderApplication.dataComponent.inject(this)
        PopularPresenter(repoRepository, this)
    }

    override fun attachPresenter(presenter: PopularContract.Presenter) {
        this.presenter = presenter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_popular, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe();
        val linearLayoutManager = LinearLayoutManager(activity)
        with(rvRepos) {
            setHasFixedSize(true)
            adapter = repoAdapter
            layoutManager = linearLayoutManager
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST))
            addOnScrollListener(EndlessRecyclerViewScrollListener(linearLayoutManager) {
                page, count, rv ->
                presenter.loadMoreRepos(page)
            })
        }
        with(swipeContainer) {
            setDefaultColors(activity)
            setOnRefreshListener {
                presenter.loadRepos()
            }
        }
    }

    override fun onDestroyView() {
        presenter.unsubscribe()
        super.onDestroyView()
    }

    override fun setLoading(active: Boolean) {
        swipeContainer.isRefreshing = active
    }

    override fun showRepos(repos: List<Repo>) {
        noRepos.visibility = android.view.View.GONE
        repoAdapter.refreshData(repos.toMutableList())
    }

    override fun showMoreRepo(repos: List<Repo>) {
        repoAdapter.addData(repos.toMutableList())
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