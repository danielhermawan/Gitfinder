package co.folto.gitfinder.data.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import co.folto.gitfinder.util.adapter.AdapterConstant
import co.folto.gitfinder.util.adapter.ViewType
import com.google.gson.annotations.SerializedName

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
@Entity
class Repo: ViewType{
    var description: String = ""
    var url: String = ""
    @PrimaryKey var id: Long = 0
    var name: String = ""
    var fullName: String = ""
    @Embedded(prefix = "owner_") var owner: Owner = Owner()
    @SerializedName("private")  @ColumnInfo(name = "private") var privated: Boolean = false
    var fork: Boolean = false
    var htmlUrl: String = ""
    var createdAt: String = ""
    var updatedAt: String = ""
    var homepage: String? = ""
    var size: Int = 0
    var stargazersCount: Int = 0
    var watchers: Int = 0
    var forks: Int = 0
    var openIssues: Int = 0
    var language: String? = ""
    var favorite: Int? = 0

    override fun getViewType(): Int = AdapterConstant.REPO
}