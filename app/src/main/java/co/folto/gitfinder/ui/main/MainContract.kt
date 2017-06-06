package co.folto.gitfinder.ui.main

import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.base.BasePresenter
import co.folto.gitfinder.ui.base.BaseView

/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */
interface MainContract {
    interface View: BaseView<Presenter> {
        fun setLoading(active: Boolean)
        fun showRepos(repos: List<Repo>)
        fun showError(message: String)
        fun showNoRepo(isError: Boolean)
        fun goToDetailRepo(id: String)
        fun showMoreRepo(repos: List<Repo>)
    }

    interface Presenter: BasePresenter {
        fun loadRepos()
        fun loadMoreRepos(page: Int)
        fun clickRepo(repo: Repo)

    }
}