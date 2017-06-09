package co.folto.gitfinder.injection.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import co.folto.gitfinder.data.local.AppDatabase
import co.folto.gitfinder.data.local.DatabaseService
import co.folto.gitfinder.injection.annotation.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context):SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application)
            = Room.databaseBuilder(app, AppDatabase::class.java, "github.db").build()

    @Provides
    @Singleton
    fun provideDatabaseService(db: AppDatabase) = DatabaseService(db)
}