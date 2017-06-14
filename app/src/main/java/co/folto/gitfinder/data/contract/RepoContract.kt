package co.folto.gitfinder.data.contract

import co.folto.gitfinder.data.model.Repo
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */
interface RepoContract {

    fun getRepos(): Flowable<List<Repo>>
    fun getRepo(owner: String, repo: String): Flowable<Repo>
    fun getTrending(page: Int = 1): Flowable<List<Repo>>
    fun getPopular(page: Int = 1): Flowable<List<Repo>>
    fun addFavorite(repo: Repo): Completable
    fun getFavorites(): Flowable<List<Repo>>
}