package com.vn.f49kh.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.RowItemDashboardBinding
import com.vn.f49kh.model.home.ItemHomeDTO

class HomeAdapter(var items: MutableList<ItemHomeDTO>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    var heightItem = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowItemDashboardBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_item_dashboard, parent, false)
        if (heightItem == 1) {
            heightItem =
                (parent.measuredHeight - (((parent.context?.resources?.getDimensionPixelSize(R.dimen.height_line_size)
                    ?: 1) * 1))) / 2
        }
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = 4


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun insertData(items: MutableList<ItemHomeDTO>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowItemDashboardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val layoutParams = itemView.layoutParams
            layoutParams.height = heightItem
            var item = items.getOrNull(position)
            binding.item = item
            binding.executePendingBindings()
        }

    }
}

