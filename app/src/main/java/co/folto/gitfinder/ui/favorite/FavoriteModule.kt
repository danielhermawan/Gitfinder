package co.folto.gitfinder.ui.favorite

import dagger.Module
import dagger.Provides

/**
 * Created by Daniel on 8/18/2017 for GitFInder project.
 */
@Module
class FavoriteModule(val view: FavoriteContract.View) {

    @Provides
    fun provideViews(): FavoriteContract.View = view
}