package co.folto.gitfinder.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
data class Owner (
        val id: Long,
        val login: String,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("gravatar_id") val gravatarId: String
)