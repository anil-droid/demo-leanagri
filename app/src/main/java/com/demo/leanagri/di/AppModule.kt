package com.demo.leanagri.di

import android.app.Application
import com.demo.leanagri.BuildConfig
import com.demo.leanagri.data.db.DemoAppDatabase
import com.demo.leanagri.data.api.MoviesAPIService
import com.demo.leanagri.data.source.MoviesRemoteDataSource
import com.demo.leanagri.core.Constants
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {


    @Singleton
    @Provides
    fun provideMoviesAPIService(okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ) = provideService(okhttpClient, converterFactory, MoviesAPIService::class.java)

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: MoviesAPIService)
            = MoviesRemoteDataSource(newsAPIService)

    @Singleton
    @Provides
    fun provideDb(app: Application) = DemoAppDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideNewsDao(db: DemoAppDatabase) = db.moviesDao()


    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply { level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideCoroutineScopeIO() = CoroutineScope(Dispatchers.IO)

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    private fun createRetrofit(
            okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.API.ENDPOINT)
                .client(okhttpClient)
                .addConverterFactory(converterFactory)
                .build()
    }

    private fun <T> provideService(okhttpClient: OkHttpClient,
            converterFactory: GsonConverterFactory, clazz: Class<T>): T {
        return createRetrofit(okhttpClient, converterFactory).create(clazz)
    }
}
