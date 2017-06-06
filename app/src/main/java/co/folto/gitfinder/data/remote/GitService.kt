package co.folto.gitfinder.data.remote

import co.folto.gitfinder.data.model.Repo
import co.folto.gitfinder.data.model.response.SearchRepoResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */

interface GitService {
    @GET("repositories")
    fun listRepos(): Flowable<List<Repo>>

    @GET("repos/{owner}/{repo}")
    fun getRepo(@Path("owner") owner: String, @Path("repo") repo: String): Flowable<Repo>

    @GET("search/repositories")
    fun searchRepo(@Query("q") search: String, @Query("sort") sort: String = "stars",
                   @Query("order") order: String = "desc", @Query("page") page: Int = 1): Flowable<SearchRepoResponse>
}
