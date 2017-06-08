package co.folto.gitfinder.injection.component

import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.injection.module.ApplicationModule
import co.folto.gitfinder.injection.module.LocalDataModule
import co.folto.gitfinder.injection.module.RemoteDataModule
import co.folto.gitfinder.ui.popular.PopularFragment
import co.folto.gitfinder.ui.repodetail.DetailFragment
import co.folto.gitfinder.ui.trending.TrendingFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, LocalDataModule::class, RemoteDataModule::class))
interface DataComponent {
    fun inject(fragment: TrendingFragment)
    fun inject(fragment: DetailFragment)
    fun inject(fragment: PopularFragment)

    fun repoRepository(): RepoRepository
}