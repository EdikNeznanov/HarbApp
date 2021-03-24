package com.example.harbapp.view.weather

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.harbapp.R
import com.example.harbapp.databinding.DialogWeatherBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class WeatherDialog : DialogFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val args: WeatherDialogArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val weatherVM = ViewModelProvider(this, viewModelFactory)
            .get(WeatherDialogViewModel::class.java)
        val binder: DialogWeatherBinding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_weather, container, false)
        binder.lifecycleOwner = viewLifecycleOwner
        binder.vm = weatherVM
        weatherVM.getWeatherData(args.harbour)
        return binder.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(
            ColorDrawable(ContextCompat.getColor(view.context, R.color.fadedBackground))
        )
        val layoutParams = dialog?.window?.attributes
        layoutParams?.gravity = Gravity.CENTER
    }
}