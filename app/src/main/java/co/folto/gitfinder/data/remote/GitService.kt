package co.folto.gitfinder.data.remote

import co.folto.gitfinder.data.model.Repo
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */

interface GitService {
    @GET("repositories")
    fun listRepos(): Flowable<List<Repo>>
}
