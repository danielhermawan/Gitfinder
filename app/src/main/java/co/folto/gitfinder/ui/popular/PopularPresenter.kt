package co.folto.gitfinder.ui.popular

import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.data.model.Repo
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


/**
 * Created by Daniel on 6/6/2017 for GitFInder project.
 */
class PopularPresenter(
        val repoRepository: RepoRepository,
        val view: PopularContract.View
) : PopularContract.Presenter {

    private var composite = CompositeDisposable()

    init {
        view.attachPresenter(this)
    }

    override fun subscribe() = loadRepos()

    override fun unsubscribe() = composite.dispose()

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
            = repoRepository.getPopular(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
}