package co.folto.gitfinder.ui.trending

import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.data.model.Repo
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */
class TrendingPresenter(
        val repoRepository: RepoRepository,
        val view: TrendingContract.View
): TrendingContract.Presenter {

    init {
        view.attachPresenter(this)
    }

    private var composite = CompositeDisposable()

    override fun subscribe() = loadRepos()

    override fun unsubscribe() {
        composite.clear()
    }

    override fun loadRepos() {
        view.setLoading(true)
        composite.clear()
        val request = getRepo(1)
            .subscribeBy (
                onNext = {
                    if(it.isEmpty())
                        view.showNoRepo(false)
                    else
                        view.showRepos(it)
                },
                onError = {
                    Timber.e(it)
                    view.showError("Unable to fetch data from github")
                    view.showNoRepo(true)
                    view.setLoading(false)
                },
                onComplete = { view.setLoading(false) }
            )
        composite.add(request)
    }

    override fun loadMoreRepos(page: Int) {
        val request = getRepo(page)
            .subscribeBy (
                onNext = {
                    view.showMoreRepo(it)
                },
                onError = {
                    view.showError("Unable to fetch more data from github")
                },
                onComplete = {  }
            )
        composite.add(request)
    }

    fun getRepo(page: Int): Flowable<List<Repo>>
            = repoRepository.getTrending(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

}