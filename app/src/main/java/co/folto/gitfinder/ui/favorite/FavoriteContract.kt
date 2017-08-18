package co.folto.gitfinder.ui.favorite

import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.base.BasePresenter

/**
 * Created by Daniel on 8/18/2017 for GitFInder project.
 */
interface FavoriteContract {
    interface Presenter: BasePresenter {
        fun loadFavoites()
    }
    interface View {
        fun showLoading(active: Boolean)
        fun showRepos(repos: List<Repo>)
        fun showError(message: String)
        fun showNoRepo(isError: Boolean)
    }
}