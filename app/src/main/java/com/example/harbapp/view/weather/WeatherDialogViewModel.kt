package com.example.harbapp.view.weather

import android.util.Log
import android.view.View
import androidx.fragment.app.findFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.harbapp.model.OwmResponse
import com.example.harbapp.networking.OwmClient
import com.example.harbapp.view.map.Harbour
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

const val KELVIN_DIFFERENCE = 273f

class WeatherDialogViewModel @Inject constructor(private val owmClient: OwmClient) : ViewModel() {
    private val mutableWeatherReport = MutableLiveData<WeatherReport>()
    val weatherReport: LiveData<WeatherReport> = mutableWeatherReport
    private val compositeDisposable = CompositeDisposable()

    fun getWeatherData(harbour: Harbour) {
        owmClient.retrieveWeather(harbour.lat, harbour.lon)
            .map { owmResponseToWeatherReport(harbour.name, it) }
            .subscribe({
                mutableWeatherReport.postValue(it)
            }, {
                Log.e(this.javaClass.name, it.message?:"")
            })
            .addTo(compositeDisposable)
    }

    private fun owmResponseToWeatherReport(harbName: String, response: OwmResponse): WeatherReport {
        return WeatherReport(
            harbName, response.main.temp - KELVIN_DIFFERENCE,
            response.weather.first().main, response.main.tempMin - KELVIN_DIFFERENCE,
            response.main.tempMax - KELVIN_DIFFERENCE
        )
    }

    fun close(view: View) {
        view.findFragment<WeatherDialog>().dismiss()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}

data class WeatherReport(
    val placeName: String,
    val currentTemp: Float,
    val currentStatus: String,
    val minTemp: Float,
    val maxTemp: Float
)