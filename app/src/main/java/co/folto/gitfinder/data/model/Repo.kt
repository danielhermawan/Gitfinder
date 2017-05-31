package co.folto.gitfinder.data.model

import co.folto.gitfinder.util.adapter.AdapterConstant
import co.folto.gitfinder.util.adapter.ViewType
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
open class Repo(
        @PrimaryKey var id: Long = 0,
        var name: String = "",
        var owner: Owner? = null,
        @SerializedName("full_name") var fullName: String = "",
        var description: String = "",
        var private: Boolean = false,
        var fork: Boolean = false,
        var url: String = "",
        @SerializedName("html_url") var htmlUrl: String = ""
) : RealmObject(), ViewType {
    override fun getViewType(): Int = AdapterConstant.REPO
}