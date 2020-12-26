package com.example.testapp.lists.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.testapp.R
import com.example.testapp.databinding.ListItemFallbackItemLayoutBinding
import com.example.testapp.lists.BaseAdapterDelegate
import com.example.testapp.lists.BaseViewHolder

class FallbackAdapterDelegate : BaseAdapterDelegate<Any, BaseViewHolder<ListItemFallbackItemLayoutBinding>>(Any::class.java) {

    override fun bindViewHolder(item: Any, holder: BaseViewHolder<ListItemFallbackItemLayoutBinding>) = Unit

    override fun createViewHolder(parent: ViewGroup): BaseViewHolder<ListItemFallbackItemLayoutBinding> =
        BaseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_fallback_item_layout, parent, false))
}
