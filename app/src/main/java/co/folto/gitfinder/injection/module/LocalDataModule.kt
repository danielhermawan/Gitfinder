package co.folto.gitfinder.injection.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import co.folto.gitfinder.BuildConfig
import co.folto.gitfinder.data.local.DatabaseService
import co.folto.gitfinder.data.local.RealmModule
import co.folto.gitfinder.injection.annotation.ApplicationContext
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import javax.inject.Singleton


/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
@Module
class LocalDataModule {

    private val DATABASE_VERSION = 1

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context):SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    internal fun provideRealmConfig(@ApplicationContext context: Context): RealmConfiguration {
        Realm.init(context)
        val config = RealmConfiguration.Builder()
                .modules(RealmModule())
                .schemaVersion(DATABASE_VERSION.toLong())
                /*.migration(RmMigration())*/
        if(BuildConfig.DEBUG)
            config.deleteRealmIfMigrationNeeded()
        return config.build()
    }

    @Provides
    @Singleton
    internal fun provideRealm(config: RealmConfiguration): Realm {
        Realm.setDefaultConfiguration(config)
        try {
            return Realm.getDefaultInstance()
        } catch (e: Exception) {
            Timber.e(e)
            Realm.deleteRealm(config)
            Realm.setDefaultConfiguration(config)
            return Realm.getDefaultInstance()
        }
    }

    @Provides
    @Singleton
    fun provideDatabaseService(realm: Realm): DatabaseService
            = DatabaseService(realm)
}