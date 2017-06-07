package co.folto.gitfinder.data.local

import co.folto.gitfinder.data.model.Repo
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Daniel on 5/31/2017 for GitFInder project.
 */
@Singleton
class RealmService @Inject constructor(
        private val realm: Realm
){
    fun closeRealm() = realm.close()

    fun saveRepos(repos: List<Repo>){

    }
}