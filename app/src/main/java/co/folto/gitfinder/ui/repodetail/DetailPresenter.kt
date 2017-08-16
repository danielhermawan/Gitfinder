package co.folto.gitfinder.ui.repodetail

import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.data.model.FavoriteRepo
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.util.start
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Flowables
import io.reactivex.rxkotlin.subscribeBy

/**
 * Created by Daniel on 6/5/2017 for GitFInder project.
 */
class DetailPresenter (
        val repoRepository: RepoRepository,
        val view: DetailContract.View,
        val repoId: String
): DetailContract.Presenter {

    private var composite = CompositeDisposable()

    private var repo: Repo? = null
    private var isFavorite: Boolean = false

    init {
        view.attachPresenter(this)
    }

    override fun subscribe() = loadRepo()

    override fun unsubscribe() = composite.clear()

    override fun loadRepo() {
        view.setLoading(true)
        val(owner, name) = repoId.split('/')
        val isFavorite = repoRepository.isFavorite(owner, name)
        val repoRequest = repoRepository.getRepo(owner, name)
        val request= Flowables.zip(isFavorite, repoRequest, {
            favorite, repo -> Pair(favorite, repo)
        })
            .start()
            .subscribe(
                {
                    this.isFavorite = it.first
                    this.repo = it.second
                    view.showRepo(it.second)
                    view.activeFavorite(it.first)
                    view.setLoading(false)
                },
                {
                    view.showError("Unable to fetch repo from github")
                    view.setLoading(false)
                }
            )
        composite.add(request)
    }

    override fun toogleFavorite() {
        repo?.let {
            if(isFavorite)
                removeFavorite(it)
            else
                addFavorite(it)
        }
    }

    fun addFavorite(repo: Repo) {
        val favorite = FavoriteRepo()
        favorite.repo = repo
        val request = repoRepository.addFavorite(favorite)
            .start()
            .subscribeBy (
                onComplete = {
                    view.showMessage("Add this to favorite")
                    view.activeFavorite(true)
                    view.setLoading(false)
                    isFavorite = true
                },
                onError = {
                    view.showError("Unable to save repo to favorite")
                    view.setLoading(false)
                }
            )
        composite.add(request)
    }

    fun removeFavorite(repo: Repo) {
        val favorite = FavoriteRepo()
        favorite.repo = repo
        view.setLoading(true)
        val(owner, name) = repoId.split('/')
        val request = repoRepository.removeFavorites(owner, name)
            .start()
            .subscribeBy (
                onComplete = {
                    view.showMessage("Remove this from favorite")
                    view.activeFavorite(false)
                    view.setLoading(false)
                    isFavorite = false
                },
                onError = {
                    view.showError("Unable to remove repo from favorite")
                    view.setLoading(false)
                }
            )
        composite.add(request)
    }

    override fun openChromeTab() {
        repo?.let {
            view.openChromeTab(it)
        }
    }
}