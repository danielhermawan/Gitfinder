package co.folto.gitfinder.injection.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import co.folto.gitfinder.BuildConfig
import co.folto.gitfinder.data.remote.GitService
import co.folto.gitfinder.injection.ApplicationContext
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.realm.Realm
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton


/**
 * Created by Daniel on 5/22/2017 for GitFInder project.
 */
@Module
class DataModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun provideOkhttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @Singleton
    fun provideSharedPreference(@ApplicationContext context: Context):SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        })
        logging.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return OkHttpClient()
                .newBuilder()
                .addInterceptor(logging)
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideGitService(retrofit: Retrofit): GitService = retrofit.create(GitService::class.java)

    @Provides
    @Singleton
    fun provideRealm(): Realm = Realm.getDefaultInstance()

}