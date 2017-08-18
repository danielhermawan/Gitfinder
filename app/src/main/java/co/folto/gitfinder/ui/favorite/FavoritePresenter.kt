package co.folto.gitfinder.ui.favorite

import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.util.start
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by Daniel on 8/18/2017 for GitFInder project.
 */
class FavoritePresenter @Inject constructor(
    private val repoRepository: RepoRepository,
    private val view: FavoriteContract.View
): FavoriteContract.Presenter {

    private var composite = CompositeDisposable()

    override fun subscribe() = loadFavoites()

    override fun unsubscribe() = composite.dispose()

    override fun loadFavoites() {
        view.showLoading(true)
        composite.clear()
        val request = repoRepository.getFavorites()
            .switchMap {
                val repos = ArrayList<Repo>()
                it.forEach {
                    repos.add(it.repo)
                }
                Flowable.just(repos.toList())
            }
            .start()
            .subscribe(
                {
                    if(it.isEmpty())
                        view.showNoRepo(false)
                    else
                        view.showRepos(it)
                    view.showLoading(false)
                },
                {
                    view.showError("Unable to fetch data from github")
                    view.showNoRepo(true)
                    view.showLoading(false)
                }
            )
        composite.add(request)
    }
}