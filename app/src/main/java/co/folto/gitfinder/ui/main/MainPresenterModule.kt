package co.folto.gitfinder.ui.main

import dagger.Module
import dagger.Provides

/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */
@Module
class MainPresenterModule(val view: MainContract.View) {

    @Provides
    fun provideView(): MainContract.View = view

}