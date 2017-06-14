package co.folto.gitfinder.ui.repodetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import co.folto.gitfinder.GitfinderApplication
import co.folto.gitfinder.R
import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.util.openChromeTabs
import co.folto.gitfinder.util.showSnack
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

/**
 * Created by Daniel on 6/5/2017 for GitFInder project.
 */
class DetailFragment: Fragment(), DetailContract.View {

    @Inject
    lateinit var repoRepository: RepoRepository
    lateinit private var presenter: DetailContract.Presenter
    private var repo: Repo? = null

    companion object {
        private val DETAIL_REPO_ID = "DETAIL_REPO_ID"
        fun newInstance(repoId: String): DetailFragment {
            val arguments = Bundle()
            arguments.putString(DETAIL_REPO_ID, repoId)
            val detailFragment = DetailFragment()
            detailFragment.arguments = arguments
            return detailFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
        GitfinderApplication.dataComponent.inject(this)
        DetailPresenter(repoRepository, this, arguments.getString(DETAIL_REPO_ID))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        inflater.inflate(R.menu.menu_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_open_browse -> {
                repo?.let {
                    activity.openChromeTabs(it.htmlUrl)
                }
            }
            R.id.item_add_favorite -> {
                repo?.let {
                    presenter.addFavorite(it)
                }
            }
        }
        return true
    }

    override fun attachPresenter(presenter: DetailContract.Presenter) {
        this.presenter = presenter
    }

    override fun setLoading(active: Boolean) {
        if(active)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.GONE
    }

    override fun showRepo(repo: Repo) {
        activity.title = repo.fullName
        textFullname.text = repo.fullName
        this.repo = repo
    }

    override fun showMessage(message: String) {
        view?.showSnack(message)
    }

    override fun showError(message: String) {
        view?.showSnack(message)
        viewError.visibility = View.VISIBLE
        textError.text = getString(R.string.detail_error_message)
    }
}