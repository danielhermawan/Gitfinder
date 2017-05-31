package co.folto.gitfinder

import android.app.Application
import co.folto.gitfinder.injection.component.DaggerDataComponent
import co.folto.gitfinder.injection.component.DataComponent
import co.folto.gitfinder.injection.module.ApplicationModule
import co.folto.gitfinder.injection.module.DataModule
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import timber.log.Timber

/**
 * Created by Daniel on 5/19/2017 for GitFInder project.
 */
class GitfinderApplication: Application() {

    companion object {
        @JvmStatic lateinit var dataComponent: DataComponent
    }

    //todo: experiment with new android component like livedata, room, view model and optional data binding
    //todo: add leakcanary, pmd, bugfinder, analythic and other tools stuff
    //todo: integrated repository with realm for caching
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
        Realm.init(this)
        dataComponent = DaggerDataComponent.builder()
                .applicationModule(ApplicationModule(this))
                .dataModule(DataModule("https://api.github.com/"))
                .build()
        dataComponent.inject(this)
    }


}