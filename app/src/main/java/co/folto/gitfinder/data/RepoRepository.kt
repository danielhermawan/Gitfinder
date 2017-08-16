package co.folto.gitfinder.data

import co.folto.gitfinder.data.contract.RepoContract
import co.folto.gitfinder.data.local.DatabaseService
import co.folto.gitfinder.data.local.PreferenceHelper
import co.folto.gitfinder.data.model.FavoriteRepo
import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.data.remote.GitService
import io.reactivex.Completable
import io.reactivex.Flowable
import org.joda.time.DateTime
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */
@Singleton
class RepoRepository @Inject constructor(
        private val gitService: GitService,
        private val databaseService: DatabaseService,
        private val preferenceHelper: PreferenceHelper
): RepoContract{

    override fun getRepos(): Flowable<List<Repo>> = gitService.listRepos()

    override fun getRepo(owner: String, repo: String): Flowable<Repo> =
        databaseService.getRepo(owner, repo)

    override fun getTrending(page: Int): Flowable<List<Repo>> {
        val dt = DateTime()
        val pushed = dt.minusDays(7).toString("yyyy-MM-dd")
        val created = dt.minusMonths(1).toString("yyyy-MM-dd")
        return gitService.searchRepo(search = "pushed:>$pushed created:>$created", page = page)
            .map { it.items }
            .doOnNext {
                databaseService.saveRepo(it)
            }
    }

    override fun getPopular(page: Int): Flowable<List<Repo>> {
        val dt = DateTime()
        val pushed = dt.minusMonths(1).toString("yyyy-MM-dd")
        return gitService.searchRepo(search = "pushed:>$pushed", page = page)
            .map { it.items }
            .doOnNext {
                databaseService.saveRepo(it)
            }
    }

    override fun addFavorite(favoriteRepo: FavoriteRepo): Completable =
        databaseService.addFavoriteRepo(favoriteRepo)

    override fun getFavorites(): Flowable<List<FavoriteRepo>> = databaseService.getFavoriteRepos()

    override fun removeFavorites(owner: String,repo: String): Completable
        = databaseService.revomeFavorite(owner, repo)

    override fun isFavorite(owner: String, repo: String): Flowable<Boolean>
        = databaseService.isFavorite(owner, repo)

    override fun clearFavorite(): Completable = databaseService.clearFavoriteRepo()
}