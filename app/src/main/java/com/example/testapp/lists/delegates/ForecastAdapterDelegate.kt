package com.example.testapp.lists.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testapp.R
import com.example.testapp.databinding.ListItemForecastCardLayoutBinding
import com.example.testapp.domain.Tram
import com.example.testapp.lists.BaseAdapterDelegate
import com.example.testapp.lists.BaseViewHolder

class ForecastAdapterDelegate(private val listener: TramSelectedListener) :
    BaseAdapterDelegate<Tram, BaseViewHolder<ListItemForecastCardLayoutBinding>>(Tram::class.java) {

    override fun bindViewHolder(item: Tram, holder: BaseViewHolder<ListItemForecastCardLayoutBinding>) {
        holder.bind {
            dueInLabel.text = holder.context.getString(R.string.dueIn, item.dueMins)
            destinationLabel.text = holder.context.getString(R.string.destination, item.destination)

            root.setOnClickListener { listener.onForecastClicked(item) }
        }
    }

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<ListItemForecastCardLayoutBinding> =
        BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_forecast_card_layout, parent, false))
}


interface TramSelectedListener {
    fun onForecastClicked(tram: Tram)
}
