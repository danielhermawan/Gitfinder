package co.folto.gitfinder.ui.repodetail

import co.folto.gitfinder.injection.FragmentScoped
import co.folto.gitfinder.injection.component.DataComponent
import dagger.Component

/**
 * Created by Daniel on 6/5/2017 for GitFInder project.
 */
@FragmentScoped
@Component(dependencies = arrayOf(DataComponent::class), modules = arrayOf(DetailPresenterModule::class))
interface DetailComponent {
    fun inject(detailActivity: DetailActivity)
}