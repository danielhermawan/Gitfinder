package co.folto.gitfinder.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
data class Owner(
        var id: Long = 0,
        var login: String = "",
        @SerializedName("avatar_url") var avatarUrl: String = "",
        @SerializedName("gravatar_id") var gravatarId: String = ""
)