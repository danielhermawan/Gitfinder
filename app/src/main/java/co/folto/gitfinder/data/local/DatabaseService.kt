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

    fun getRepo(): Flowable<List<Repo>> {
        return db.repoDao().getAll()
    }
}