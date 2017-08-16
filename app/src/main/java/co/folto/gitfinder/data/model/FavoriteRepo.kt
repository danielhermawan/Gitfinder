package co.folto.gitfinder.data.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Daniel on 8/16/2017 for GitFInder project.
 */
@Entity(tableName = "favorite_repos")
class FavoriteRepo {
    @PrimaryKey(autoGenerate = true) var autoId: Long = 0
    @Embedded var repo: Repo = Repo()
}