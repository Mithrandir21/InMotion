package com.example.testapp.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.testapp.App
import com.example.testapp.api.LuasForecastingApi
import com.example.testapp.api.RetrofitProvider
import com.example.testapp.db.Database
import com.example.testapp.db.ForecastDao
import com.example.testapp.repo.ForecastRepo
import com.example.testapp.repo.ForecastRepoImpl
import com.example.testapp.ui.MainActivity
import com.example.testapp.ui.MainViewModel
import com.example.testapp.utils.time.TimeBeforeNoon
import com.example.testapp.utils.time.TimeBeforeNoonImpl
import com.example.testapp.viewmodel.AppLifecycleOwner
import com.example.testapp.viewmodel.AppLifecycleOwnerImpl
import com.example.testapp.viewmodel.ViewModelFactory
import com.example.testapp.viewmodel.ViewModelMapKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module(includes = [AndroidSupportInjectionModule::class, AppDependencyBinder::class])
object AppDependencyProvider {

    @Provides
    @JvmStatic
    fun provideRetrofit(retrofitProvider: RetrofitProvider): Retrofit = retrofitProvider.getRetrofit()

    @Provides
    @JvmStatic
    fun provideLuasForecastingApi(retrofit: Retrofit): LuasForecastingApi = retrofit.create(LuasForecastingApi::class.java)

    @Provides
    @JvmStatic
    fun provideDatabase(context: Context): Database =
        Room.databaseBuilder(context, Database::class.java, "${Database::class.java.simpleName}.db").build()

    @Provides
    @JvmStatic
    fun provideForecastDao(db: Database): ForecastDao = db.getForecastDao()
}

/** An interface defining the way application-wide dependencies are being bound. */
@Module
interface AppDependencyBinder {

    @ContributesAndroidInjector
    fun injectMainActivity(): MainActivity

    /** Binds [MainViewModel] to `ViewModel` map. */
    @Binds
    @IntoMap
    @ViewModelMapKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    /** Binds the [App] context to `Application`. */
    @Binds
    fun bindApplication(application: App): Application

    /** Binds the [App] context to `Context`. */
    @Binds
    fun bindContext(application: App): Context

    /** Binds an instance of [ViewModelFactory] to `ViewModelProvider.Factory`. */
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    fun bindAppLifecycleOwner(impl: AppLifecycleOwnerImpl): AppLifecycleOwner

    @Binds
    fun bindsTimeBeforeNoon(impl: TimeBeforeNoonImpl): TimeBeforeNoon

    @Binds
    fun bindsForecastRepo(impl: ForecastRepoImpl): ForecastRepo
}