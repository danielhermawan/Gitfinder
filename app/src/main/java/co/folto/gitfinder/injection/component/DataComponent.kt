package co.folto.gitfinder.injection.component

import android.app.Application
import android.content.Context
import co.folto.gitfinder.GitfinderApplication
import co.folto.gitfinder.data.RepoRepository
import co.folto.gitfinder.data.local.PreferenceHelper
import co.folto.gitfinder.data.remote.GitService
import co.folto.gitfinder.injection.ApplicationContext
import co.folto.gitfinder.injection.module.ApplicationModule
import co.folto.gitfinder.injection.module.DataModule
import co.folto.gitfinder.ui.main.MainFragment
import dagger.Component
import io.realm.Realm
import javax.inject.Singleton

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class, DataModule::class))
interface DataComponent {
    fun inject(application: GitfinderApplication)
    fun inject(fragment: MainFragment)

    fun application(): Application
    @ApplicationContext fun context(): Context
    fun gitService(): GitService
    fun preferenceHelper(): PreferenceHelper
    fun repoRepository(): RepoRepository
    fun Realm(): Realm
}