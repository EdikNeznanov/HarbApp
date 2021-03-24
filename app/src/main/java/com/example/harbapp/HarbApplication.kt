package com.example.harbapp

import android.app.Application
import com.example.harbapp.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class HarbApplication: Application(), HasAndroidInjector {

    @Inject
    lateinit var universalAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder().application(this).build().inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = universalAndroidInjector

}