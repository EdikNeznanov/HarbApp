package com.example.harbapp.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.harbapp.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MapFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mMap: GoogleMap
    private var mapReady = false
    private var harbourList: List<Harbour> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        val vm = ViewModelProvider(this, viewModelFactory)
            .get(MapActivityViewModel::class.java)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            mapReady = true
            mMap.setOnMarkerClickListener (::navigateToWeather)
            updateMap()
        }
        vm.harbours.observe(viewLifecycleOwner, {
            harbourList = it
            updateMap()
        })
        return rootView
    }

    private fun navigateToWeather(marker: Marker): Boolean {
        val harbour = Harbour(
            marker.title,
            marker.position.latitude.toFloat(),
            marker.position.longitude.toFloat()
        )
        val action = MapFragmentDirections.mapFragmentToWeatherDialog(harbour)
        findNavController().navigate(action)
        return true
    }

    private fun updateMap() {
        if (mapReady && harbourList.isNotEmpty()) {
            mMap.clear()
            harbourList.forEach { harbour ->
                mMap.addMarker(
                    MarkerOptions().position(
                        LatLng(harbour.lat.toDouble(), harbour.lon.toDouble())
                    ).title(harbour.name))
            }
        }
    }
}