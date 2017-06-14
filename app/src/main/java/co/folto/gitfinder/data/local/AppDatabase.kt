package co.folto.gitfinder.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import co.folto.gitfinder.data.model.Owner
import co.folto.gitfinder.data.model.Repo

/**
 * Created by Daniel on 6/8/2017 for GitFInder project.
 */
@Database(entities = arrayOf(Repo::class, Owner::class), version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}