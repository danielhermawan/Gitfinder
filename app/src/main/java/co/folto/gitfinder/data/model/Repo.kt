package co.folto.gitfinder.data.model

import co.folto.gitfinder.util.adapter.AdapterConstant
import co.folto.gitfinder.util.adapter.ViewType
import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
data class Repo(
        var id: Long = 0,
        var name: String = "",
        var owner: Owner = Owner(),
        @SerializedName("full_name") var fullName: String = "",
        var description: String = "",
        var private: Boolean = false,
        var fork: Boolean = false,
        var url: String = "",
        @SerializedName("html_url") var htmlUrl: String = ""
) : ViewType {
    override fun getViewType(): Int = AdapterConstant.REPO
}