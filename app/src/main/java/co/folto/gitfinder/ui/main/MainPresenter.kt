package co.folto.gitfinder.ui.main

import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.data.model.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */
class MainPresenter @Inject constructor(
        val repoRepository: RepoRepository,
        val view: MainContract.View
): MainContract.Presenter {

    private var composite = CompositeDisposable()

    override fun subscribe() = loadRepos()

    override fun unsubscribe() = composite.clear()

    override fun loadRepos() {
        view.setLoading(true)
        composite.clear()
        val request = repoRepository.getRepos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onNext = {
                    if(it.isEmpty())
                        view.showNoRepo()
                    else
                        view.showRepos(it)
                },
                onError = {
                    view.showError("Unable to fetch data from github")
                    view.setLoading(false)
                },
                onComplete = { view.setLoading(false) }
            )
        composite.add(request)
    }

    override fun clickRepo(repo: Repo) {
        view.goToDetailRepo(repo.id)
    }
}