package com.vn.f49kh.adapter.service

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.RowItemServiceBinding
import com.vn.f49kh.model.home.ItemHomeDTO
import extension.setOnSingleClickListener

class ServiceAdapter(var items: MutableList<ItemHomeDTO>, var heightRV: Int, var clickItem:(ItemHomeDTO) -> Unit) :
    RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    var heightItem = 1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowItemServiceBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_item_service, parent, false)
        if (heightItem <= 1) {
            var heightEachDeco =
                parent.context?.resources?.getDimensionPixelSize(R.dimen.activity_horizontal_margin) ?: 16;
            heightItem = (heightRV - heightEachDeco * 4) / 3
        }
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun insertData(items: MutableList<ItemHomeDTO>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowItemServiceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var item = items.getOrNull(position)
            item?.let {
                binding.item = item
                val layoutParams = itemView.layoutParams
                layoutParams.height = heightItem
                binding.root.setOnSingleClickListener {
                    clickItem.invoke(item)
                }
                binding.executePendingBindings()
            }
        }
    }


}
