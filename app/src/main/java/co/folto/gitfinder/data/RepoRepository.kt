package co.folto.gitfinder.data

import co.folto.gitfinder.data.contract.RepoContract
import co.folto.gitfinder.data.local.PreferenceHelper
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.data.remote.GitService
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */
@Singleton
class RepoRepository @Inject constructor(
        private val gitService: GitService,
        private val preferenceHelper: PreferenceHelper
): RepoContract{
    override fun getRepos(): Flowable<List<Repo>> {
        return gitService.listRepos()
    }
}