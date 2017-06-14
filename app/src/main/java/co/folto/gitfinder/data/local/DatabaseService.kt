package co.folto.gitfinder.data.local

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

    fun getRepos(): Flowable<List<Repo>> {
        return db.repoDao().getAll()
    }

    fun getRepo(owner: String,repo: String): Flowable<Repo> {
        return db.repoDao().getByName("$owner/$repo")
    }

    fun getFavorite(): Flowable<List<Repo>> {
        return db.repoDao().getFavorite()
    }
}