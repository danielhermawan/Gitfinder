package co.folto.gitfinder.ui.favorite

import co.folto.gitfinder.injection.annotation.ScreenScoped
import co.folto.gitfinder.injection.component.DataComponent
import dagger.Component

/**
 * Created by Daniel on 8/18/2017 for GitFInder project.
 */
@ScreenScoped
@Component(dependencies = arrayOf(DataComponent::class), modules = arrayOf(FavoriteModule::class))
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)
}