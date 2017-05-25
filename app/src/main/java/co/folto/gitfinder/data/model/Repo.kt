package co.folto.gitfinder.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
data class Repo(
        val id: Long,
        val name: String,
        val owner: Owner,
        @SerializedName("full_name") val fullName: String,
        val description: String,
        val private: Boolean,
        val fork: Boolean,
        val url: String,
        @SerializedName("html_url") val htmlUrl: String
)