package co.folto.gitfinder.ui.repodetail

import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.ui.base.BasePresenter
import co.folto.gitfinder.ui.base.BaseView

/**
 * Created by Daniel on 6/5/2017 for GitFInder project.
 */
interface DetailContract {
    interface Presenter: BasePresenter {
        fun loadRepo()
    }

    interface View: BaseView<Presenter> {
        fun showRepo(repo: Repo)
        fun showError(message: String)
        fun setLoading(active: Boolean)
    }
}