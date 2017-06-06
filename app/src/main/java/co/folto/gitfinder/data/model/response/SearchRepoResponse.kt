package co.folto.gitfinder.data.model.response

import co.folto.gitfinder.data.model.Repo

/**
 * Created by Daniel on 6/6/2017 for GitFInder project.
 */
data class SearchRepoResponse(
        val totalCount: Int,
        val incompleteResults: Boolean,
        val items: List<Repo>
)