package co.folto.gitfinder.injection.module

import android.app.Application
import android.content.Context
import co.folto.gitfinder.injection.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
@Module
class ApplicationModule(val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): android.app.Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application
}