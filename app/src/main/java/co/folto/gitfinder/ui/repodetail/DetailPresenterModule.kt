package co.folto.gitfinder.ui.repodetail

import dagger.Module
import dagger.Provides

/**
 * Created by Daniel on 6/5/2017 for GitFInder project.
 */
@Module
class DetailPresenterModule(val view: DetailContract.View, val repoId: String) {
    @Provides
    fun provideView(): DetailContract.View = view

    @Provides
    fun provideRepoId() = repoId
}