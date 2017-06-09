package co.folto.gitfinder.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
@Entity
class Owner {
    var login: String = ""
    var type: String = ""
    @PrimaryKey var id: Long = 0
    var avatarUrl: String = ""
    var gravatarId: String = ""
}