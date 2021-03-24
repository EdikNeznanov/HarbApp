package com.example.harbapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.harbapp.view.map.MapActivityViewModel
import com.example.harbapp.view.weather.WeatherDialogViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory):
            ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MapActivityViewModel::class)
    abstract fun bindActivityViewModel(mapViewModel: MapActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherDialogViewModel::class)
    abstract fun bindWeatherDialogViewModel(mapViewModel: WeatherDialogViewModel): ViewModel
}