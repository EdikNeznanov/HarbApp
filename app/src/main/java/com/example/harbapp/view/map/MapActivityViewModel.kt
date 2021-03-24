package com.example.harbapp.view.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.example.harbapp.model.HarbResponse
import com.example.harbapp.networking.HarbClient
import io.reactivex.Flowable
import java.io.Serializable
import javax.inject.Inject

class MapActivityViewModel @Inject constructor(private val harbClient: HarbClient) : ViewModel() {
    val harbours: LiveData<List<Harbour>> = getHarbs().toLiveData()

    private fun getHarbs(): Flowable<List<Harbour>> {
        return harbClient.retrieveHarbours()
            .map (::toHarbour)
            .toFlowable()
            .startWith(listOf<Harbour>())
    }

    private fun toHarbour(responseList: List<HarbResponse>) =
        responseList.map { Harbour(it.name, it.lat, it.lon) }

}
class Harbour(val name: String, val lat: Float, val lon: Float) : Serializable