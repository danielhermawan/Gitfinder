package co.folto.gitfinder.data.local

import co.folto.gitfinder.data.model.Repo
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Daniel on 6/8/2017 for GitFInder project.
 */
@Singleton
class DatabaseService @Inject constructor(private val realm: Realm){

    fun saveRepo(repos: List<Repo>) {
        realm.executeTransaction {
            it.copyToRealm(repos[0])
        }
    }
}