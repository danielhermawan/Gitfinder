package co.folto.gitfinder

import android.app.Application
import co.folto.gitfinder.injection.component.DaggerDataComponent
import co.folto.gitfinder.injection.component.DataComponent
import co.folto.gitfinder.injection.module.ApplicationModule
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import timber.log.Timber

/**
 * Created by Daniel on 5/19/2017 for GitFInder project.
 */
class GitfinderApplication: Application() {


    //todo: experiment with new android component like livedata, room, view model and optional data binding
    //todo: add leakcanary, pmd, bugfinder, analythic and other tools stuff
    //todo: integrated repository with realm for caching
    //todo: Make base fragment and stuff
    //todo: Fix and made it like reddit
    //todo: Glide and okhttp
    //todo: search, filter, fix detail, bottom navigation, search by language or topic
    companion object {
        @JvmStatic lateinit var dataComponent: DataComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                    .build());
        }
        dataComponent = DaggerDataComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }


}