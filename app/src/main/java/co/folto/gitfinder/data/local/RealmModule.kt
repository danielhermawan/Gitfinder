package co.folto.gitfinder.data.local

import co.folto.gitfinder.data.model.Owner
import co.folto.gitfinder.data.model.Repo
import io.realm.annotations.RealmModule

/**
 * Created by Daniel on 6/7/2017 for GitFInder project.
 */
@RealmModule(classes = arrayOf(Owner::class, Repo::class))
class RealmModule {
}