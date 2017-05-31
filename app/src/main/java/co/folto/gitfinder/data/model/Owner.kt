package co.folto.gitfinder.data.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
open class Owner(
        @PrimaryKey var id: Long = 0,
        var login: String = "",
        @SerializedName("avatar_url") var avatarUrl: String = "",
        @SerializedName("gravatar_id") var gravatarId: String = ""
) : RealmObject()