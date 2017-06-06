package co.folto.gitfinder.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import co.folto.gitfinder.R
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.repodetail.DetailActivity
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
        setHasOptionsMenu(true)
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

    override fun onStart() {
        super.onStart()
        presenter.subscribe();
    }

    override fun onStop() {
        super.onStop()
        presenter.unsubscribe()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.item_search -> {

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setLoading(active: Boolean) {
        swipeContainer.isRefreshing = active
    }

    override fun showRepos(repos: List<Repo>) {
        noRepos.visibility = View.GONE
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
        noRepos.visibility = View.VISIBLE
    }

    override fun goToDetailRepo(id: String)
            = activity.startActivity(DetailActivity.newIntent(activity, id))
}