package co.folto.gitfinder.ui.repodetail

import co.folto.gitfinder.data.RepoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Daniel on 6/5/2017 for GitFInder project.
 */
class DetailPresenter @Inject constructor(
        val repoRepository: RepoRepository,
        val view: DetailContract.View,
        val repoId: String
): DetailContract.Presenter {

    private var composite = CompositeDisposable()

    init {
        view.attachPresenter(this)
    }

    override fun subscribe() = loadRepo()

    override fun unsubscribe() = composite.clear()

    override fun loadRepo() {
        view.setLoading(true)
        val(owner, name) = repoId.split('/')
        val request = repoRepository.getRepo(owner, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                    onNext = {
                        view.showRepo(it)
                    },
                    onError = {
                        view.showError("Unable to fetch repo from github")
                        view.setLoading(false)
                    },
                    onComplete = { view.setLoading(false) }
                )
        composite.add(request)
    }
}