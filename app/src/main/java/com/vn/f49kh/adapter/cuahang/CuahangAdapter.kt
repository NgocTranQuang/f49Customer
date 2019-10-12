package com.vn.f49kh.adapter.cuahang

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vn.f49kh.R
import com.vn.f49kh.databinding.RowItemCuahangBinding
import com.vn.f49kh.model.CuaHangDTO


class CuahangAdapter(
    var items: MutableList<CuaHangDTO>,
    var clickItem: (CuaHangDTO) -> Unit
) :
    RecyclerView.Adapter<CuahangAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RowItemCuahangBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.row_item_cuahang, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun insertData(items: MutableList<CuaHangDTO>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: RowItemCuahangBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            var item = items.getOrNull(position)
            item?.let {
                binding.data = it
                binding.executePendingBindings()
            }
        }
    }


}