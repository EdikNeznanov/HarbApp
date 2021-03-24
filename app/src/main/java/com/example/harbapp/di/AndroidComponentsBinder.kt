package com.example.harbapp.di

import com.example.harbapp.view.MainActivity
import com.example.harbapp.view.map.MapFragment
import com.example.harbapp.view.weather.WeatherDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AndroidComponentsBinder {

    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    internal abstract fun bindMapFragment(): MapFragment

    @ContributesAndroidInjector(modules = [ViewModelsModule::class])
    internal abstract fun bindWeatherDialog(): WeatherDialog
}