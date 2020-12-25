package com.example.testapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.domain.Tram
import com.example.testapp.lists.AdapterDelegatesManager
import com.example.testapp.lists.BaseAdapter
import com.example.testapp.lists.BaseAdapterListener
import com.example.testapp.lists.delegates.FallbackAdapterDelegate
import com.example.testapp.lists.delegates.ForecastAdapterDelegate
import com.example.testapp.lists.delegates.TramSelectedListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.main_layout.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), TramSelectedListener, BaseAdapterListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy { ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java) }

    private val forecastAdapter = BaseAdapter(
        delegatesManager = AdapterDelegatesManager()
            .setFallbackDelegate(FallbackAdapterDelegate())
            .addDelegate(ForecastAdapterDelegate(this)),
        listener = this
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        list.adapter = forecastAdapter
        list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.forceReloadForecast()
    }

    override fun onResume() {
        super.onResume()

        reloadButton.setOnClickListener { viewModel.forceReloadForecast() }

        // Observe new CityWeather items having been loaded
        viewModel.observeForecast().observe(this) { (forecast, trams) ->
            stationName.text = getString(R.string.stationNameLabel, forecast.stop)
            stationAbr.text = getString(R.string.abbreviationLabel, forecast.stopAbv)
            stationMessage.text = getString(R.string.messageLabel, forecast.message)

            forecastAdapter.replaceData(trams)
        }

        // Observe whether some action is in Progress or not
        viewModel.observeProgress().observe(this) {
            loadingSpinner.isVisible = it
        }

        // Observe any Errors, such as network or I/O errors.
        viewModel.observeErrors().observe(this) {
            it.printStackTrace()
        }
    }

    override fun onForecastClicked(tram: Tram) {
        Toast.makeText(this, "${tram.dueMins} clicked", Toast.LENGTH_SHORT).show()
    }

    override fun listEmptied() {
        listEmpty.isVisible = true
        list.isVisible = false
    }

    override fun listFilled() {
        listEmpty.isVisible = false
        list.isVisible = true
    }

    override fun listStillEmpty() {
        listEmpty.isVisible = true
        list.isVisible = false
    }

    override fun listStillFilled() {
        listEmpty.isVisible = false
        list.isVisible = true
    }
}