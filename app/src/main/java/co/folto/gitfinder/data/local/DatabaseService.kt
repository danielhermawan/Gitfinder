package co.folto.gitfinder.data.local

import co.folto.gitfinder.data.model.FavoriteRepo
import co.folto.gitfinder.data.model.Repo
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Daniel on 6/8/2017 for GitFInder project.
 */
@Singleton
class DatabaseService @Inject constructor(private val db: AppDatabase){

    fun saveRepo(repos: List<Repo>): Completable {
        db.repoDao().insertAll(*repos.toTypedArray())
        return Completable.complete()
    }

    fun saveRepo(repo: Repo): Completable {
        return Completable.create {
            db.repoDao().insertAll(repo)
            it.onComplete()
        }
    }

    fun getRepos(): Flowable<List<Repo>> = db.repoDao().getAll()

    fun getRepo(owner: String,repo: String): Flowable<Repo> = db.repoDao().getByName("$owner/$repo")

    fun addFavoriteRepo(favoriteRepo: FavoriteRepo): Completable
        = Completable.create {
            db.favoriteRepoDao().insert(favoriteRepo)
            it.onComplete()
        }

    fun getFavoriteRepos(): Flowable<List<FavoriteRepo>> = db.favoriteRepoDao().getAll()

    fun revomeFavorite(owner: String,repo: String): Completable
        = Completable.create {
            db.favoriteRepoDao().delete("$owner/$repo")
            it.onComplete()
        }

    fun getFavoriteRepo(owner: String,repo: String): Flowable<FavoriteRepo>
        = db.favoriteRepoDao().getDetail("$owner/$repo")

    fun isFavorite(owner: String,repo: String): Flowable<Boolean> {
        return db.favoriteRepoDao().getCount("$owner/$repo")
            .flatMap {
                var valid = false
                if(it != 0)
                    valid = true
                Flowable.just(valid)
            }
    }

    fun clearFavoriteRepo(): Completable
        = Completable.create {
            db.favoriteRepoDao().clear()
            it.onComplete()
        }
}