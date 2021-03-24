package com.example.harbapp.di

import com.example.harbapp.HarbApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class, ApplicationModule::class, ViewModelsModule::class,
        AndroidComponentsBinder::class]
)

interface ApplicationComponent: AndroidInjector<HarbApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: HarbApplication): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(app: HarbApplication)
}