package co.folto.gitfinder.ui.main

import co.folto.gitfinder.injection.FragmentScoped
import co.folto.gitfinder.injection.component.DataComponent
import dagger.Component

/**
 * Created by Daniel on 5/23/2017 for GitFInder project.
 */

@FragmentScoped
@Component(dependencies = arrayOf(DataComponent::class), modules = arrayOf(MainPresenterModule::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}